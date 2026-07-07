import java.util.ArrayList;

enum YakitTuru {
    DIZEL,
    BENZIN,
    ELEKTIRIK

}

interface AracOzellikleri {
    String motorSesi();
}

class KiralamaHatasi extends Exception{
    public KiralamaHatasi(String mesaj) {
        super(mesaj);
    }
}

class Arac implements AracOzellikleri{

    private String plaka;
    protected int GunlukKira;
    protected YakitTuru yakit;

    public Arac(String plaka, int GunlukKira, YakitTuru yakit){
        this.plaka = plaka;
        this.GunlukKira = GunlukKira;
        this.yakit = yakit;
    }

    public String motorSesi(){
        if (this.yakit == YakitTuru.DIZEL) return "Vrummm";
        else if(this.yakit == YakitTuru.BENZIN) return "Vrooom";
        else return "vınnnnn";
    }

    public String getPlaka(){
        return plaka;
    }

    public void setPlaka(String plaka){
        this.plaka = plaka;

    }
}   

class ElektirikliArac extends Arac{
    public ElektirikliArac(String plaka,int GunlukKira){
        super(plaka, GunlukKira, YakitTuru.ELEKTIRIK);
    }

    public String motorSesi(){
            return "vıııız (sessiz)";
    }
}

class Galeri{    
    ArrayList<Arac> otopark = new ArrayList<>();

    public Galeri(){
        otopark.add(new Arac("34 ABC 12", 788, YakitTuru.DIZEL));
        otopark.add(new ElektirikliArac("06 DEF 78", 1000));
        // Kasten negatif kiralı bir araç ekliyoruz ki hatamızı görelim:
        otopark.add(new Arac("35 NEG 99", -500, YakitTuru.BENZIN));
    }
}

public class AracKiralamaSistemi {

    public static void main(String[] args){
        
        Galeri galerim = new Galeri();
        
        // Galerideki araçları tek tek geziyoruz
        for (Arac siradakiArac : galerim.otopark) {
            System.out.println("Plaka: " + siradakiArac.getPlaka());
            System.out.println("Motor: " + siradakiArac.motorSesi());
            
            // Hata fırlatma ihtimali olan yeri try-catch içine alıyoruz
            try {
                if (siradakiArac.GunlukKira < 0) {
                    // Şart sağlanırsa fırlat! Kod burada kesilir ve catch'e düşer.
                    throw new KiralamaHatasi("HATA: Günlük kira negatif olamaz!");
                }
                // Hata fırlatılmazsa normal çalışmaya devam eder
                System.out.println("Günlük Kira: " + siradakiArac.GunlukKira + " TL");
                
            } catch (KiralamaHatasi e) {
                // Fırlatılan hatayı yakalayıp ekrana mesajını basıyoruz
                System.out.println(e.getMessage());
            }
            
            System.out.println("-------------------------");
        }
    }
}