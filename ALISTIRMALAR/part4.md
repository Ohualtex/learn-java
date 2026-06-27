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
