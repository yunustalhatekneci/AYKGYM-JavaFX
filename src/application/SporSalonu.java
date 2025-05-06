package application;

public class SporSalonu {
    private String lokasyon;
    private int aylikUcret;
    private boolean saunaVar;
    private boolean havuzVar;
    private boolean fitnessEkipmaniVar;
    private boolean tumSalonlaraGirisVar;
    private String salonTipi;
    private PT sorumluPT;

    
    public SporSalonu(String lokasyon, int aylikUcret, boolean saunaVar, boolean havuzVar, PT sorumluPT) {
        this.lokasyon = lokasyon;
        this.aylikUcret = aylikUcret;
        this.saunaVar = saunaVar;
        this.havuzVar = havuzVar;
        this.fitnessEkipmaniVar = true; // tüm salonlarda var
        this.sorumluPT = sorumluPT;

        if (aylikUcret == 4500) {
            salonTipi = "GOLD";
            tumSalonlaraGirisVar = true;
        } else if (aylikUcret == 2500) {
            salonTipi = "SILVER";
            tumSalonlaraGirisVar = true; // GOLD hariç
        } else {
            salonTipi = "BRONZE";
            tumSalonlaraGirisVar = false;
        }
    }

    
    public SporSalonu(String lokasyon, String salonTipi) {
        this.lokasyon = lokasyon;
        this.salonTipi = salonTipi.toUpperCase();
        this.fitnessEkipmaniVar = true; // tüm salonlarda var

        switch (this.salonTipi) {
            case "GOLD" -> {
                this.aylikUcret = 4500;
                this.saunaVar = true;
                this.havuzVar = true;
                this.tumSalonlaraGirisVar = true;
            }
            case "SILVER" -> {
                this.aylikUcret = 2500;
                this.saunaVar = false;
                this.havuzVar = false;
                this.tumSalonlaraGirisVar = true;
            }
            default -> {
                this.aylikUcret = 1500;
                this.saunaVar = false;
                this.havuzVar = false;
                this.tumSalonlaraGirisVar = false;
                this.salonTipi = "BRONZE";
            }
        }

        this.sorumluPT = null;
    }

    public String getSalonTipi() {
        return salonTipi;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public int getAylikUcret() {
        return aylikUcret;
    }

    public boolean isSaunaVar() {
        return saunaVar;
    }

    public boolean isHavuzVar() {
        return havuzVar;
    }

    public boolean isFitnessEkipmaniVar() {
        return fitnessEkipmaniVar;
    }

    public boolean isTumSalonlaraGirisVar() {
        return tumSalonlaraGirisVar;
    }

    public PT getSorumluPT() {
        return sorumluPT;
    }

    @Override
    public String toString() {
        String ptAd = (sorumluPT != null) ? sorumluPT.getAdSoyad() : "Belirtilmemiş";
        String girisHakki = tumSalonlaraGirisVar ? "Tüm salonlara giriş" : "Sadece kayıtlı salona giriş";
        return lokasyon + " (" + salonTipi + ", " + aylikUcret + " TL/ay, PT: " + ptAd +
               ", Fitness: Var, " + girisHakki + ")";
    }
}
