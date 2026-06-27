# ✍️ ALIŞTIRMALAR — Part 4

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
ArrayList<String> l = new ArrayList<>();
l.add("a");
l.add("b");
l.add("c");
System.out.println(l.indexOf("b") + " " + l.contains("x"));   // ?
```
<details><summary>Cevap</summary>

`1 false`. `indexOf("b")` → "b"nin indeksi = 1; `contains("x")` → liste "x" içermez = false.
</details>

**2. Soru**
```java
class IB {
    {
        System.out.println("blok");
    }

    IB() {
        System.out.println("ctor");
    }
}
// new IB();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
blok
ctor
```
Örnek (instance) başlatma bloğu `{ }`, constructor gövdesinden ÖNCE çalışır.
</details>

**3. Soru**
```java
class SB {
    static {
        System.out.println("static blok");
    }

    {
        System.out.println("ornek blok");
    }

    SB() {
        System.out.println("ctor");
    }
}
// new SB(); new SB();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
static blok
ornek blok
ctor
ornek blok
ctor
```
`static {}` bloğu sınıf yüklenince SADECE BİR KEZ; örnek bloğu + ctor her `new`'de tekrar çalışır.
</details>

**4. Soru**
```java
ArrayList<Integer> l = new ArrayList<>();
l.add(1);
l.add(2);
l.add(3);
l.clear();
System.out.println(l.isEmpty() + " " + l.size());   // ?
```
<details><summary>Cevap</summary>

`true 0`. `clear()` tüm elemanları siler → liste boş; `isEmpty()`=true, `size()`=0.
</details>

**5. Soru**
```java
class Hayvan {
}

class Kedi extends Hayvan {
}

Hayvan h = new Kedi();
System.out.println(h instanceof Kedi);
System.out.println(h instanceof Hayvan);
// Çıktı (2 satır)?
```
<details><summary>Cevap</summary>

```
true
true
```
`h` bir `Kedi` nesnesi; `Kedi` aynı zamanda `Hayvan` olduğu için ikisi de `true`.
</details>

**6. Soru**
```java
System.out.println(1 + 2 + "x" + 1 + 2);   // ?
```
<details><summary>Cevap</summary>

`3x12`. Soldan sağa: `1+2`=3 (sayı) → `3+"x"`="3x" (String) → `"3x"+1`="3x1" → `+2`="3x12". String'e değince `+` birleştirir.
</details>

**7. Soru**
```java
class Nokta {
    int x, y;

    Nokta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + "," + y;
    }
}

System.out.println(new Nokta(3, 4));   // ?
```
<details><summary>Cevap</summary>

`3,4`. `println` nesneyi yazdırırken otomatik `toString()` çağırır.
</details>

**8. Soru** (harman: kalıtım + static + constructor)
```java
class Arac {
    static int sayi = 0;

    Arac() {
        sayi++;
    }
}

class Araba extends Arac {
    Araba() {
    }
}

new Arac();
new Araba();
new Araba();
System.out.println(Arac.sayi);   // ?
```
<details><summary>Cevap</summary>

`3`. Her `Araba()` gizli `super()` ile `Arac()`'i çağırır → `sayi++`. 1 (Arac) + 1 (Araba) + 1 (Araba) = **3**.
</details>

**9. Soru** (boşlukları doldur)
```java
__(1)__ class Sekil {
    __(2)__ double alan();
}

class Kare extends Sekil {
    double k;

    Kare(double k) {
        this.k = k;
    }

    @Override
    double alan() {
        return k * k;
    }
}
```
<details><summary>Cevap</summary>

(1) `abstract`  (2) `abstract` — soyut sınıf ve soyut metot.
</details>

**10. Soru**
```java
System.out.println(7 / 2);
System.out.println(7.0 / 2);
System.out.println(7 % 3);
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
3
3.5
1
```
`7/2` tam sayı bölmesi = 3; `7.0/2` ondalıklı = 3.5; `7%3` kalan = 1.
</details>
