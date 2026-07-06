# ✍️ ALIŞTIRMALAR — Part 8

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru** (birden fazla catch)
```java
try {
    int[] arr = new int[3];
    System.out.println(arr[5]);
} catch (ArithmeticException e) {
    System.out.println("aritmetik hata");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("dizi hatasi");
} catch (Exception e) {
    System.out.println("genel hata");
}
// Çıktı?
```
<details><summary>Cevap</summary>

`dizi hatasi`. `arr[5]` dizinin sınırını aşar → `ArrayIndexOutOfBoundsException` fırlar. Java, catch bloklarını yukarıdan aşağı sırayla kontrol eder; **ilk uyan** blok çalışır.
</details>

**2. Soru** (finally her zaman çalışır)
```java
try {
    System.out.println("deneme");
    throw new RuntimeException("hata");
} catch (RuntimeException e) {
    System.out.println("yakalandi");
} finally {
    System.out.println("finally");
}
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
deneme
yakalandi
finally
```
`finally` bloğu, hata fırlasın ya da fırlamasın, **her zaman** en son çalışır.
</details>

**3. Soru** (özel istisna + getMessage)
```java
class YetersizBakiyeException extends Exception {
    public YetersizBakiyeException(String mesaj) {
        super(mesaj);
    }
}

try {
    throw new YetersizBakiyeException("Bakiye yetersiz");
} catch (YetersizBakiyeException e) {
    System.out.println(e.getMessage());
}
// Çıktı?
```
<details><summary>Cevap</summary>

`Bakiye yetersiz`. Constructor'da `super(mesaj)` ile mesaj `Exception`'a iletilir; `getMessage()` bu mesajı geri verir.
</details>

**4. Soru** (özyineleme — recursion)
```java
static int faktoriyel(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * faktoriyel(n - 1);
}

System.out.println(faktoriyel(5));
// Çıktı?
```
<details><summary>Cevap</summary>

`120`. `faktoriyel(5)` = 5×4×3×2×1 = 120. Fonksiyon kendini daha küçük bir girdiyle çağırır (recursion); `n<=1` şartı **taban durum (base case)** olup döngüyü durdurur.
</details>

**5. Soru** (nesne dizisi)
```java
class Ogrenci {
    String ad;

    Ogrenci(String ad) {
        this.ad = ad;
    }
}

Ogrenci[] liste = new Ogrenci[2];
liste[0] = new Ogrenci("Ali");
liste[1] = new Ogrenci("Ayse");
System.out.println(liste[1].ad);
// Çıktı?
```
<details><summary>Cevap</summary>

`Ayse`. Diziler nesne de tutabilir; `liste[1]`, ikinci `Ogrenci` nesnesine (Ayşe) işaret eder.
</details>

**6. Soru** (derlenir mi? — checked exception)
```java
class BankaException extends Exception {
}

class Banka {
    void cek() {
        throw new BankaException();
    }
}
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `Exception`'dan türeyen (RuntimeException hariç) istisnalar **checked** sayılır; ya `try/catch` ile yakalanmalı ya da metot `throws BankaException` ile bildirmelidir. Düzeltme: `void cek() throws BankaException { ... }`.
</details>

**7. Soru** (boşluk doldur — static)
```java
class Yardimci {
    __(1)__ int kare(int x) {
        return x * x;
    }
}

Yardimci y = new Yardimci();
System.out.println(y.kare(4));
System.out.println(Yardimci.kare(5));
```
<details><summary>Cevap</summary>

(1) `static`. `static` metotlar nesneye ihtiyaç duymadan **sınıf adıyla** çağrılabilir (`Yardimci.kare(5)`); nesne üzerinden çağrılması da çalışır ama önerilmez. Çıktı: `16` sonra `25`.
</details>

**8. Soru** (overloading — hangi metot çağrılır)
```java
class A {
    void yaz(int x) {
        System.out.println("int: " + x);
    }

    void yaz(double x) {
        System.out.println("double: " + x);
    }
}

A a = new A();
a.yaz(5);
a.yaz(5.0);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
int: 5
double: 5.0
```
Java, argümanın **derleme zamanındaki tipine** göre hangi overload'un çağrılacağına karar verir: `5` (int) → int metodu, `5.0` (double) → double metodu.
</details>

**9. Soru** (zor — kısa devre / short-circuit)
```java
static boolean kontrol() {
    System.out.println("kontrol calisti");
    return true;
}

int x = 5;
if (x > 10 && kontrol()) {
    System.out.println("girdi");
}
System.out.println("bitti");
// Çıktı?
```
<details><summary>Cevap</summary>

`bitti`. `&&` **kısa devre** yapar: sol taraf (`x > 10`) false olunca sağ taraf (`kontrol()`) hiç çalıştırılmaz — "kontrol calisti" YAZILMAZ. Sadece "bitti" yazılır.
</details>

**10. Soru** (if / else-if zinciri)
```java
int not = 65;
if (not >= 85) {
    System.out.println("AA");
} else if (not >= 70) {
    System.out.println("BA");
} else if (not >= 60) {
    System.out.println("BB");
} else {
    System.out.println("KALDI");
}
// Çıktı?
```
<details><summary>Cevap</summary>

`BB`. Şartlar sırayla kontrol edilir; 65, `>=85` ve `>=70` şartlarını sağlamaz ama `>=60`'ı sağlar → "BB" yazdırılır ve zincir sona erer.
</details>
