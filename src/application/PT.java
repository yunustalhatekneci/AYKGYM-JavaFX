package application;

public class PT {
    private String adSoyad;
    private int paketSaat;
    private int fiyat;

    public PT(String adSoyad, int paketSaat) {
        this.adSoyad = adSoyad;
        this.paketSaat = paketSaat;

        if (paketSaat == 10) {
            this.fiyat = 500;
        } else if (paketSaat == 15) {
            this.fiyat = 700;
        } else {
            throw new IllegalArgumentException("Sadece 10 veya 15 saatlik paket olabilir.");
        }
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public int getPaketSaat() {
        return paketSaat;
    }

    public int getFiyat() {
        return fiyat;
    }

    @Override
    public String toString() {
        return adSoyad + " - " + paketSaat + " saat (" + fiyat + " TL)";
    }
}
