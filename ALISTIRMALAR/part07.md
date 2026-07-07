# ✍️ ALIŞTIRMALAR — Part 7

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR/README.md)

---

**1. Soru** (zor — Integer önbelleği)
```java
Integer a = 100;
Integer b = 100;
Integer c = 200;
Integer d = 200;
System.out.println(a == b);
System.out.println(c == d);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
true
false
```
Java, **-128 ile 127** arasındaki `Integer` değerlerini önbellekte (cache) tutup tekrar kullanır → `a` ve `b` aynı nesne (true). 200 bu aralığın dışında, her biri **ayrı nesne** oluşturulur → `c == d` false. (`equals()` kullansaydı ikisi de true olurdu.)
</details>

**2. Soru** (derlenir mi?)
```java
class Ata {
    final void yaz() {
        System.out.println("Ata");
    }
}

class Cocuk extends Ata {
    void yaz() {
        System.out.println("Cocuk");
    }
}
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `final` bir metot **override edilemez**. `Cocuk` sınıfı `yaz()`'ı ezmeye çalışınca "cannot override" hatası alınır.
</details>

**3. Soru** (derlenir mi?)
```java
final class A {
}

class B extends A {
}
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `final` bir sınıftan **miras alınamaz** (cannot inherit from final A). `final class` = "bu sınıfın alt sınıfı olamaz" demektir.
</details>

**4. Soru** (birden fazla interface)
```java
interface Ucabilir {
    void uc();
}

interface Yuzebilir {
    void yuz();
}

class Ordek implements Ucabilir, Yuzebilir {
    public void uc() {
        System.out.println("ucuyor");
    }

    public void yuz() {
        System.out.println("yuzuyor");
    }
}

Ordek o = new Ordek();
o.uc();
o.yuz();
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
ucuyor
yuzuyor
```
Bir sınıf `implements` ile **aynı anda birden fazla interface** uygulayabilir (virgülle ayrılır); `extends` ise tek sınıfla sınırlıdır.
</details>

**5. Soru** (interface sabiti)
```java
interface Sabitler {
    int MAX = 100;
}

class Kutu implements Sabitler {
}

System.out.println(Kutu.MAX);
// Çıktı?
```
<details><summary>Cevap</summary>

`100`. Interface içindeki alanlar otomatik olarak **`public static final`**'dir (sabit); `implements` eden sınıf bu sabite doğrudan erişebilir.
</details>

**6. Soru** (boşlukları doldur — 2 boyutlu dizi)
```java
int __(1)__ matris = {{1, 2}, {3, 4}};
System.out.println(matris[1][0]);
System.out.println(matris[0][1]);
```
<details><summary>Cevap</summary>

(1) `[][]` → `int[][] matris`. Çıktı: `3` sonra `2` (satır ve sütun indeksleriyle erişilir: `matris[satir][sutun]`).
</details>

**7. Soru** (dizi üzerinde for-each)
```java
int[] sayilar = {5, 10, 15};
int toplam = 0;
for (int s : sayilar) {
    toplam += s;
}
System.out.println(toplam);
// Çıktı?
```
<details><summary>Cevap</summary>

`30`. Genişletilmiş for (for-each) döngüsü dizideki her elemanı sırayla `s`'e atar; 5+10+15=30.
</details>

**8. Soru** (zor — referans takma adı / aliasing)
```java
class Kutu {
    int deger;
}

Kutu k1 = new Kutu();
k1.deger = 5;
Kutu k2 = k1;
k2.deger = 9;
System.out.println(k1.deger);
// Çıktı?
```
<details><summary>Cevap</summary>

`9`. `k2 = k1` yeni bir nesne KOPYALAMAZ; `k2` de aynı nesneyi gösterir (aynı referans). `k2` üzerinden yapılan değişiklik `k1`'de de görünür.
</details>

**9. Soru**
```java
String a = "Java";
String b = "JAVA";
System.out.println(a.equals(b));
System.out.println(a.equalsIgnoreCase(b));
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
false
true
```
`equals()` büyük/küçük harfe **duyarlıdır**; `equalsIgnoreCase()` harf büyüklüğünü yok sayar.
</details>

**10. Soru**
```java
String s = "elma,armut,muz";
String[] parcalar = s.split(",");
System.out.println(parcalar.length);
System.out.println(parcalar[1]);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
3
armut
```
`split(",")` string'i virgülden böler, bir String dizisi döner: `["elma","armut","muz"]`; indeks 1 = "armut".
</details>
