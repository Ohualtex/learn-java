import java.util.ArrayList;

/*
 * Kütüphane/kiralama örneği: enum, interface, exception, kalıtım,
 * ArrayList ve polimorfizmi bir arada gösterir.
 * Derle/çalıştır: javac MedyaKutuphanesi.java && java MedyaKutuphanesi
 */

// enum

enum Tur {
    FILM,
    MUZIK,
    DIZI
}

interface Z{

    String getK();
    String getM();

}

class Y extends Exception {
    
    public Y(String mesaj){
        super(mesaj);
    }

}

abstract class Medya implements Z{

    private String ad;
    protected String tc;
    protected int ucret;
    protected Tur tur;

    Medya(String ad, String tc, int ucret, Tur tur){

        this.ad = ad;
        this.tc = tc;
        this.ucret = ucret;
        this.tur = tur;
    }

    abstract void ucret();

    String getAd(){
        return ad;
    }

    String getTc(){
        return tc;
    }

    int getUcret(){
        return ucret;
    }

    public String getK(){
        return "K Metodu"; 
    }

    public String getM(){
        return "M Metodu";
    }
}

class Kutuphane{

    ArrayList<Medya> medyaListesi = new ArrayList<>();

    Kutuphane(){
        medyaListesi.add(new Medya("Inception", "111111111", 50, Tur.FILM) {
            void ucret() { }
        });
        medyaListesi.add(new Medya("Bohemian Rhapsody", "222222222", 30, Tur.MUZIK) {
            void ucret() { }
        });
        medyaListesi.add(new Medya("Breaking Bad", "333333333", 70, Tur.DIZI) {
            void ucret() { }
        });
    }
}

public class MedyaKutuphanesi{

    public static void main(String[] args){
        Kutuphane k = new Kutuphane();

        for(Medya m : k.medyaListesi){
            System.out.println("Ad: " + m.getAd());
            System.out.println("TC: " + m.getTc());
            System.out.println("Ücret: " + m.getUcret() + " TL");
            System.out.println("Tür: " + m.tur); // Enum'ı da ekrana basalım!
            System.out.println(m.getK());
            System.out.println(m.getM());
            System.out.println("--------------------");
        }

        // İstisna örneği:
        System.out.println("\n[Sistem Testi Başlıyor...]");
        try {
            boolean sistemHatasi = true;
            if(sistemHatasi) {
                throw new Y("Kütüphane sistemine ulaşılamıyor!");
            }
        } catch (Y e) {
            System.out.println("HATA YAKALANDI: " + e.getMessage());
        }
    }   
}