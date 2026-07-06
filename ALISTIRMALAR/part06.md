# ✍️ ALIŞTIRMALAR — Part 6

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru** (switch — dikkat: break yok)
```java
int gun = 2;
switch (gun) {
    case 1:
        System.out.println("Pazartesi");
    case 2:
        System.out.println("Sali");
    case 3:
        System.out.println("Carsamba");
        break;
    case 4:
        System.out.println("Persembe");
}
// Çıktı?
```
<details><summary>Cevap</summary>

```
Sali
Carsamba
```
`gun=2` → `case 2` eşleşir ama `break` yok → **düşer (fall-through)** → `case 3` de çalışır, orada `break` var ve durur.
</details>

**2. Soru** (do-while)
```java
int x = 10;
do {
    System.out.println(x);
    x++;
} while (x < 5);
// Çıktı?
```
<details><summary>Cevap</summary>

`10`. `do-while` şartı SONDA kontrol eder; gövde en az **bir kez** çalışır. İlk çalışmada x=10 yazılır, x=11 olur, şart (11<5) yanlış, döngü biter.
</details>

**3. Soru**
```java
int a = 7;
String sonuc = (a % 2 == 0) ? "cift" : "tek";
System.out.println(sonuc);
// Çıktı?
```
<details><summary>Cevap</summary>

`tek`. Üçlü operatör (ternary): `a % 2 == 0` yanlış (7 tek sayı) → `:`den sonraki değer seçilir → "tek".
</details>

**4. Soru**
```java
System.out.println(Math.max(3, 8));
System.out.println(Math.abs(-5));
System.out.println(Math.pow(2, 3));
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
8
5
8.0
```
`Math.max` büyüğü verir; `Math.abs` mutlak değer; `Math.pow(2,3)` = 2 üzeri 3 = 8.0 (sonuç her zaman `double`).
</details>

**5. Soru** (derlenir mi?)
```java
final int SAYI = 10;
SAYI = 20;
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `final` değişkene bir kez değer verildikten sonra **değiştirilemez** (cannot assign a value to final variable).
</details>

**6. Soru** (method overloading — aşırı yükleme)
```java
class Hesap {
    int topla(int a, int b) {
        return a + b;
    }

    double topla(double a, double b) {
        return a + b;
    }
}

Hesap h = new Hesap();
System.out.println(h.topla(2, 3));
System.out.println(h.topla(2.5, 3.5));
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
5
6.0
```
Aynı isimli, farklı parametre tipli iki metot = **overloading**. Java, verilen argüman tipine göre doğru metodu seçer (int→int metodu, double→double metodu).
</details>

**7. Soru** (casting — tip dönüşümü)
```java
double d = 9.7;
int i = (int) d;
System.out.println(i);
// Çıktı?
```
<details><summary>Cevap</summary>

`9`. `(int)` dönüşümü **yuvarlamaz, keser** (truncate) — ondalık kısmı atılır.
</details>

**8. Soru** (char aritmetiği)
```java
char c = 'A';
int x = c + 1;
System.out.println(x);
System.out.println((char) (c + 1));
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
66
B
```
`char`, arka planda bir sayıdır (ASCII/Unicode kodu). `'A'` = 65, `+1` = 66 (int). `(char)` ile geri harfe çevrilince 66 → 'B'.
</details>

**9. Soru** (dizi `.length` vs String `.length()`)
```java
int[] arr = {1, 2, 3, 4};
String s = "merhaba";
System.out.println(arr.length + " " + s.length());
// Çıktı?
```
<details><summary>Cevap</summary>

`4 7`. Dizide `.length` bir **özelliktir** (parantez yok); String'de `.length()` bir **metottur** (parantez var). Karıştırılırsa derleme hatası olur.
</details>

**10. Soru** (StringBuilder)
```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" OOP");
sb.reverse();
System.out.println(sb);
// Çıktı?
```
<details><summary>Cevap</summary>

`POO avaJ`. `append` sona ekler → "Java OOP"; `reverse()` tüm karakterleri ters çevirir → "POO avaJ". `StringBuilder`, `String`'in aksine **değiştirilebilir (mutable)**.
</details>
