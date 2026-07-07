# Bölüm 2: Bellek, Veri Tipleri ve Referanslar
*(Nesnelere geçmeden önce referans mantığı ve bellek oturmalı)*

## 2.1) Bellek Yönetimi (Stack vs Heap) ve Referans Mantığı

Java'da bellek ikiye ayrılır:
1. **Stack (Yığın):** İlkel tipler (`int`, `double`) ve nesnelerin **referansları** (kumandalar) burada tutulur.
2. **Heap (Öbek):** `new` kelimesiyle oluşturulan tüm **nesneler** (televizyonlar) burada tutulur.

**Tuzak — Metotlara Parametre Göndermek (Pass-by-Value):**

```java
class Test {
    static void ilkelDegistir(int a) {
        a = 99; // Sadece kopya değişti!
    }

    static void referansDegistir(ArrayList<Integer> liste) {
        liste.add(99); // Orijinal nesneye etki eder!
    }

    public static void main(String[] args) {
        int sayi = 5;
        ilkelDegistir(sayi);
        System.out.println(sayi); // 5 -> DEĞİŞMEDİ! İlkel tiplerin değeri kopyalanır.

        ArrayList<Integer> l = new ArrayList<>();
        referansDegistir(l);
        System.out.println(l); // [99] -> DEĞİŞTİ! Nesnenin adresi kopyalandığı için aynı orijinal nesne değiştirildi.
    }
}
```

> 🔑 **Ezber:** "İlkel tipte (int) kopyayı verirsin, orijinal değişmez. Referans tipte (nesne) kumandayı verirsin, televizyon (orijinal) değişir."

---

## 2.2) Tip Dönüşümü (Casting) ve Wrapper/Autoboxing

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

## 2.3) equals() Metodunu Ezmek (Override)

İki nesneyi `==` veya varsayılan `.equals()` ile karşılaştırdığınızda Java, nesnelerin **bellek adreslerine (referanslarına)** bakar. İçerikleri aynı olsa bile `false` döner. İçeriği kıyaslamak için `equals` metodunu ezmeliyiz.

```java
class Urun {
    String ad;
    int fiyat;

    Urun(String ad, int fiyat) {
        this.ad = ad;
        this.fiyat = fiyat;
    }

    @Override
    public boolean equals(Object o) {
        // 1. Kendi adresiyle aynıysa zaten true
        if (this == o) return true;
        // 2. Gelen nesne null ise veya tipler uyuşmuyorsa false
        if (o == null || getClass() != o.getClass()) return false;
        
        // 3. Güvenli şekilde tip dönüşümü (downcast) yap ve alanları kıyasla
        Urun diger = (Urun) o;
        return this.fiyat == diger.fiyat && this.ad.equals(diger.ad);
    }
}

Urun u1 = new Urun("Telefon", 5000);
Urun u2 = new Urun("Telefon", 5000);

System.out.println(u1 == u2);      // false (Farklı bellek adresleri)
System.out.println(u1.equals(u2)); // true  (equals ezildiği için artık içerik kıyaslandı)
```

> 🔑 **Ezber:** "String'de equals içerik kıyaslar çünkü zaten ezilmiştir. Kendi sınıfında içeriği kıyaslamak istiyorsan, equals'ı kendin ezmelisin."
