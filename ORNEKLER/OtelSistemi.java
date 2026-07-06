import java.util.ArrayList;

/*
 * Otel rezervasyon örneği: enum, exception, encapsulation, ArrayList
 * ve static sayaç bir arada.
 * Derle/çalıştır: javac OtelSistemi.java && java OtelSistemi
 */

enum OdaTipi {
    STANDART, DELUXE, SUIT
}

class OdaMusaitDegilException extends Exception {
    public OdaMusaitDegilException(String mesaj) {
        super(mesaj);
    }
}

class Oda {
    private int odaNo;
    private OdaTipi tip;
    private double gecelikUcret;
    private boolean doluMu;

    public Oda(int odaNo, OdaTipi tip, double gecelikUcret) {
        this.odaNo = odaNo;
        this.tip = tip;
        this.gecelikUcret = gecelikUcret;
        this.doluMu = false;
    }

    public void rezerveEt() throws OdaMusaitDegilException {
        if (doluMu) {
            throw new OdaMusaitDegilException(odaNo + " numarali oda zaten dolu!");
        }
        doluMu = true;
    }

    public void bosalt() {
        doluMu = false;
    }

    public int getOdaNo() {
        return odaNo;
    }

    public OdaTipi getTip() {
        return tip;
    }

    public double getGecelikUcret() {
        return gecelikUcret;
    }

    public boolean isDoluMu() {
        return doluMu;
    }
}

class Rezervasyon {
    private static int toplamRezervasyon = 0;
    private String misafirAdi;
    private Oda oda;
    private int geceSayisi;

    public Rezervasyon(String misafirAdi, Oda oda, int geceSayisi) throws OdaMusaitDegilException {
        oda.rezerveEt();
        this.misafirAdi = misafirAdi;
        this.oda = oda;
        this.geceSayisi = geceSayisi;
        toplamRezervasyon++;
    }

    public double toplamUcret() {
        return oda.getGecelikUcret() * geceSayisi;
    }

    public String getMisafirAdi() {
        return misafirAdi;
    }

    public Oda getOda() {
        return oda;
    }

    public static int getToplamRezervasyon() {
        return toplamRezervasyon;
    }
}

public class OtelSistemi {
    public static void main(String[] args) {
        ArrayList<Oda> odalar = new ArrayList<>();
        odalar.add(new Oda(101, OdaTipi.STANDART, 500));
        odalar.add(new Oda(102, OdaTipi.DELUXE, 800));

        ArrayList<Rezervasyon> rezervasyonlar = new ArrayList<>();

        try {
            rezervasyonlar.add(new Rezervasyon("Mehmet Demir", odalar.get(0), 3));
            rezervasyonlar.add(new Rezervasyon("Zeynep Kaya", odalar.get(1), 2));

            for (Rezervasyon r : rezervasyonlar) {
                System.out.println(r.getMisafirAdi() + " - Oda " + r.getOda().getOdaNo()
                        + " (" + r.getOda().getTip() + ") - Toplam: " + r.toplamUcret() + " TL");
            }

            rezervasyonlar.add(new Rezervasyon("Can Aydin", odalar.get(0), 1));

        } catch (OdaMusaitDegilException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("Toplam rezervasyon sayisi: " + Rezervasyon.getToplamRezervasyon());
    }
}
