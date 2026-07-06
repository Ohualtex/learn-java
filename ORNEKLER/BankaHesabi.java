import java.util.ArrayList;

/*
 * Banka hesabı örneği: abstract class, interface, kalıtım, instanceof
 * ile downcast, exception ve static sayaç bir arada.
 * Derle/çalıştır: javac BankaHesabi.java && java BankaHesabi
 */

class YetersizBakiyeException extends Exception {
    public YetersizBakiyeException(String mesaj) {
        super(mesaj);
    }
}

interface Faizli {
    double faizUygula();
}

abstract class Hesap {
    private static int hesapSayisi = 0;
    private String hesapSahibi;
    protected double bakiye;

    public Hesap(String hesapSahibi, double bakiye) {
        this.hesapSahibi = hesapSahibi;
        this.bakiye = bakiye;
        hesapSayisi++;
    }

    public void paraYatir(double miktar) {
        bakiye += miktar;
    }

    public void paraCek(double miktar) throws YetersizBakiyeException {
        if (miktar > bakiye) {
            throw new YetersizBakiyeException(hesapSahibi + " icin bakiye yetersiz!");
        }
        bakiye -= miktar;
    }

    public String getHesapSahibi() {
        return hesapSahibi;
    }

    public double getBakiye() {
        return bakiye;
    }

    public static int getHesapSayisi() {
        return hesapSayisi;
    }

    public abstract String hesapTuru();
}

class VadesizHesap extends Hesap {
    public VadesizHesap(String hesapSahibi, double bakiye) {
        super(hesapSahibi, bakiye);
    }

    @Override
    public String hesapTuru() {
        return "Vadesiz Hesap";
    }
}

class VadeliHesap extends Hesap implements Faizli {
    private double faizOrani;

    public VadeliHesap(String hesapSahibi, double bakiye, double faizOrani) {
        super(hesapSahibi, bakiye);
        this.faizOrani = faizOrani;
    }

    @Override
    public double faizUygula() {
        double faiz = bakiye * faizOrani;
        bakiye += faiz;
        return faiz;
    }

    @Override
    public String hesapTuru() {
        return "Vadeli Hesap";
    }
}

public class BankaHesabi {
    public static void main(String[] args) {
        ArrayList<Hesap> hesaplar = new ArrayList<>();
        hesaplar.add(new VadesizHesap("Ali Veli", 1000));
        hesaplar.add(new VadeliHesap("Ayse Yilmaz", 5000, 0.05));

        for (Hesap h : hesaplar) {
            System.out.println(h.getHesapSahibi() + " - " + h.hesapTuru() + " - Bakiye: " + h.getBakiye() + " TL");
        }

        try {
            hesaplar.get(0).paraCek(2000);
        } catch (YetersizBakiyeException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        for (Hesap h : hesaplar) {
            if (h instanceof Faizli) {
                Faizli f = (Faizli) h;
                double faiz = f.faizUygula();
                System.out.println(h.getHesapSahibi() + " icin uygulanan faiz: " + faiz + " TL");
            }
        }

        System.out.println("Toplam hesap sayisi: " + Hesap.getHesapSayisi());
    }
}
