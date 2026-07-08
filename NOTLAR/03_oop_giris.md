# Bölüm 3: Nesne Yönelimli Programlamaya (OOP) Giriş
*(Sınıf, nesne ve sınıf üyelerinin temelleri)*

## 3.1) Sınıf Yapısına Genel Bakış

| Yapı | Anahtar kelime | Hatırla |
|------|----------------|---------|
| **enum** | `enum Tur { FILM, MUZIK, DIZI }` | Sabit değer listesi. **Asla `new` ile nesne üretilemez!** |
| **interface** | `interface Z { String getK(); }` | Sadece imza, gövde yok. **`new` ile nesne üretilemez!** |
| **exception** | `class Y extends Exception` | `throw new Y("mesaj")` + `try/catch` |
| **üst sınıf** | `class Medya implements Z` | `private ad`, `protected tc/ucret` + getter'lar |
| **koleksiyon** | `ArrayList<Medya> liste` | `liste.add(...)`, `for (Medya m : liste)` |

**Erişim belirleyiciler:**
- `private` → sadece kendi sınıfı (dışarı **getter** ile)
- `protected` → kendi sınıfı + **alt sınıflar**
- `public` → herkes

---

## 3.2) this(...) — Aynı Sınıfta Constructor Zinciri

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

## 3.3) static Değişkenler (Paylaşımlı Tek Kopya)

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

## 3.4) final Anahtar Kelimesi

```java
final int MAX_HIZ = 120;
// MAX_HIZ = 130;  // ❌ HATA! final değişken değiştirilemez.

final class GuvenliSinif { /* ... */ }
// class AltSinif extends GuvenliSinif { } // ❌ HATA! final sınıf miras alınamaz.

class Ata {
    final void yazdir() { /* ... */ }
}
class Cocuk extends Ata {
    // void yazdir() { } // ❌ HATA! final metot override edilemez.
}
```

**Tuzak (Referans Tiplerinde final):**
`final` bir `ArrayList` oluşturduğunda, listenin **içindeki** elemanları değiştirebilirsin (ekleme/çıkarma yapabilirsin). Sadece listeyi `new` ile başka bir listeye eşitleyemezsin.

```java
final ArrayList<String> liste = new ArrayList<>();
liste.add("Java"); // ✅ Çalışır, içi değişebilir.
// liste = new ArrayList<>(); // ❌ HATA! Yeni referans atanamaz.
```

> 🔑 **Ezber:** "final değişkende sabittir, metotta ezilemez, sınıfta kısırdır (miras vermez)."
