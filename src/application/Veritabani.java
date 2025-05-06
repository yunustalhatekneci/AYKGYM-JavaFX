package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Veritabani {
    private static final String URL = "jdbc:sqlite:uyeler.db";
    private static final bst bstKayitlar = new bst();

    // Veritabanı bağlantısı
    public static Connection baglantiAc() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
            return null;
        }
    }

    // Tabloyu oluştur
    public static void tabloOlustur() {
        String sql = """
            CREATE TABLE IF NOT EXISTS uyeler (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                adSoyad TEXT NOT NULL,
                telefon TEXT NOT NULL,
                yas INTEGER,
                salon TEXT,
                uyelikTipi TEXT,
                yillikMi INTEGER,
                pt TEXT
            );
        """;

        try (Connection conn = baglantiAc(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✔ Tablo oluşturuldu veya zaten mevcut.");
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }

    // Üye ekle (veritabanı + BST)
    public static void uyeEkle(String adSoyad, String telefon, int yas, String salon, String uyelikTipi, boolean yillikMi, String pt) {
        String sql = "INSERT INTO uyeler (adSoyad, telefon, yas, salon, uyelikTipi, yillikMi, pt) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = baglantiAc(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adSoyad);
            pstmt.setString(2, telefon);
            pstmt.setInt(3, yas);
            pstmt.setString(4, salon);
            pstmt.setString(5, uyelikTipi);
            pstmt.setInt(6, yillikMi ? 1 : 0);
            pstmt.setString(7, pt);
            pstmt.executeUpdate();

            // BST'ye de ekle
            SporSalonu s = new SporSalonu(salon, uyelikTipi);
            PT ptObj = (!"Yok".equalsIgnoreCase(pt)) ? new PT(pt, 10) : null;
            Üyelik uye = new Üyelik(adSoyad, telefon, yas, s, yillikMi);
            if (ptObj != null) uye.setPt(ptObj);
            bstKayitlar.ekle(uye);

            System.out.println("✔ Üye eklendi: " + adSoyad);
        } catch (SQLException e) {
            System.out.println("Ekleme hatası: " + e.getMessage());
        }
    }

    // Veritabanından tüm üyeleri getirir
    public static List<Üyelik> uyeleriGetir() {
        List<Üyelik> liste = new ArrayList<>();
        String sql = "SELECT * FROM uyeler";

        try (Connection conn = baglantiAc(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String adSoyad = rs.getString("adSoyad");
                String telefon = rs.getString("telefon");
                int yas = rs.getInt("yas");
                String salonStr = rs.getString("salon");
                String uyelikTipi = rs.getString("uyelikTipi");
                boolean yillikMi = rs.getInt("yillikMi") == 1;
                String ptStr = rs.getString("pt");

                try {
                    SporSalonu salon = new SporSalonu(salonStr, uyelikTipi);
                    PT pt = (ptStr != null && !ptStr.isEmpty() && !ptStr.equalsIgnoreCase("Yok")) ? new PT(ptStr, 10) : null;
                    Üyelik uye = new Üyelik(adSoyad, telefon, yas, salon, yillikMi);
                    if (pt != null) {
                        uye.setPt(pt);
                    }
                    liste.add(uye);
                } catch (Exception ex) {
                    System.out.println("Veri dönüştürme hatası: " + ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }

        return liste;
    }

    // BST sıfırla ve yeniden yükle 
    public static void yenidenYukle() {
        bstKayitlar.temizle();
        List<Üyelik> uyeler = uyeleriGetir();
        for (Üyelik u : uyeler) {
            bstKayitlar.ekle(u);
        }
    }

    // Alfabetik sıralı üyeleri döndür
    public static List<Üyelik> siraliUyeListesi() {
        return bstKayitlar.inOrderListe();
    }

    // Üye sil (adSoyad'a göre)
    public static void uyeSil(String adSoyad) {
        String sql = "DELETE FROM uyeler WHERE adSoyad = ?";

        try (Connection conn = baglantiAc(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, adSoyad);
            int silinen = pstmt.executeUpdate();
            if (silinen > 0) {
                System.out.println("✔ Veritabanından silindi: " + adSoyad);
                bstKayitlar.sil(adSoyad);  // BST'den de sil
            } else {
                System.out.println("Silinecek üye bulunamadı.");
            }
        } catch (SQLException e) {
            System.out.println("Silme hatası: " + e.getMessage());
        }
    }
}
