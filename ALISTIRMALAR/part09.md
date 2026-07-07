# ✍️ ALIŞTIRMALAR — Part 9

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR/README.md)

---

**1. Soru** (break)
```java
for (int i = 0; i < 10; i++) {
    if (i == 3) {
        break;
    }
    System.out.println(i);
}
// Çıktı?
```
<details><summary>Cevap</summary>

```
0
1
2
```
`i==3` olunca `break` döngüyü **tamamen sonlandırır**; 3 ve sonrası hiç yazılmaz.
</details>

**2. Soru** (continue)
```java
for (int i = 0; i < 5; i++) {
    if (i % 2 == 0) {
        continue;
    }
    System.out.println(i);
}
// Çıktı?
```
<details><summary>Cevap</summary>

```
1
3
```
`continue`, o adımı **atlayıp** döngünün bir sonraki turuna geçer (döngüyü bitirmez). Çift sayılarda (0,2,4) atlanır, sadece tek sayılar (1,3) yazdırılır.
</details>

**3. Soru** (Arrays.sort)
```java
int[] sayilar = {5, 2, 8, 1};
Arrays.sort(sayilar);
System.out.println(Arrays.toString(sayilar));
// Çıktı?
```
<details><summary>Cevap</summary>

`[1, 2, 5, 8]`. `Arrays.sort()` diziyi yerinde (aynı dizi üzerinde) küçükten büyüğe sıralar; `Arrays.toString()` diziyi okunabilir yazdırır.
</details>

**4. Soru** (enum + switch)
```java
enum Gun {
    PAZARTESI, SALI, CARSAMBA
}

Gun g = Gun.SALI;
switch (g) {
    case PAZARTESI:
        System.out.println("1");
        break;
    case SALI:
        System.out.println("2");
        break;
    case CARSAMBA:
        System.out.println("3");
        break;
}
// Çıktı?
```
<details><summary>Cevap</summary>

`2`. `switch`, `enum` sabitleriyle de çalışır; `g` değeri `SALI` olduğu için o `case` çalışır.
</details>

**5. Soru** (String → int dönüşümü)
```java
String s = "42";
int x = Integer.parseInt(s);
System.out.println(x + 8);
// Çıktı?
```
<details><summary>Cevap</summary>

`50`. `Integer.parseInt()` metin halindeki sayıyı gerçek `int`'e çevirir; sonra normal toplama yapılır (42+8=50).
</details>

**6. Soru** (boşlukları doldur — private + getter)
```java
class Ogrenci {
    __(1)__ String ad;

    public String __(2)__() {
        return ad;
    }
}
```
<details><summary>Cevap</summary>

(1) `private`  (2) `getAd` — alan dışarıdan gizlenir (`private`), erişim için standart isimlendirmeyle bir **getter** (`getAd()`) yazılır.
</details>

**7. Soru** (harman: enum + kurucu metot + ArrayList)
```java
enum Renk {
    KIRMIZI, YESIL, MAVI
}

class Kalem {
    Renk renk;

    Kalem(Renk renk) {
        this.renk = renk;
    }
}

ArrayList<Kalem> kalemler = new ArrayList<>();
kalemler.add(new Kalem(Renk.KIRMIZI));
kalemler.add(new Kalem(Renk.MAVI));
for (Kalem k : kalemler) {
    System.out.println(k.renk);
}
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
KIRMIZI
MAVI
```
Her `Kalem` nesnesi kendi `Renk` enum değerini taşır; listedeki sırayla yazdırılır.
</details>

**8. Soru** (upcast / downcast)
```java
class Hayvan {
}

class Kopek extends Hayvan {
    void havla() {
        System.out.println("Hav");
    }
}

Hayvan h = new Kopek();
Kopek k = (Kopek) h;
k.havla();
// Çıktı?
```
<details><summary>Cevap</summary>

`Hav`. `Hayvan h = new Kopek()` otomatik **upcast** (üst tipe atama, Java kendisi yapar). `(Kopek) h` ise elle **downcast** (alt tipe indirme) — `Kopek`'e özgü `havla()`'ya erişmek için gereklidir.
</details>

**9. Soru** (bileşik atama operatörleri)
```java
int x = 10;
x += 5;
x -= 3;
x *= 2;
x /= 4;
System.out.println(x);
// Çıktı?
```
<details><summary>Cevap</summary>

`6`. Sırayla: 10+5=15 → 15-3=12 → 12*2=24 → 24/4=6.
</details>

**10. Soru** (zor — String değişmezliği)
```java
String s = "Java";
s.concat(" OOP");
System.out.println(s);
// Çıktı?
```
<details><summary>Cevap</summary>

`Java`. `String` **değişmezdir (immutable)**: `concat()` YENİ bir string döndürür, `s`'in kendisini değiştirmez. Sonuç bir değişkene atanmadığı (`s = s.concat(" OOP")` denmediği) için kaybolur.
</details>
