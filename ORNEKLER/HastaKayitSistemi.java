import java.util.ArrayList;

/*
 * Hasta kayıt sistemi örneği: abstract class, kalıtım, polimorfizm,
 * enum ve exception bir arada.
 * Derle/çalıştır: javac HastaKayitSistemi.java && java HastaKayitSistemi
 */

enum Bolum {
    DAHILIYE, KARDIYOLOJI, ORTOPEDI, COCUK
}

class RandevuCakismasiException extends Exception {
    public RandevuCakismasiException(String mesaj) {
        super(mesaj);
    }
}

abstract class Kisi {
    private String ad;
    protected String tc;

    public Kisi(String ad, String tc) {
        this.ad = ad;
        this.tc = tc;
    }

    public String getAd() {
        return ad;
    }

    public String getTc() {
        return tc;
    }

    public abstract String bilgi();
}

class Doktor extends Kisi {
    private Bolum bolum;
    private ArrayList<String> randevuSaatleri = new ArrayList<>();

    public Doktor(String ad, String tc, Bolum bolum) {
        super(ad, tc);
        this.bolum = bolum;
    }

    public void randevuEkle(String saat) throws RandevuCakismasiException {
        if (randevuSaatleri.contains(saat)) {
            throw new RandevuCakismasiException("Dr. " + getAd() + " icin " + saat + " saati dolu!");
        }
        randevuSaatleri.add(saat);
    }

    public Bolum getBolum() {
        return bolum;
    }

    @Override
    public String bilgi() {
        return "Dr. " + getAd() + " - " + bolum;
    }
}

class Hasta extends Kisi {
    private static int hastaSayisi = 0;
    private int yas;

    public Hasta(String ad, String tc, int yas) {
        super(ad, tc);
        this.yas = yas;
        hastaSayisi++;
    }

    public int getYas() {
        return yas;
    }

    @Override
    public String bilgi() {
        return getAd() + " (" + yas + ") - TC: " + getTc();
    }

    public static int getHastaSayisi() {
        return hastaSayisi;
    }
}

public class HastaKayitSistemi {
    public static void main(String[] args) {
        Doktor drAyse = new Doktor("Ayse Yilmaz", "11111111111", Bolum.KARDIYOLOJI);
        Hasta hasta1 = new Hasta("Ahmet Kara", "22222222222", 45);
        Hasta hasta2 = new Hasta("Fatma Sen", "33333333333", 30);

        ArrayList<Kisi> kisiler = new ArrayList<>();
        kisiler.add(drAyse);
        kisiler.add(hasta1);
        kisiler.add(hasta2);

        for (Kisi k : kisiler) {
            System.out.println(k.bilgi());
        }

        try {
            drAyse.randevuEkle("10:00");
            drAyse.randevuEkle("11:00");
            System.out.println("Randevular basariyla olusturuldu.");

            drAyse.randevuEkle("10:00");
        } catch (RandevuCakismasiException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("Toplam hasta sayisi: " + Hasta.getHastaSayisi());
    }
}
