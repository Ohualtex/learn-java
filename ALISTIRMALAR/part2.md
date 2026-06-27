# ✍️ ALIŞTIRMALAR — Part 2

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
ArrayList<String> l = new ArrayList<>();
l.add("A"); l.add("B"); l.add("C"); l.add(0, "Z"); l.remove("B");
// l = ?   (DİKKAT: remove("B") -> string mi index mi?)
```
<details><summary>Cevap</summary>

`[Z, A, C]`. `[A,B,C]`→add(0,"Z")`[Z,A,B,C]`→remove("B"): argüman **String** → `remove(Object)` → **"B" değerini** siler → **`[Z, A, C]`**
</details>

**2. Soru** (harman: abstract + override + polimorfizm + ArrayList + static)
```java
abstract class Urun {
    static int adet = 0;
    String ad;
    Urun(String ad){ this.ad = ad; adet++; }
    abstract double fiyat();
}
class Kitap extends Urun { Kitap(String ad){ super(ad); } double fiyat(){ return 50; } }
class Kalem extends Urun { Kalem(String ad){ super(ad); } double fiyat(){ return 10; } }

ArrayList<Urun> liste = new ArrayList<>();
liste.add(new Kitap("Java"));
liste.add(new Kalem("Tukenmez"));
liste.add(new Kitap("Roman"));
double toplam = 0;
for (Urun u : liste) toplam += u.fiyat();
System.out.println("Toplam: " + toplam);
System.out.println("Adet: " + Urun.adet);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
Toplam: 110.0
Adet: 3
```
Kitap(50)+Kalem(10)+Kitap(50)=110.0 ; her `new` constructor'da `adet++` → 3.
</details>

**3. Soru**
```java
class Kutu {
    int en, boy;
    Kutu()              { this(2, 3); }
    Kutu(int en, int boy){ this.en = en; this.boy = boy; }
    int alan()          { return en * boy; }
}
Kutu k = new Kutu();
System.out.println(k.alan());   // ?
```
<details><summary>Cevap</summary>

`6`. `this(2,3)` → en=2, boy=3 → alan = 2*3 = **6**.
</details>

**4. Soru**
```java
class Ata { void yaz(){ System.out.println("Ata"); } }
class Cocuk extends Ata {
    @Override void yaz(){ super.yaz(); System.out.println("Cocuk"); }
}
// new Cocuk().yaz();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
Ata
Cocuk
```
`super.yaz()` önce atayı çağırır.
</details>

**5. Soru** (boşlukları doldur)
```java
ArrayList<Integer> l = new ArrayList<>();
l.add(1); l.add(2); l.add(3);     // [1, 2, 3]
l.__(1)__ ;     // listeyi [1, 9, 2, 3] yap
l.__(2)__ ;     // sonra [1, 9, 2, 99] yap (sondaki 3 -> 99)
```
<details><summary>Cevap</summary>

(1) `add(1, 9)`  (2) `set(3, 99)`  → sonuç `[1, 9, 2, 99]`
</details>

**6. Soru**
```java
class Nesne { static int sayac = 0; Nesne(){ sayac++; } }
new Nesne(); new Nesne(); new Nesne();
System.out.println(Nesne.sayac);   // ?
```
<details><summary>Cevap</summary>

`3`. Her `new Nesne()` constructor'da `sayac++` yapar → 3.
</details>

**7. Soru** (derlenir mi?)
```java
class Ata { Ata(int x){ System.out.println("Ata " + x); } }
class Cocuk extends Ata { Cocuk(){ System.out.println("Cocuk"); } }
// Bu kod DERLENİR Mİ? Neden?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `Cocuk()` içinde gizli `super()` çağrılır ama `Ata`'da parametresiz constructor yok (sadece `Ata(int)`). Düzeltme: `Cocuk(){ super(0); ... }`.
</details>

**8. Soru**
```java
ArrayList<Integer> l = new ArrayList<>();
l.add(5); l.add(6); l.add(7); l.remove(1);
// l = ?   (remove(1) değeri mi indeksi mi siler?)
```
<details><summary>Cevap</summary>

`[5, 7]`. `remove(1)`: argüman **int** → `remove(int index)` → **indeks 1** (=6) silinir.
</details>

**9. Soru**
```java
abstract class Sekil { abstract double alan(); }
class Kare  extends Sekil { double k; Kare(double k){ this.k=k; }  double alan(){ return k*k; } }
class Daire extends Sekil { double r; Daire(double r){ this.r=r; } double alan(){ return 3*r*r; } }

Sekil s = new Kare(4);   System.out.println(s.alan());
s = new Daire(2);        System.out.println(s.alan());
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
16.0
12.0
```
Kare(4)=4*4=16.0 ; Daire(2)=3*2*2=12.0. Aynı `s` referansı ama çağrılan metot **nesneye** göre belirlenir.
</details>

**10. Soru** (boşlukları doldur)
```java
__(1)__ Z { String getK(); }
class Medya __(2)__ Z {
    __(3)__ String getK(){ return "K"; }
}
```
<details><summary>Cevap</summary>

(1) `interface`  (2) `implements`  (3) `public` (interface metotları public uygulanmalı)
</details>
