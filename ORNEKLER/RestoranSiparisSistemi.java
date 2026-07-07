import java.util.ArrayList;

enum Porsiyon {
    KUCUK,
    ORTA,
    BUYUK,
    AILE_BOYU
}

interface YemekOzellikleri {
    String icerikGetir();
}

class StokHatasi extends Exception {
    public StokHatasi(String message) {
        super(message); // Hata mesajı Exception sınıfına (üste) gönderiliyor
    }
} 

class Yemek implements YemekOzellikleri {
    private String isim;
    protected double fiyat;
    protected Porsiyon porsiyon;

    // Constructor
    public Yemek(String isim, double fiyat, Porsiyon porsiyon) {
        this.isim = isim;
        this.fiyat = fiyat;
        this.porsiyon = porsiyon;
    }
    
    // Private değişkene dışarıdan ulaşmak için Getter
    public String getIsim() {
        return isim;
    }

    // Arayüzden (Interface) gelen metodu ezmek (Override) ZORUNLUDUR
    @Override
    public String icerikGetir() {
        return "Standart İçerik";
    }
}

class Pizza extends Yemek {
    // Pizza sınıfının Constructor'ı
    public Pizza(String isim, double fiyat, Porsiyon porsiyon) {
        // Gelen bilgileri baba sınıfa (Yemek) yolluyoruz
        super(isim, fiyat, porsiyon);
    }

    // Yemek sınıfından gelen metodu eziyoruz
    @Override
    public String icerikGetir() {
        return "Peynir, Sucuk, Domates, Zeytin";
    }
}

class Adisyon {
    ArrayList<Yemek> siparisler = new ArrayList<>();

    // ArrayList'e ekleme işlemleri MUTLAKA Constructor (veya metot) içinde olmalıdır!
    public Adisyon() {
        siparisler.add(new Pizza("Margarita Pizza", 150.0, Porsiyon.ORTA));
        siparisler.add(new Pizza("Karışık Pizza", 200.0, Porsiyon.AILE_BOYU));
        
        // Hatayı test edebilmek için kasten eksi fiyatlı bir ürün ekliyoruz
        siparisler.add(new Pizza("Hatalı Pizza", -50.0, Porsiyon.KUCUK));
    }
}

public class RestoranSiparisSistemi {
    
    // Programın çalışması için ana metot ŞARTTIR!
    public static void main(String[] args) {
        Adisyon adisyon = new Adisyon();
        
        // for-each döngüsü: adisyon.siparisler listesindeki her yemeği dön
        for (Yemek siradakiYemek : adisyon.siparisler) {
            System.out.println("Sipariş: " + siradakiYemek.getIsim());
            System.out.println("Porsiyon: " + siradakiYemek.porsiyon);
            System.out.println("İçerik: " + siradakiYemek.icerikGetir());
            
            try {
                if (siradakiYemek.fiyat < 0) {
                    throw new StokHatasi("HATA: Yemek fiyatı negatif olamaz!");
                }
                System.out.println("Fiyat: " + siradakiYemek.fiyat + " TL");
                
            } catch (StokHatasi e) {
                System.out.println(e.getMessage());
            }
            
            System.out.println("-------------------------");
        }
    }
}
