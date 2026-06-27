# ✍️ ALIŞTIRMALAR — Part 4

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
ArrayList<String> l = new ArrayList<>();
l.add("a"); l.add("b"); l.add("c");
System.out.println(l.indexOf("b") + " " + l.contains("x"));   // ?
```
<details><summary>Cevap</summary>

`1 false`. `indexOf("b")` → "b"nin indeksi = 1; `contains("x")` → liste "x" içermez = false.
</details>

**2. Soru**
```java
class IB {
    { System.out.println("blok"); }
    IB(){ System.out.println("ctor"); }
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
    static { System.out.println("static blok"); }
    { System.out.println("ornek blok"); }
    SB(){ System.out.println("ctor"); }
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
l.add(1); l.add(2); l.add(3);
l.clear();
System.out.println(l.isEmpty() + " " + l.size());   // ?
```
<details><summary>Cevap</summary>

`true 0`. `clear()` tüm elemanları siler → liste boş; `isEmpty()`=true, `size()`=0.
</details>

**5. Soru**
```java
class Hayvan {}
class Kedi extends Hayvan {}
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
