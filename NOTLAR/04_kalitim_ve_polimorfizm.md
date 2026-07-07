# Bölüm 4: İleri Seviye OOP (Kalıtım ve Polimorfizm)
*(Sınıflar arası ilişkiler)*

## 4.1) Kalıtım & Constructor Sırası (super)

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

## 4.2) Soyutlama ve Çok Biçimlilik (abstract + interface + override + private)

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
