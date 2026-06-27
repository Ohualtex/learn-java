# ✍️ ALIŞTIRMALAR — Part 3

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**Soru 1.** (harman: kalıtım + this() + super + sıra) — zor
```java
class A {
    A()      { System.out.println("A()"); }
    A(int x) { System.out.println("A(int)"); }
}
class B extends A {
    B()      { this(5);   System.out.println("B()"); }
    B(int x) { super(x);  System.out.println("B(int)"); }
}
// new B();  -->  Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
A(int)
B(int)
B()
```
`new B()` → `B()` → `this(5)` → `B(int)` → `super(5)` → `A(int)` yaz → dön `B(int)` yaz → dön `B()` yaz.
</details>

**Soru 2.** (derlenir mi?)
```java
interface Ucan { void uc(); }
class Kus implements Ucan { }   // uc() doldurulmadı
// DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `Kus`, `Ucan`'ı `implements` edip `uc()`'u doldurmadı. Çözüm: `uc()`'u yaz **ya da** `Kus`'u `abstract` yap.
</details>

**Soru 3.** (boşlukları doldur)
```java
class Kutu {
    int en, boy;
    Kutu(){ __(1)__(1, 1); }
    Kutu(int en, int boy){ __(2)__.en = en; this.boy = boy; }
}
```
<details><summary>Cevap</summary>

(1) `this`  (2) `this`
</details>

**Soru 4.** (boşlukları doldur)
```java
class Ogrenci {
    __(1)__ int sayac = 0;     // tüm nesneler aynı kopyayı paylaşsın
    Ogrenci(){ sayac__(2)__; } // her nesnede 1 artsın
}
```
<details><summary>Cevap</summary>

(1) `static`  (2) `++`
</details>

**Soru 5.** (harman: interface + polimorfizm + ArrayList)
```java
interface Sesli { String ses(); }
class Kopek implements Sesli { public String ses(){ return "Hav"; } }
class Kus   implements Sesli { public String ses(){ return "Cik"; } }

ArrayList<Sesli> l = new ArrayList<>();
l.add(new Kopek()); l.add(new Kus()); l.add(new Kopek());
for (Sesli s : l) System.out.print(s.ses() + " ");
// Çıktı?
```
<details><summary>Cevap</summary>

`Hav Cik Hav ` — her nesnenin kendi `ses()`'i çalışır (polimorfizm); liste sırası Kopek, Kus, Kopek.
</details>

**Soru 6.**
```java
ArrayList<Integer> l = new ArrayList<>();
l.add(10); l.add(20); l.add(30);
l.set(1, 99); l.add(1, 7);
System.out.println(l.get(2) + " boyut=" + l.size());   // ?
```
<details><summary>Cevap</summary>

`99 boyut=4`. `[10,20,30]`→set(1,99)`[10,99,30]`→add(1,7)`[10,7,99,30]`→ `get(2)`=99, boyut=4.
</details>

**Soru 7.**
```java
class Ust { int deger(){ return 10; } }
class Alt extends Ust { @Override int deger(){ return super.deger() + 5; } }
Ust u = new Alt();
System.out.println(u.deger());   // ?
```
<details><summary>Cevap</summary>

`15`. Nesne `Alt`, override edilen `deger()` çalışır; `super.deger()`=10, +5 → **15** (polimorfizm + super).
</details>

**Soru 8.**
```java
class Sayac { static int n = 0; static void artir(){ n++; } }
Sayac.artir(); Sayac.artir();
Sayac s = new Sayac(); s.artir();
System.out.println(Sayac.n);   // ?
```
<details><summary>Cevap</summary>

`3`. `artir()` static; 3 kez çağrıldı (nesneden çağrılması da aynı static `n`'i artırır) → **3**.
</details>

**Soru 9.** (zor — this() zinciri)
```java
class A {
    A()             { this(1);    System.out.println("A()"); }
    A(int x)        { this(x, 2); System.out.println("A(int)"); }
    A(int x, int y) { System.out.println("A(int,int) " + (x + y)); }
}
// new A();  -->  Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
A(int,int) 3
A(int)
A()
```
`A()`→`this(1)`→`A(int)`→`this(1,2)`→`A(int,int)` (1+2=3) yaz → dön `A(int)` yaz → dön `A()` yaz.
</details>

**Soru 10.** (derlenir mi?)
```java
abstract class Sekil { abstract void ciz(); }
class Kare extends Sekil { }   // ciz() doldurulmadı
// new Kare();  -->  DERLENİR Mİ?
```
<details><summary>Cevap</summary>

**DERLENMEZ.** `Kare`, abstract `Sekil`'den türüyor ama soyut `ciz()`'i doldurmadı. Çözüm: `ciz()`'i yaz **ya da** `Kare`'yi `abstract` yap.
</details>
