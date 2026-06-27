# 📘 Java OOP — Notlar

Temel nesne yönelimli programlama konuları: her başlıkta **kavram + tuzak + ezber cümle**.
Alıştırmalar için → [`ALISTIRMALAR/`](ALISTIRMALAR/) (Part 1, Part 2, ...)

---

## 1) Sınıf Yapısı

Örnek: `Main.java`

| Yapı | Anahtar kelime | Hatırla |
|------|----------------|---------|
| **enum** | `enum Tur { FILM, MUZIK, DIZI }` | Sabit değer listesi |
| **interface** | `interface Z { String getK(); }` | Sadece imza, gövde yok; `implements` eden doldurur |
| **exception** | `class Y extends Exception` | `throw new Y("mesaj")` + `try/catch` |
| **üst sınıf** | `class Medya implements Z` | `private ad`, `protected tc/ucret` + getter'lar |
| **koleksiyon** | `ArrayList<Medya> liste` | `liste.add(...)`, `for (Medya m : liste)` |

**Erişim belirleyiciler:**
- `private` → sadece kendi sınıfı (dışarı **getter** ile)
- `protected` → kendi sınıfı + **alt sınıflar**
- `public` → herkes

---

## 2) ArrayList Metotları (IndexOutOfBounds tuzağı)

```java
ArrayList<Integer> l = new ArrayList<>();
l.add(3);        // [3]            -> sona ekler
l.add(8);        // [3, 8]
l.add(4);        // [3, 8, 4]
l.add(5, 21);    // ❌ HATA! index 5 > boyut 3
```

| Metot | Anlamı | Index kuralı |
|-------|--------|--------------|
| `add(x)` | sona ekler | — |
| `add(i, x)` | i. indekse araya sokar | **i ≤ boyut** |
| `set(i, x)` | i. indekstekini değiştirir | **i < boyut** |
| `remove(i)` | i. indekstekini siler | **i < boyut** |

### ⚠️ İki büyük tuzak
1. **`add(i, x)` taşması:** index, boyuttan büyükse → `IndexOutOfBoundsException`.
2. **`remove(3)` değeri değil İNDEKSİ siler!**
   - `remove(int index)` → indeksi siler ← sayı yazınca bu çalışır
   - `remove(Object o)` → değeri siler (`remove(Integer.valueOf(3))`)

> 🔑 **Çıktıyı izlerken:** her satırdan sonra listeyi `[ ]` çiz, her index'i kontrol et. Taşma varsa → Exception.

---

## 3) Kalıtım & Constructor Sırası (super)

```java
class A { A(){ System.out.println("A"); } }
class B extends A { B(){ System.out.println("B"); } }
class C extends B { C(){ System.out.println("C"); } }
// new C()  -->  Çıktı:  A  B  C
```

**Neden A B C?** Her constructor'ın ilk satırına Java gizli `super()` koyar.
Çağrı yukarı gider (C→B→A), çalışma yukarıdan aşağı olur (A→B→C).

> 🔑 **Ezber: "En eski ata önce çalışır." (yukarıdan aşağıya)**

**Tuzaklar:**
- `System.out.println("A");` doğrudan sınıf gövdesine yazılamaz → constructor `A(){...}` içine koy.
- Bir dosyada sadece **1 tane `public class`** olabilir.

---

## 4) static Değişkenler (paylaşımlı tek kopya)

```java
class Sayac {
    static int x = 0;
    void K(int u){ x = x + u; }
    void L(int v){ x = x + v; }
}
// main:
Sayac a = new Sayac();
Sayac b = new Sayac();
a.K(5);   // x = 5
b.L(3);   // x = 8   (AYNI x!)
System.out.println(a.x + " - " + b.x);  // 8 - 8
```

| | `static int x` | `int x` (static'siz) |
|---|---|---|
| Kopya sayısı | Sınıf için **1 tane** | Her nesneye **ayrı** |
| Sonuç | `8 - 8` | `5 - 3` |

> 🔑 **Ezber: "static = nesneye değil sınıfa ait, herkes aynı kopyayı paylaşır."**

---

## 5) this(...) — Aynı Sınıfta Constructor Zinciri

```java
class A {
    int x;
    A()      { this(5); System.out.println("Parametresiz"); }  // A(int)'i çağırır
    A(int x) { this.x = x; System.out.println("Parametreli x=" + x); }
}
// new A()  -->  Çıktı:
// Parametreli x=5
// Parametresiz
```

**Neden bu sıra?** `this(5)` önce çalışır (çağrılan constructor biter), sonra kalan satırlar.

| Kural | Açıklama |
|-------|----------|
| `this(...)` | **aynı sınıfın** başka constructor'ı |
| `super(...)` | **ata sınıfın** constructor'ı |
| Konum | İkisi de **ilk satır** olmalı (aynı anda kullanılamaz) |
| `this.x` | Bu constructor çağrısı DEĞİL; nesnenin alanına erişim |

> 🔑 **Ezber: `this(...)` = kardeş constructor, `super(...)` = baba constructor.**

---

## 6) inheritance + abstract + interface + override + private (Polimorfizm)

Hepsi tek örnekte:

```java
interface Z { String selam(); }                 // metotlar otomatik public abstract

abstract class Medya implements Z {             // ABSTRACT: nesnesi OLUŞTURULAMAZ
    private String ad;                          // private: alt sınıf bile göremez
    protected int ucret;                        // protected: alt sınıf görebilir
    Medya(String ad, int ucret){ this.ad=ad; this.ucret=ucret; }
    public String getAd(){ return ad; }         // private'a tek erişim: getter
    abstract void oynat();                       // SOYUT metot: alt sınıf doldurmak ZORUNDA
    void bilgi(){ System.out.println(getAd()+" - "+ucret+" TL"); }  // normal metot
}

class Film extends Medya {                      // INHERITANCE (extends)
    Film(String ad, int ucret){ super(ad, ucret); }
    @Override void oynat(){ System.out.println(getAd()+" oynatiliyor"); }   // OVERRIDE
    @Override public String selam(){ return "Film selam!"; }                // interface doldur
}

// main:
Medya m = new Film("Inception", 50);   // POLIMORFIZM: tip Medya, nesne Film
m.bilgi();   m.oynat();   m.selam();
/* Çıktı:
   Inception - 50 TL
   Inception oynatiliyor
   Film selam!                          */
```

### a) `abstract` (soyut)
- `abstract class` → **nesnesi oluşturulamaz** (`new Medya()` ❌). Sadece miras için.
- `abstract void oynat();` → **gövdesiz** metot; alt sınıf **doldurmak zorunda** (yoksa o sınıf da abstract olmalı).
- Abstract sınıfta **hem soyut hem normal** metot olabilir (interface'ten farkı).

### b) `@Override` (ezme/geçersiz kılma)
- Ata sınıftan gelen metodu alt sınıfta **yeniden yazmak**.
- `@Override` etiketi zorunlu değil ama **yaz** → imza yanlışsa derleyici uyarır.
- İmza **birebir aynı** olmalı (isim + parametreler).
- `super.oynat()` → ezilen ata metoduna yine de erişebilirsin.

### c) `private`
- Sadece **kendi sınıfı** erişir. Alt sınıf bile **göremez** ve **override edemez**.
- Dışarıdan erişim için **getter/setter** yazılır.

### d) `interface` vs `abstract class` (ÖNEMLİ)

| | interface | abstract class |
|---|-----------|----------------|
| Metot gövdesi | Yok (normalde) | Hem var hem yok olabilir |
| Alan | `public static final` (sabit) | Normal alan olabilir |
| Kalıtım | `implements` ile **birden fazla** | `extends` ile **tek** |
| Constructor | ❌ yok | ✅ var |
| Ne zaman? | "Yapabilir" yeteneği (örn. `Z`) | "Bir tür ...dir" (örn. `Medya`) |

### e) `extends` vs `implements`
- `class B extends A` → **tek** sınıftan miras.
- `class B implements X, Y` → **çok** interface uygulanabilir.
- Birlikte: `class Film extends Medya implements Z { }`

> 🔑 **Ezber:** abstract = "yarım sınıf, nesnesi olmaz". override = "ata metodu eziyorum". interface = "söz veriyorum bu metotları yazacağım". private = "kimse görmesin".
