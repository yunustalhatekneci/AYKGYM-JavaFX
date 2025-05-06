package application;

public class Üye {
    private String adSoyad;
    private String telefon;
    private int yas;
    protected SporSalonu salon;
    protected String uyelikTipi;
    protected boolean yillikMi;
    protected PT pt;

    
    public Üye(String adSoyad, String telefon, int yas) {
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.yas = yas;
    }

    
    public Üye(String adSoyad, String telefon, int yas, SporSalonu salon, String uyelikTipi, boolean yillikMi, PT pt) {
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.yas = yas;
        this.salon = salon;
        this.uyelikTipi = uyelikTipi;
        this.yillikMi = yillikMi;
        this.pt = pt;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getYas() {
        return yas;
    }

    public SporSalonu getSalon() {
        return salon;
    }

    public String getUyelikTipi() {
        return uyelikTipi;
    }

    public boolean isYillikMi() {
        return yillikMi;
    }

    public PT getPt() {
        return pt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ad Soyad: ").append(adSoyad)
          .append(", Telefon: ").append(telefon)
          .append(", Yaş: ").append(yas);

        // Bu kısımlar null değilse eklenir
        if (salon != null) sb.append(", Salon: ").append(salon.getLokasyon());
        if (uyelikTipi != null) sb.append(", Üyelik Tipi: ").append(uyelikTipi);
        if (pt != null) sb.append(", PT: ").append(pt.toString());
        if (uyelikTipi != null) sb.append(", Yıllık mı: ").append(yillikMi ? "Evet" : "Hayır");

        return sb.toString();
    }
}
