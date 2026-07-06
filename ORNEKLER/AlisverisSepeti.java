import java.util.ArrayList;

/*
 * Alışveriş sepeti örneği: enum, interface, exception, ArrayList,
 * encapsulation ve static sayaç bir arada.
 * Derle/çalıştır: javac AlisverisSepeti.java && java AlisverisSepeti
 */

enum Kategori {
    GIDA, ELEKTRONIK, GIYIM, KIRTASIYE
}

interface Indirimli {
    double indirimliFiyat();
}

class StokYetersizException extends Exception {
    public StokYetersizException(String mesaj) {
        super(mesaj);
    }
}

class Urun implements Indirimli {
    private String ad;
    private double fiyat;
    private int stok;
    private Kategori kategori;

    public Urun(String ad, double fiyat, int stok, Kategori kategori) {
        this.ad = ad;
        this.fiyat = fiyat;
        this.stok = stok;
        this.kategori = kategori;
    }

    public void stokAzalt(int adet) throws StokYetersizException {
        if (adet > stok) {
            throw new StokYetersizException(ad + " icin yeterli stok yok! (mevcut: " + stok + ")");
        }
        stok -= adet;
    }

    public String getAd() {
        return ad;
    }

    public double getFiyat() {
        return fiyat;
    }

    public int getStok() {
        return stok;
    }

    public Kategori getKategori() {
        return kategori;
    }

    @Override
    public double indirimliFiyat() {
        return fiyat * 0.9;
    }
}

class SepetSatiri {
    private Urun urun;
    private int adet;

    public SepetSatiri(Urun urun, int adet) {
        this.urun = urun;
        this.adet = adet;
    }

    public double toplam() {
        return urun.getFiyat() * adet;
    }

    public Urun getUrun() {
        return urun;
    }

    public int getAdet() {
        return adet;
    }
}

class Sepet {
    private static int olusturulanSepetSayisi = 0;
    private ArrayList<SepetSatiri> satirlar = new ArrayList<>();

    public Sepet() {
        olusturulanSepetSayisi++;
    }

    public void ekle(Urun urun, int adet) throws StokYetersizException {
        urun.stokAzalt(adet);
        satirlar.add(new SepetSatiri(urun, adet));
    }

    public double toplamTutar() {
        double toplam = 0;
        for (SepetSatiri s : satirlar) {
            toplam += s.toplam();
        }
        return toplam;
    }

    public static int getOlusturulanSepetSayisi() {
        return olusturulanSepetSayisi;
    }
}

public class AlisverisSepeti {
    public static void main(String[] args) {
        Urun laptop = new Urun("Laptop", 20000, 5, Kategori.ELEKTRONIK);
        Urun tisort = new Urun("Tisort", 150, 10, Kategori.GIYIM);

        Sepet sepet = new Sepet();
        try {
            sepet.ekle(laptop, 1);
            sepet.ekle(tisort, 3);
            System.out.println("Toplam tutar: " + sepet.toplamTutar() + " TL");
            System.out.println("Laptop indirimli fiyat: " + laptop.indirimliFiyat() + " TL");

            sepet.ekle(laptop, 10);
        } catch (StokYetersizException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("Olusturulan sepet sayisi: " + Sepet.getOlusturulanSepetSayisi());
    }
}
