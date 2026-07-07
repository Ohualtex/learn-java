# 📘 Java OOP — Notlar

Temel nesne yönelimli programlama konuları: her başlıkta **kavram + tuzak + ezber cümle**.
Alıştırmalar için → [`ALISTIRMALAR/`](ALISTIRMALAR/) (Part 1, Part 2, ...)
Çalışan tam örnekler için → [`ORNEKLER/`](ORNEKLER/)

---

## 1) Sınıf Yapısı

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
class A {
    A() {
        System.out.println("A");
    }
}

class B extends A {
    B() {
        System.out.println("B");
    }
}

class C extends B {
    C() {
        System.out.println("C");
    }
}
// new C();  -->  Çıktı:  A  B  C
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

    void arttirX(int u) {
        x = x + u;
    }

    void arttirY(int v) {
        x = x + v;
    }
}

Sayac a = new Sayac();
Sayac b = new Sayac();
a.arttirX(5);   // x = 5
b.arttirY(3);   // x = 8   (AYNI x!)
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

    A() {
        this(5);   // A(int)'i çağırır
        System.out.println("Parametresiz");
    }

    A(int x) {
        this.x = x;
        System.out.println("Parametreli x=" + x);
    }
}
// new A();  -->  Çıktı:
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
interface Z {
    String selam();   // metotlar otomatik public abstract
}

abstract class Medya implements Z {   // ABSTRACT: nesnesi OLUŞTURULAMAZ
    private String ad;                // private: alt sınıf bile göremez
    protected int ucret;              // protected: alt sınıf görebilir

    Medya(String ad, int ucret) {
        this.ad = ad;
        this.ucret = ucret;
    }

    public String getAd() {           // private'a tek erişim: getter
        return ad;
    }

    abstract void oynat();            // SOYUT metot: alt sınıf doldurmak ZORUNDA

    void bilgi() {                    // normal metot
        System.out.println(getAd() + " - " + ucret + " TL");
    }
}

class Film extends Medya {            // INHERITANCE (extends)
    Film(String ad, int ucret) {
        super(ad, ucret);
    }

    @Override
    void oynat() {                    // OVERRIDE
        System.out.println(getAd() + " oynatiliyor");
    }

    @Override
    public String selam() {           // interface doldur
        return "Film selam!";
    }
}

Medya m = new Film("Inception", 50);   // POLIMORFIZM: tip Medya, nesne Film
m.bilgi();
m.oynat();
System.out.println(m.selam());
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

---

## 7) Diziler (Array)

```java
int[] sayilar = new int[3];
boolean[] bayraklar = new boolean[2];
String[] isimler = new String[2];
System.out.println(sayilar[0] + " " + bayraklar[0] + " " + isimler[0]);
// Çıktı: 0 false null

int[] dizi = {10, 20, 30};
System.out.println(dizi.length);   // 3  (özellik, parantezsiz)
System.out.println(dizi[1]);       // 20 (indeks 0'dan başlar)

int[][] matris = {{1, 2}, {3, 4}}; // 2 boyutlu dizi
System.out.println(matris[1][0]);  // 3  -> matris[satir][sutun]
```

**Varsayılan değerler** (dizi oluşturunca otomatik atanır):

| Tip | Varsayılan |
|-----|-----------|
| `int`, `double` vb. sayısal | `0` |
| `boolean` | `false` |
| Nesne (`String` vb.) | `null` |

> 🔑 **Ezber:** dizide `.length` **özelliktir** (parantezsiz); `ArrayList`'te `.size()` **metottur** (parantezli). Karıştırmak derleme hatası verir.

---

## 8) Tip Dönüşümü (Casting) ve Wrapper/Autoboxing

```java
double d = 9.7;
int i = (int) d;
System.out.println(i);   // 9  -> KESER, yuvarlamaz!

Hayvan h = new Kopek();  // upcast: otomatik, Java kendi yapar
Kopek k = (Kopek) h;     // downcast: elle, alt tipe özel metoda erişmek için

Integer a = 100, b = 100;
Integer c = 200, d2 = 200;
System.out.println(a == b);    // true
System.out.println(c == d2);   // false
```

**Casting türleri:**
- `(int) d` → **primitive** dönüşüm (double→int), ondalık **atılır** (yuvarlama yok).
- `Hayvan h = new Kopek()` → **upcast**, otomatik ve güvenli (alt tip her zaman üst tiptir).
- `(Kopek) h` → **downcast**, elle yapılır; yanlış tipte olursa `ClassCastException` fırlar.

**Zor tuzak — Integer önbelleği (cache):** Java, **-128 ile 127** arasındaki `Integer` değerlerini önbellekte tutar ve tekrar kullanır → bu aralıkta `==` beklenmedik şekilde `true` verir. Aralık dışında (`200` gibi) her biri ayrı nesnedir → `==` false. Sayı karşılaştırmasında **her zaman `.equals()`** kullan.

> 🔑 **Ezber:** "(int) kesme yapar, yuvarlama yapmaz." "Wrapper karşılaştırmasında `==` değil `.equals()` kullan."

---

## 9) String Derinlemesine

```java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");
System.out.println(s1 == s2);      // true  -> ikisi de string havuzunda AYNI nesne
System.out.println(s1 == s3);      // false -> new ile AYRI nesne oluşturuldu
System.out.println(s1.equals(s3)); // true  -> içerik aynı

s1.concat(" OOP");
System.out.println(s1);            // "Java" -> DEĞİŞMEDİ! (immutable)

StringBuilder sb = new StringBuilder("Java");
sb.append(" OOP");
System.out.println(sb);            // "Java OOP" -> StringBuilder değiştirilebilir (mutable)
```

**`String` değişmezdir (immutable):** `concat()`, `replace()`, `toUpperCase()` gibi metotlar **yeni bir String döndürür**, orijinali değiştirmez. Sonucu kullanmak için mutlaka bir değişkene ata: `s1 = s1.concat(" OOP");`

**Sık kullanılan metotlar:** `length()`, `charAt(i)`, `substring(basla, bitir)`, `toUpperCase()`, `equals()` / `equalsIgnoreCase()`, `split(",")`, `trim()`, `replace(eski, yeni)`.

> 🔑 **Ezber:** "String değişmez, StringBuilder değişir." `==` referans karşılaştırır, `.equals()` içerik karşılaştırır.

---

## 10) Exception Hiyerarşisi

```java
try {
    int[] arr = new int[3];
    System.out.println(arr[5]);
} catch (ArithmeticException e) {
    System.out.println("aritmetik");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("dizi");
} catch (Exception e) {
    System.out.println("genel");
} finally {
    System.out.println("finally her zaman calisir");
}
// Çıktı:
// dizi
// finally her zaman calisir
```

**Hiyerarşi:** `Throwable` → `Exception` → `RuntimeException` (unchecked) ve diğerleri (checked).

| | Checked (örn. kendi `Exception`'ımız) | Unchecked (`RuntimeException` alt sınıfı) |
|---|---|---|
| Derleyici zorlar mı? | ✅ Evet — `try/catch` ya da `throws` şart | ❌ Hayır |
| Örnek | `class Y extends Exception` | `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException` |

**Kurallar:**
- Birden fazla `catch` varsa, Java yukarıdan aşağı **ilk uyan** bloğu çalıştırır → **en özel** exception **en üstte** olmalı.
- `finally` bloğu hata olsun olmasın **her zaman** çalışır.
- Checked bir exception fırlatan metot, onu ya `try/catch` ile yakalamalı ya da `throws` ile bildirmelidir — yoksa **derlenmez**.

> 🔑 **Ezber:** "finally her zaman çalışır." "Checked exception'ı görmezden gelemezsin, derleyici seni durdurur."

---

## 11) Kontrol Akışı (switch, döngüler, ternary)

```java
int gun = 2;
switch (gun) {
    case 1:
        System.out.println("Pazartesi");
    case 2:
        System.out.println("Sali");
        break;
    default:
        System.out.println("Diger");
}
// Çıktı: Sali   (gun=2 direkt case 2'ye düşer)

int x = 10;
do {
    System.out.println(x);
    x++;
} while (x < 5);
// Çıktı: 10   (do-while gövdesi EN AZ BİR KEZ çalışır, şart sonda kontrol edilir)

String sonuc = (7 % 2 == 0) ? "cift" : "tek";
System.out.println(sonuc);   // tek
```

**Tuzaklar:**
- `switch`'te bir `case`'in sonunda `break` yoksa, bir sonraki `case`'e **düşer (fall-through)** — istemeden birden fazla blok çalışabilir.
- `while` şartı **baştan** kontrol eder (yanlışsa hiç çalışmayabilir); `do-while` şartı **sonda** kontrol eder (en az bir kez çalışır).
- Ternary (`?:`) tek satırlık if-else'in kısayoludur: `şart ? doğruysaDeğer : yanlışsaDeğer`.

> 🔑 **Ezber:** "switch'te break unutma, düşer." "do-while önce çalışır, sonra sorar; while önce sorar, sonra çalışır."
