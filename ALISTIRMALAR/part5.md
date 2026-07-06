# ✍️ ALIŞTIRMALAR — Part 5

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
String a = "merhaba";
String b = "merhaba";
String c = new String("merhaba");
System.out.println(a == b);
System.out.println(a == c);
System.out.println(a.equals(c));
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
true
false
true
```
`a == b` → ikisi de string havuzundaki aynı nesne = true; `a == c` → `new String` ayrı nesne = false; `a.equals(c)` → içerik aynı = true. Kural: içerik `equals()` ile, referans `==` ile karşılaştırılır.
</details>

**2. Soru**
```java
int[] d = new int[3];
boolean[] b = new boolean[2];
String[] s = new String[2];
System.out.println(d[0] + " " + b[0] + " " + s[0]);
// Çıktı?
```
<details><summary>Cevap</summary>

`0 false null`. Dizi oluşturulunca elemanlar otomatik varsayılan değer alır: `int`→0, `boolean`→false, nesne (`String`)→null.
</details>

**3. Soru**
```java
int x = 5;
System.out.println(x++);
System.out.println(x);
System.out.println(++x);
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
5
6
7
```
`x++` önce değeri (5) kullanır, sonra artırır → x=6; sonra x=6 yazılır; `++x` önce artırır (7), sonra kullanır.
</details>

**4. Soru** (boşlukları doldur — erişim belirleyici)
```java
class Hesap {
    __(1)__ double bakiye;   // alt sınıflar erişebilsin ama dışarıya (private gibi) kapalı olsun
}

class VadeliHesap extends Hesap {
    void faizEkle() {
        bakiye = bakiye + 10;   // erişebiliyor
    }
}
```
<details><summary>Cevap</summary>

(1) `protected`. `protected` → kendi sınıfı + **alt sınıflar** (ve aynı paket) erişir; sınıf dışından `nesne.bakiye` ile erişim kapalıdır.
</details>

**5. Soru** (yazım kuralı — isimlendirme)
```java
// Java isimlendirme kuralına (convention) göre hangisi DOĞRU?
// a) class ogrenci { }
// b) class Ogrenci { }
// c) class OGRENCI { }
```
<details><summary>Cevap</summary>

**b) `class Ogrenci`**. Sınıf adları **PascalCase** (her kelime büyük harfle başlar). Ek olarak: metot/değişken → camelCase (`hesapla`, `toplamTutar`); sabitler → UPPER_CASE (`PI`, `MAX_SAYI`).
</details>

**6. Soru** (yazım kuralı — geçerli değişken adı)
```java
// Hangileri GEÇERLİ değişken adı?
int 2sayi;    // 1
int _sayi;    // 2
int sayi-1;   // 3
int $deger;   // 4
int class;    // 5
```
<details><summary>Cevap</summary>

Geçerli: **2 (`_sayi`)** ve **4 (`$deger`)**.
Geçersiz: **1** rakamla başlayamaz, **3** tire (`-`) içeremez, **5** `class` anahtar kelimedir.
Kural: harf, `_` veya `$` ile başlar; rakamla başlayamaz; anahtar kelime olamaz.
</details>

**7. Soru** (çıktı — String metotları)
```java
String s = "Merhaba";
System.out.println(s.length());
System.out.println(s.charAt(0));
System.out.println(s.substring(0, 3));
System.out.println(s.toUpperCase());
// Çıktı (4 satır)?
```
<details><summary>Cevap</summary>

```
7
M
Mer
MERHABA
```
`length()`=7 harf; `charAt(0)`=ilk harf 'M'; `substring(0,3)`=0..2 arası "Mer"; `toUpperCase()`=hepsi büyük.
</details>

**8. Soru** (boşlukları doldur — döngü)
```java
// 0, 1, 2, 3, 4 yazdır
for (int i = 0; i __(1)__ 5; i__(2)__) {
    System.out.println(i);
}
```
<details><summary>Cevap</summary>

(1) `<`  (2) `++`
</details>

**9. Soru** (çıktı — boolean operatörler)
```java
int a = 5, b = 10;
System.out.println(a > 3 && b < 5);
System.out.println(a > 3 || b < 5);
System.out.println(!(a == 5));
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
false
true
false
```
`a>3`=true, `b<5`=false → `&&` → false; `||` → true; `a==5`=true → `!` → false.
</details>

**10. Soru** (boşlukları doldur — sabit tanımı)
```java
// Sınıf sabiti: değeri değişmez + tüm nesneler paylaşır (ad BÜYÜK_HARF)
public __(1)__ __(2)__ int MAX_SAYI = 100;
```
<details><summary>Cevap</summary>

(1) `static`  (2) `final` → `static final`. `static` = tek kopya (sınıfa ait), `final` = değeri sonradan değişmez. Sabit adı `MAX_SAYI` gibi BÜYÜK_HARF + `_` ile yazılır.
</details>
