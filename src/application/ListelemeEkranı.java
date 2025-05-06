package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ListelemeEkranı {

    public void goster() {
        Stage pencere = new Stage();
        pencere.setTitle("KAYITLI ÜYELER");

        TextArea alan = new TextArea();
        alan.setEditable(false);
        alan.setStyle("-fx-font-family: 'monospace'; -fx-font-size: 13px;");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox butonlar = new HBox(10);

        Button yenileBtn = new Button("🔄 Tümünü Listele");
        Button siraliBtn = new Button("🔠 Alfabetik Sırala");
        Button silBtn = new Button("❌ Üye Sil");

        butonlar.getChildren().addAll(yenileBtn, siraliBtn, silBtn);

        // Listeleme işlemleri
        yenileBtn.setOnAction(e -> {
            alan.setText(olusturVeritabaniListesi());
        });

        siraliBtn.setOnAction(e -> {
            Veritabani.yenidenYukle(); // BST temizlenip yeniden oluşturulur
            alan.setText(olusturSiraliListe());
        });

        silBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Üye Sil");
            dialog.setHeaderText("Silmek istediğiniz üyenin adını ve soyadını girin:");
            dialog.setContentText("Ad Soyad:");
            dialog.showAndWait().ifPresent(ad -> {
                String adSoyad = ad.trim();
                Veritabani.uyeSil(adSoyad); // Veritabanı ve BST'den sil
                alan.setText(olusturVeritabaniListesi()); // Listeyi güncelle
            });
        });

        // Açılışta tüm kayıtları göster
        alan.setText(olusturVeritabaniListesi());

        root.getChildren().addAll(butonlar, alan);

        Scene scene = new Scene(root, 600, 500);
        pencere.setScene(scene);
        pencere.show(); // PENCEREYİ AÇ
    }

    private String olusturVeritabaniListesi() {
        List<Üyelik> uyeler = Veritabani.uyeleriGetir();
        StringBuilder sb = new StringBuilder();
        sb.append("📋 KAYITLAR (En son):\n\n");
        for (Üyelik u : uyeler) {
            sb.append(u.toString()).append("\n\n");
        }
        return sb.toString();
    }

    private String olusturSiraliListe() {
        List<Üyelik> sirali = Veritabani.siraliUyeListesi();
        StringBuilder sb = new StringBuilder();
        sb.append("🔠 KAYITLAR (Alfabetik):\n\n");
        for (Üyelik u : sirali) {
            sb.append(u.toString()).append("\n\n");
        }
        return sb.toString();
    }
}
