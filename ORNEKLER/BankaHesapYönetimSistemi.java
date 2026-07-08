abstract class BankaHesabi{
    
    protected String hesapNo;
    protected double bakiye;

    double getBakiye(){
        return bakiye;
    }

    abstract void paraCek(double miktar) throws YetersizBakiyeException;

    void paraYatir(double miktar){
        bakiye = bakiye + miktar;
    }
}

class VadesizHesap extends BankaHesabi{

    void paraCek(double miktar) throws YetersizBakiyeException {
        if(bakiye > miktar){
        bakiye = bakiye - miktar;
            System.out.println("Para çekildi: " + miktar);
        }else{
            throw new YetersizBakiyeException("Yetersiz Bakiye");
        }
    }

}

class VadeliHesap extends BankaHesabi{

    void paraCek(double miktar) throws YetersizBakiyeException {
        if(bakiye > miktar){
            bakiye = bakiye - (miktar + bakiye * 0.05);
                System.out.println("Para çekildi: " + miktar);
        }else{
            throw new YetersizBakiyeException("Yetersiz Bakiye");
        }
    }

}

class YetersizBakiyeException extends Exception{

    public YetersizBakiyeException(String mesaj){
        super(mesaj);
    }
}

class BankaHesabiYonetimSistemi {

    public static void main(String[] args) {
        VadesizHesap ayse = new VadesizHesap();
        VadeliHesap ahmet = new VadeliHesap();

        ayse.paraYatir(2000);
        ahmet.paraYatir(7000);

        try {
            System.out.println("--- Ayşe'nin İşlemleri ---");
            ayse.paraCek(1000); // 1000 kalacak
            ayse.paraCek(3000); // HATA FIRLATACAK!
        } catch (YetersizBakiyeException e) {
            System.out.println("HATA: " + e.getMessage());
        }

        try {
            System.out.println("\n--- Ahmet'in İşlemleri ---");
            ahmet.paraCek(1000); // 1050 düşülecek
            ahmet.paraCek(3000); // 3150 düşülecek
        } catch (YetersizBakiyeException e) {
            System.out.println("HATA: " + e.getMessage());
        }

        System.out.println("\n--- SON DURUM ---");
        System.out.println("Ayşe Bakiye: " + ayse.getBakiye());
        System.out.println("Ahmet Bakiye: " + ahmet.getBakiye());

    }
}