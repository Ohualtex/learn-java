import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * RPG karakter oluşturma örneği — repodaki en kapsamlı örnek.
 * Gösterdiği kavramlar: 3 seviyeli kalıtım (Karakter -> Savasci/Buyucu/Avci
 * -> somut sınıflar), enum, Comparable (generics), HashMap/ArrayList,
 * özel exception'lar, static sayaç, polimorfizm ve savunmacı kopyalama
 * (defensive copy) ile gerçek encapsulation.
 * Derle/çalıştır: javac KarakterSistemi.java && java KarakterSistemi
 */

enum KarakterTipi {
    SAVASCI, BUYUCU, AVCI
}

enum EkipmanYuvasi {
    SILAH, ZIRH
}

enum YetenekTuru {
    FIZIKSEL, BUYU, DESTEK
}

class YetersizGumusException extends Exception {
    public YetersizGumusException(String mesaj) {
        super(mesaj);
    }
}

class UygunOlmayanEsyaException extends Exception {
    public UygunOlmayanEsyaException(String mesaj) {
        super(mesaj);
    }
}

class Yetenek {
    private String ad;
    private String aciklama;
    private YetenekTuru tur;

    public Yetenek(String ad, String aciklama, YetenekTuru tur) {
        this.ad = ad;
        this.aciklama = aciklama;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public YetenekTuru getTur() {
        return tur;
    }

    @Override
    public String toString() {
        return ad + " [" + tur + "] - " + aciklama;
    }
}

// Comparable<Esya>: eşyaların değerine göre sıralanabilmesini sağlar
class Esya implements Comparable<Esya> {
    private String ad;
    private double deger;
    private KarakterTipi uygunTip;
    private EkipmanYuvasi yuva;
    private Yetenek verdigiYetenek;

    public Esya(String ad, double deger, KarakterTipi uygunTip, EkipmanYuvasi yuva, Yetenek verdigiYetenek) {
        this.ad = ad;
        this.deger = deger;
        this.uygunTip = uygunTip;
        this.yuva = yuva;
        this.verdigiYetenek = verdigiYetenek;
    }

    public String getAd() {
        return ad;
    }

    public double getDeger() {
        return deger;
    }

    public KarakterTipi getUygunTip() {
        return uygunTip;
    }

    public EkipmanYuvasi getYuva() {
        return yuva;
    }

    public Yetenek getVerdigiYetenek() {
        return verdigiYetenek;
    }

    @Override
    public int compareTo(Esya digeri) {
        return Double.compare(this.deger, digeri.deger);
    }

    @Override
    public String toString() {
        return ad + " (" + deger + " gumus, " + yuva + ")";
    }
}

abstract class Karakter {
    private static int karakterSayisi = 0;

    private String ad;
    private double gumusKesesi;
    private ArrayList<Esya> envanter = new ArrayList<>();
    private Map<EkipmanYuvasi, Esya> kusanilanlar = new HashMap<>();

    public Karakter(String ad, double baslangicGumus) {
        this.ad = ad;
        this.gumusKesesi = baslangicGumus;
        karakterSayisi++;
    }

    public void gumusEkle(double miktar) {
        gumusKesesi += miktar;
    }

    public void gumusHarca(double miktar) throws YetersizGumusException {
        if (miktar > gumusKesesi) {
            throw new YetersizGumusException(ad + " icin gumus yetersiz! (bakiye: " + gumusKesesi + ")");
        }
        gumusKesesi -= miktar;
    }

    public double getGumusKesesi() {
        return gumusKesesi;
    }

    public void envantereEkle(Esya esya) {
        envanter.add(esya);
    }

    // dışarıya envanterin KOPYASI verilir; gerçek liste sadece karakterin
    // kendi metotlarıyla (envantereEkle, kusan) değişebilir
    public ArrayList<Esya> getEnvanter() {
        return new ArrayList<>(envanter);
    }

    public void kusan(Esya esya) throws UygunOlmayanEsyaException {
        if (esya.getUygunTip() != getTip()) {
            throw new UygunOlmayanEsyaException(
                    ad + " (" + unvan() + ") " + esya.getAd() + " esyasini kusanamaz! (" + esya.getUygunTip() + " sinifina ozel)");
        }
        kusanilanlar.put(esya.getYuva(), esya);
    }

    public ArrayList<Yetenek> aktifYetenekler() {
        ArrayList<Yetenek> liste = new ArrayList<>();
        for (Esya e : kusanilanlar.values()) {
            if (e.getVerdigiYetenek() != null) {
                liste.add(e.getVerdigiYetenek());
            }
        }
        return liste;
    }

    public void yetenekKullan(String yetenekAdi) {
        for (Yetenek y : aktifYetenekler()) {
            if (y.getAd().equalsIgnoreCase(yetenekAdi)) {
                System.out.println(ad + " -> " + y.getAd() + " yetenegini kullandi!");
                return;
            }
        }
        System.out.println(ad + " su an '" + yetenekAdi + "' yetenegini kullanamaz (uygun esya kusanili degil).");
    }

    public String getAd() {
        return ad;
    }

    public abstract KarakterTipi getTip();

    public abstract String unvan();

    public static int getKarakterSayisi() {
        return karakterSayisi;
    }

    @Override
    public String toString() {
        return ad + " - " + unvan() + " (" + getTip() + ") | Gumus: " + gumusKesesi
                + " | Envanter: " + envanter.size() + " esya | Kusanili: " + kusanilanlar.size();
    }
}

// --- Savaşçı dalı ---
abstract class Savasci extends Karakter {
    public Savasci(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public KarakterTipi getTip() {
        return KarakterTipi.SAVASCI;
    }
}

class Dovuscu extends Savasci {
    public Dovuscu(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Dovuscu";
    }
}

class Paladin extends Savasci {
    public Paladin(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Paladin";
    }
}

class Sovalye extends Savasci {
    public Sovalye(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Sovalye";
    }
}

// --- Büyücü dalı ---
abstract class Buyucu extends Karakter {
    public Buyucu(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public KarakterTipi getTip() {
        return KarakterTipi.BUYUCU;
    }
}

class Necromancer extends Buyucu {
    public Necromancer(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Necromancer";
    }
}

class Elementalist extends Buyucu {
    public Elementalist(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Elementalist";
    }
}

class Sifaci extends Buyucu {
    public Sifaci(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Sifaci";
    }
}

// --- Avcı dalı ---
abstract class Avci extends Karakter {
    public Avci(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public KarakterTipi getTip() {
        return KarakterTipi.AVCI;
    }
}

class Suikastci extends Avci {
    public Suikastci(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Suikastci";
    }
}

class Nisanci extends Avci {
    public Nisanci(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Nisanci";
    }
}

class Korucu extends Avci {
    public Korucu(String ad, double gumus) {
        super(ad, gumus);
    }

    @Override
    public String unvan() {
        return "Korucu";
    }
}

public class KarakterSistemi {
    public static void main(String[] args) {
        Paladin paladin = new Paladin("Kaan", 100);
        Necromancer necromancer = new Necromancer("Elif", 150);
        Nisanci nisanci = new Nisanci("Deniz", 80);

        paladin.gumusEkle(50);
        System.out.println(paladin);

        try {
            paladin.gumusHarca(500);
        } catch (YetersizGumusException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        Yetenek kutsalVurus = new Yetenek("Kutsal Vurus", "Dusmana kutsal hasar verir", YetenekTuru.FIZIKSEL);
        Yetenek ruhCagirma = new Yetenek("Ruh Cagirma", "Olu askerler cagirir", YetenekTuru.BUYU);
        Yetenek nisanAl = new Yetenek("Nisan Al", "Kritik vurus sansini artirir", YetenekTuru.FIZIKSEL);

        Esya kilic = new Esya("Kutsal Kilic", 200, KarakterTipi.SAVASCI, EkipmanYuvasi.SILAH, kutsalVurus);
        Esya kalkan = new Esya("Demir Kalkan", 100, KarakterTipi.SAVASCI, EkipmanYuvasi.ZIRH, null);
        Esya asa = new Esya("Olum Asasi", 250, KarakterTipi.BUYUCU, EkipmanYuvasi.SILAH, ruhCagirma);
        Esya yay = new Esya("Uzun Yay", 180, KarakterTipi.AVCI, EkipmanYuvasi.SILAH, nisanAl);

        paladin.envantereEkle(kilic);
        paladin.envantereEkle(kalkan);

        try {
            paladin.kusan(kilic);
            paladin.kusan(kalkan);
            System.out.println(paladin.getAd() + " aktif yetenekleri: " + paladin.aktifYetenekler());
        } catch (UygunOlmayanEsyaException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        paladin.yetenekKullan("Kutsal Vurus");
        paladin.yetenekKullan("Ruh Cagirma");

        try {
            paladin.kusan(asa);
        } catch (UygunOlmayanEsyaException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            necromancer.envantereEkle(asa);
            necromancer.kusan(asa);
            System.out.println(necromancer.getAd() + " aktif yetenekleri: " + necromancer.aktifYetenekler());
        } catch (UygunOlmayanEsyaException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            nisanci.envantereEkle(yay);
            nisanci.kusan(yay);
            System.out.println(nisanci.getAd() + " aktif yetenekleri: " + nisanci.aktifYetenekler());
        } catch (UygunOlmayanEsyaException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        ArrayList<Esya> tumEsyalar = new ArrayList<>();
        tumEsyalar.add(kilic);
        tumEsyalar.add(kalkan);
        tumEsyalar.add(asa);
        tumEsyalar.add(yay);
        Collections.sort(tumEsyalar);
        System.out.println("Degerine gore siralanmis esyalar: " + tumEsyalar);

        ArrayList<Esya> disaridanBakis = paladin.getEnvanter();
        disaridanBakis.add(asa);
        System.out.println("Disaridan eklenen kopyanin boyutu: " + disaridanBakis.size());
        System.out.println(paladin.getAd() + " gercek envanteri (etkilenmedi): " + paladin.getEnvanter().size());

        System.out.println("Toplam karakter sayisi: " + Karakter.getKarakterSayisi());
    }
}
