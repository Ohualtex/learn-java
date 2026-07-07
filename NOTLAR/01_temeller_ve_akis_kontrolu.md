# Bölüm 1: Temeller ve Akış Kontrolü
*(Önce Java'nın temel yapıtaşları anlaşılmalı)*

## 1.1) Kontrol Akışı (switch, döngüler, ternary)

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

---

## 1.2) Temel Veri Yapıları: Diziler (Array)

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

## 1.3) String Derinlemesine

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
