# ✍️ ALIŞTIRMALAR — Part 1

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
ArrayList<Integer> a = new ArrayList<>();
a.add(10); a.add(20); a.add(1, 99); a.set(0, 5); a.remove(2);
// a = ?
```
<details><summary>Cevap</summary>

`[5, 99]` — `[10]`→`[10,20]`→add(1,99)`[10,99,20]`→set(0,5)`[5,99,20]`→remove(2) (indeks 2 = 20 silinir)→ **`[5, 99]`**
</details>

**2. Soru**
```java
class X { X(){ System.out.println("X"); } }
class Y extends X { Y(){ System.out.println("Y"); } }
// new Y();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
X
Y
```
super önce → ata önce çalışır (yukarıdan aşağı).
</details>

**3. Soru**
```java
class S { static int t = 1; void f(){ t = t * 2; } }
S a = new S();  S b = new S();
a.f(); b.f(); a.f();
System.out.println(b.t);   // ?
```
<details><summary>Cevap</summary>

`8`. t=1 → a.f()=2 → b.f()=4 → a.f()=8. `b.t` de aynı static t → **8**.
</details>

**4. Soru**
```java
class K {
    K()      { this(3); System.out.println("bos"); }
    K(int n) { System.out.println("sayi=" + n); }
}
// new K();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
sayi=3
bos
```
`this(3)` önce çalışır, sonra kalan satır.
</details>

**5. Soru**
```java
abstract class Hayvan {
    abstract String ses();
    void konus(){ System.out.println(ses()); }
}
class Kedi extends Hayvan { @Override String ses(){ return "Miyav"; } }
// 1) Hayvan h = new Kedi();  h.konus();  -->  Çıktı?
// 2) Hayvan h = new Hayvan();  yazılabilir mi?
```
<details><summary>Cevap</summary>

(1) `Miyav` — nesne `Kedi`, override edilen `ses()` çalışır (polimorfizm).
(2) **Hayır** — `Hayvan` abstract, `new Hayvan()` derlenmez.
</details>

**6. Soru**
```java
ArrayList<Integer> l = new ArrayList<>();
l.add(1); l.add(2); l.add(4, 9);
// Çıktı / sonuç?
```
<details><summary>Cevap</summary>

**IndexOutOfBoundsException** (Index: 4, Size: 2). `add(4,9)` için index ≤ boyut(2) olmalı; 4 > 2 → hata.
</details>

**7. Soru** (boşlukları doldur)
```java
abstract class Calisan {
    String ad;
    Calisan(String ad){ this.ad = ad; }
    abstract double maas();
}
class Muhendis __(1)__ Calisan {
    Muhendis(String ad){ __(2)__(ad); }
    __(3)__
    double maas(){ return 5000; }
}
```
<details><summary>Cevap</summary>

(1) `extends`  (2) `super`  (3) `@Override`
</details>

**8. Soru**
```java
class Arac { Arac(String s){ System.out.println("Arac: " + s); } }
class Araba extends Arac { Araba(){ super("4 teker"); System.out.println("Araba"); } }
// new Araba();  -->  Çıktı?
```
<details><summary>Cevap</summary>

```
Arac: 4 teker
Araba
```
`super("4 teker")` önce ata constructor'ını çalıştırır.
</details>

**9. Soru**
```java
class P { static int s = 0; int i = 0; void art(){ s++; i++; } }
P a = new P();  P b = new P();
a.art(); a.art(); b.art();
System.out.println(a.s + " " + a.i + " " + b.s + " " + b.i);   // ?
```
<details><summary>Cevap</summary>

`3 2 3 1`. `s` static (paylaşımlı): 2+1=3 (a.s=b.s=3). `i` her nesneye ayrı: a.i=2, b.i=1.
</details>

**10. Soru** (zor — private override edilmez!)
```java
class A { private void g(){ System.out.println("A.g"); }  void f(){ g(); } }
class B extends A { void g(){ System.out.println("B.g"); } }
// new B().f();  -->  Çıktı?
```
<details><summary>Cevap</summary>

`A.g`. `private` metotlar **override edilmez**. `f()` içindeki `g()` derleme anında `A.g`'ye bağlanır; `B.g` bambaşka bir metottur. ← klasik tuzak!
</details>
