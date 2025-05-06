package application;

public class Üyelik extends Üye {
    private SporSalonu salon;
    private String uyelikTuru;
    private boolean yillikUyelik;
    private PT pt;

    public Üyelik(String adSoyad, String telefon, int yas, SporSalonu salon, boolean yillikUyelik) {
        super(adSoyad, telefon, yas);
        this.salon = salon;
        this.uyelikTuru = belirleUyelikTuru(salon);
        this.yillikUyelik = yillikUyelik;
    }

    private String belirleUyelikTuru(SporSalonu salon) {
        String tip = salon.getSalonTipi().toUpperCase();
        if (tip.equals("GOLD") || tip.equals("SILVER")) {
            return tip;
        } else {
            return "BRONZE";
        }
    }

    public void setPt(PT pt) {
        this.pt = pt;
    }

    public PT getPt() {
        return pt;
    }

    public SporSalonu getSalon() {
        return salon;
    }

    public String getUyelikTuru() {
        return uyelikTuru;
    }

    public boolean isYillikUyelik() {
        return yillikUyelik;
    }

    public int toplamUcret() {
        switch (uyelikTuru) {
            case "GOLD":
                return yillikUyelik ? 45000 : 4500;
            case "SILVER":
                return yillikUyelik ? 25000 : 2500;
            default:
                return 1500;
        }
    }

    public boolean salonGirisYetkisi(SporSalonu hedefSalon) {
        if (uyelikTuru.equals("GOLD")) {
            return true;
        } else if (uyelikTuru.equals("SILVER")) {
            return !hedefSalon.getSalonTipi().equalsIgnoreCase("GOLD");
        } else {
            return hedefSalon.getLokasyon().equalsIgnoreCase(salon.getLokasyon());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ad Soyad: ").append(getAdSoyad());
        sb.append("\nTelefon: ").append(getTelefon());
        sb.append("\nYaş: ").append(getYas());

        if (salon != null) {
            sb.append("\nSalon: ").append(salon.getLokasyon());
            sb.append("\nÜyelik Tipi: ").append(uyelikTuru);
            sb.append(yillikUyelik ? " (Yıllık)" : " (Aylık)");
            sb.append("\nÜcret: ").append(toplamUcret()).append(" TL");
            sb.append("\nFitness Ekipmanı: ").append(salon.isFitnessEkipmaniVar() ? "Var" : "Yok");
            sb.append("\nGiriş Hakkı: ").append(salon.isTumSalonlaraGirisVar() ? "Tüm salonlara giriş" : "Sadece kayıtlı salon");
        }

        if (pt != null) {
            sb.append("\nPT: ").append(pt.toString());
        }

        return sb.toString();
    }
}
