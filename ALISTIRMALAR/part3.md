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

---

> ℹ️ Part 3'te 5 soru var (toplam 25). Yeni partiler `part4.md`, `part5.md` ... olarak 10'arlı eklenir.
