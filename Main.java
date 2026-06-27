import java.util.ArrayList;

/*
 * Java OOP örneği: enum, interface, exception, kalıtım,
 * ArrayList ve polimorfizm.
 * Derle/çalıştır: javac Main.java && java Main
 */

// enum
enum Tur {
    FILM,
    MUZIK,
    DIZI
}

// interface
interface Z {
    String getK();
    String getM();
}

// exception
class Y extends Exception {
    public Y(String mesaj) {
        super(mesaj);
    }
}

// üst sınıf
class Medya implements Z {

    private   String ad;
    protected String tc;
    protected int    ucret;
    protected Tur    tur;

    public Medya(String ad, String tc, int ucret, Tur tur) {
        this.ad    = ad;
        this.tc    = tc;
        this.ucret = ucret;
        this.tur   = tur;
    }

    // alt sınıflar override edebilir
    public void ucret() {
    }

    public String getAd()    { return ad;    }
    public String getTc()    { return tc;    }
    public int    getUcret() { return ucret; }

    @Override
    public String getK() {
        return "getK() -> Ad: " + ad + " | Tur: " + tur;
    }

    @Override
    public String getM() {
        return "getM() -> TC: " + tc + " | Ucret: " + ucret + " TL";
    }
}

// Medya listesini tutan sınıf
class Kutuphane {

    ArrayList<Medya> liste = new ArrayList<>();

    public Kutuphane() {
        liste.add(new Medya("Inception",         "11111111111", 50, Tur.FILM));
        liste.add(new Medya("Bohemian Rhapsody", "22222222222", 30, Tur.MUZIK));
        liste.add(new Medya("Breaking Bad",      "33333333333", 70, Tur.DIZI));
    }
}

public class Main {
    public static void main(String[] args) {

        Kutuphane k = new Kutuphane();

        for (Medya m : k.liste) {
            System.out.println("Ad    : " + m.getAd());
            System.out.println("TC    : " + m.getTc());
            System.out.println("Ucret : " + m.getUcret());
            System.out.println(m.getK());
            System.out.println(m.getM());
            System.out.println("----------------------------------");
        }

        // istisna örneği
        try {
            int ucret = -10;
            if (ucret < 0) {
                throw new Y("Ucret negatif olamaz!");
            }
        } catch (Y e) {
            System.out.println("Hata yakalandi -> " + e.getMessage());
        }
    }
}
