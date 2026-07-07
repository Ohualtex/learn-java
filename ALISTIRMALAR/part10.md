# ✍️ ALIŞTIRMALAR — Part 10

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR/README.md)

---

**1. Soru** (harman: interface + abstract + static + ArrayList)
```java
interface Odeyebilir {
    double tutar();
}

abstract class Fatura implements Odeyebilir {
    static int sayac = 0;

    Fatura() {
        sayac++;
    }
}

class ElektrikFaturasi extends Fatura {
    public double tutar() {
        return 250.5;
    }
}

class SuFaturasi extends Fatura {
    public double tutar() {
        return 80.25;
    }
}

ArrayList<Fatura> faturalar = new ArrayList<>();
faturalar.add(new ElektrikFaturasi());
faturalar.add(new SuFaturasi());
double toplam = 0;
for (Fatura f : faturalar) {
    toplam += f.tutar();
}
System.out.println(toplam);
System.out.println(Fatura.sayac);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
330.75
2
```
250.5 + 80.25 = 330.75. Her `new` çağrısı `Fatura()` constructor'ını (gizli `super()` ile) çalıştırıp `sayac`'ı artırır → 2.
</details>

**2. Soru** (while — şart baştan yanlışsa hiç çalışmaz)
```java
int x = 20;
while (x < 10) {
    System.out.println(x);
    x++;
}
System.out.println("bitti");
// Çıktı?
```
<details><summary>Cevap</summary>

`bitti`. `while` şartı **girmeden önce** kontrol eder; `20 < 10` baştan yanlış olduğu için gövde hiç çalışmaz (bir önceki part'taki `do-while` ile karşılaştır — o en az bir kez çalışıyordu).
</details>

**3. Soru** (iç içe döngü)
```java
for (int i = 1; i <= 2; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.print(i + "" + j + " ");
    }
}
// Çıktı?
```
<details><summary>Cevap</summary>

`11 12 13 21 22 23 `. Dış döngü `i`=1,2; her `i` için iç döngü `j`=1,2,3 tamamen döner. `i + "" + j` iki sayıyı yan yana **string olarak** birleştirir (toplamaz).
</details>

**4. Soru** (equals override edilmemiş — varsayılan davranış)
```java
class Nokta {
    int x;

    Nokta(int x) {
        this.x = x;
    }
}

Nokta n1 = new Nokta(5);
Nokta n2 = new Nokta(5);
System.out.println(n1 == n2);
System.out.println(n1.equals(n2));
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
false
false
```
`Nokta`, `equals()`'ı **override etmemiş**; varsayılan `equals()` de `==` gibi referans karşılaştırır. `n1` ve `n2` farklı nesneler (x aynı olsa bile) → ikisi de false.
</details>

**5. Soru** (boşlukları doldur — tam sınıf iskeleti)
```java
class Urun {
    __(1)__ String ad;

    __(2)__ Urun(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }

    @__(3)__
    public String toString() {
        return "Urun: " + ad;
    }
}
```
<details><summary>Cevap</summary>

(1) `private`  (2) `public`  (3) `Override` — alan gizlenir, constructor dışarıdan çağrılabilsin diye `public`, `toString()` ata metodu eziyor.
</details>

**6. Soru** (derlenir mi? — dönüş tipi uyuşmazlığı)
```java
class Hesap {
    int getSayi() {
        return "5";
    }
}
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** Metot `int` döndürmeli ama `"5"` bir **String**. "incompatible types: String cannot be converted to int" hatası alınır.
</details>

**7. Soru** (harman: abstract + polimorfizm + dizi)
```java
abstract class Sekil {
    abstract double alan();
}

class Dikdortgen extends Sekil {
    double en, boy;

    Dikdortgen(double en, double boy) {
        this.en = en;
        this.boy = boy;
    }

    double alan() {
        return en * boy;
    }
}

class Ucgen extends Sekil {
    double taban, yukseklik;

    Ucgen(double taban, double yukseklik) {
        this.taban = taban;
        this.yukseklik = yukseklik;
    }

    double alan() {
        return (taban * yukseklik) / 2;
    }
}

Sekil[] sekiller = { new Dikdortgen(4, 5), new Ucgen(6, 4) };
for (Sekil s : sekiller) {
    System.out.println(s.alan());
}
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
20.0
12.0
```
Dikdörtgen: 4×5=20.0. Üçgen: (6×4)/2=12.0. Dizi `Sekil` tipinde ama her eleman kendi `alan()`'ını çalıştırır (polimorfizm).
</details>

**8. Soru** (zor — static metot override edilmez, gizlenir)
```java
class Ata {
    static void yaz() {
        System.out.println("Ata");
    }
}

class Cocuk extends Ata {
    static void yaz() {
        System.out.println("Cocuk");
    }
}

Ata a = new Cocuk();
a.yaz();
// Çıktı?
```
<details><summary>Cevap</summary>

`Ata`. **`static` metotlar override edilmez, gizlenir (hiding)**. Hangi metodun çağrılacağı, nesnenin gerçek tipine göre değil, **referansın bildirilen tipine** (`Ata a`) göre belirlenir — polimorfizm burada ÇALIŞMAZ. (Instance metotlarda `Cocuk` çalışırdı; bu klasik bir tuzaktır.)
</details>

**9. Soru** (boşlukları doldur — dizi `.length` vs liste `.size()`)
```java
int[] dizi = new int[5];
ArrayList<Integer> liste = new ArrayList<>();
liste.add(1);
liste.add(2);
System.out.println(dizi.__(1)__ + " " + liste.__(2)__());
```
<details><summary>Cevap</summary>

(1) `length` (özellik, parantezsiz)  (2) `size` (metot, parantezli) → çıktı `5 2`.
</details>

**10. Soru** (harman özet: interface + abstract + super + ArrayList + polimorfizm)
```java
interface Canli {
    String sesCikar();
}

abstract class Hayvan implements Canli {
    String ad;

    Hayvan(String ad) {
        this.ad = ad;
    }
}

class Kus extends Hayvan {
    Kus(String ad) {
        super(ad);
    }

    public String sesCikar() {
        return ad + ": Cik cik";
    }
}

class Balik extends Hayvan {
    Balik(String ad) {
        super(ad);
    }

    public String sesCikar() {
        return ad + ": ...";
    }
}

ArrayList<Hayvan> hayvanlar = new ArrayList<>();
hayvanlar.add(new Kus("Muhabbet Kusu"));
hayvanlar.add(new Balik("Japon Baligi"));
for (Hayvan h : hayvanlar) {
    System.out.println(h.sesCikar());
}
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
Muhabbet Kusu: Cik cik
Japon Baligi: ...
```
`Hayvan`, `Canli` interface'ini uygular; `Kus` ve `Balik` `super(ad)` ile ortak alanı doldurur; liste üzerinde dolaşırken her nesne kendi `sesCikar()`'ını çalıştırır (polimorfizm).
</details>
