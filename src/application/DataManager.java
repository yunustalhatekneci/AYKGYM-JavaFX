package application;

import java.util.LinkedList;
import java.util.List;

public class DataManager {
    private List<Üyelik> kayitlar = new LinkedList<>();
    private bst agac = new bst();

    public void uyeEkle(Üyelik uye) {
        kayitlar.add(uye);
        agac.ekle(uye);  //  Artık uyumlu
    }

    public void uyeSil(Üyelik uye) {
        kayitlar.remove(uye);
        agac = new bst();  // BST’yi sıfırla
        for (Üyelik k : kayitlar) {
            agac.ekle(k);
        }
    }

    //  adSoyad ile silme eklendi
    public void uyeSil(String adSoyad) {
        Üyelik silinecek = null;
        for (Üyelik u : kayitlar) {
            if (u.getAdSoyad().equalsIgnoreCase(adSoyad)) {
                silinecek = u;
                break;
            }
        }
        if (silinecek != null) {
            uyeSil(silinecek); // yukarıdaki metodu çağır
        }
    }

    public List<Üyelik> getKayitlar() {
        return kayitlar;
    }

    public bst getAgac() {
        return agac;
    }
}
