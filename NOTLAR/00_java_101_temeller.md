# Bölüm 0: Java 101 (Programlamaya Giriş)
*(Nesnelere ve döngülere geçmeden önce bilinmesi gereken en temel kavramlar)*

## 0.1) Değişkenler, Yazdırma ve Kaçış Karakterleri (Escape Sequences)

```java
int yas = 25;
double maas = 15000.50;
boolean ogrenciMi = true;
char harf = 'A';

// Yazdırma (print vs println)
System.out.print("Yanyana yazar");
System.out.println("Alt satira gecer");

// Kaçış Karakterleri (\n, \t, vb.)
System.out.println("Merhaba\nJava"); // \n: Alt satıra iner (New line)
System.out.println("Ad\tSoyad");      // \t: Bir tab (sekme) boşluk bırakır
System.out.println("\"Java\" harika"); // \": Çift tırnak karakterini yazdırır
System.out.println("C:\\Kullanicilar"); // \\: Tek bir ters slash yazdırır
```

**Tuzak:** `System.out.print` yazıp alt satıra geçmesini beklemek. Değişken tanımlarken `char` için tek tırnak (`'A'`), `String` için çift tırnak (`"A"`) kullanılması gerektiğinin unutulması.

> 🔑 **Ezber:** "`print` yan yana yazar, `println` yazıp alt satıra geçer. `\n` yeni satır, `\t` sekme (tab) demektir."

---

## 0.2) Operatörler (Matematiksel ve Mantıksal)

```java
// Mod Alma (%) - Bir sayının bölümünden kalanı verir
int kalan = 10 % 3; // 1 (10'un 3'e bölümünden kalan 1'dir)

// Mantıksal Operatörler (&&, ||, !)
boolean a = true;
boolean b = false;

System.out.println(a && b); // false (VE: İkisi de true olmalı)
System.out.println(a || b); // true  (VEYA: Biri true olsa yeter)
System.out.println(!a);     // false (DEĞİL: Tersine çevirir)

// Karşılaştırma
System.out.println(5 == 5); // true (Eşit mi?)
System.out.println(5 != 4); // true (Eşit DEĞİL mi?)
```

**Tuzak:** Atama operatörü (`=`) ile eşitlik kontrolü (`==`) operatörünü birbirine karıştırmak. Örn: `if(x = 5)` yazarsanız hata alırsınız, `if(x == 5)` olmalıdır.

> 🔑 **Ezber:** "`=` atama yapar, `==` eşit mi diye sorar. `%` (mod) kalanı bulur, çift-tek sayı kontrolünde hayat kurtarır."

---

## 0.3) Temel Şart Blokları (if - else if - else)

```java
int not = 75;

if (not >= 90) {
    System.out.println("AA");
} else if (not >= 70) {
    System.out.println("BB"); // Burası çalışır
} else {
    System.out.println("Kaldiniz");
}
```

**Tuzak:** `if` şartlarından sonra noktalı virgül (`;`) koymak. `if (not > 50); { ... }` yazarsanız, `if` bloğu anında biter ve süslü parantez içindeki kod şarttan bağımsız her zaman çalışır.

> 🔑 **Ezber:** "if'in sonuna noktalı virgül konmaz. Zincirdeki ilk doğru şart çalışır, gerisi atlanır."

---

## 0.4) Kullanıcıdan Veri Alma (Scanner Sınıfı)

```java
import java.util.Scanner;

Scanner okuyucu = new Scanner(System.in);

System.out.print("Yasinizi girin: ");
int yas = okuyucu.nextInt();

System.out.print("Adinizi girin: ");
// okuyucu.nextLine(); // HATA ÇÖZÜMÜ İÇİN BURADA BOŞ BİR NEXTLINE ÇAĞRILMALI!
String ad = okuyucu.nextLine(); 

System.out.println("Merhaba " + ad + ", yas " + yas);
```

**Zor Tuzak (Scanner Bug'ı):** `nextInt()` veya `nextDouble()` ile sayı aldıktan hemen sonra `nextLine()` ile metin almaya çalışırsanız, Java sayının sonundaki "Enter" tuşunu okuyup `nextLine()`'ı atlar (sanki kullanıcı boşluk girmiş gibi algılar).
**Çözüm:** Sayı okuduktan sonra araya boş bir `okuyucu.nextLine();` koyarak "Enter" karakterini yutmasını sağlamak.

> 🔑 **Ezber:** "Sayıdan sonra metin (`nextLine`) okuyacaksan, araya bir boş `nextLine()` atıp çöpü temizle."

---

## 0.5) Metotların Temeli (void vs return)

```java
class HesapMakinesi {
    // 1. Geriye değer DÖNDÜRMEYEN metot (void)
    void ekranaYazdir(String mesaj) {
        System.out.println("Sonuc: " + mesaj);
        // return kullanılamaz (veya tek başına 'return;' yazılır)
    }

    // 2. Geriye değer DÖNDÜREN metot (int, double, String vb.)
    int topla(int a, int b) {
        int sonuc = a + b;
        return sonuc; // Değeri çağrıldığı yere fırlatır
    }
}

// ... main metodunda:
HesapMakinesi hm = new HesapMakinesi();
hm.ekranaYazdir("Hesap başliyor"); // Sadece ekrana yazar, iş biter.

int x = hm.topla(5, 3); // 8 değerini döndürür ve x'e atar.
```

**Tuzak:** Geriye değer döndüren (`int`, `String` vb.) bir metotta `return` kelimesini unutmak derleme hatasına yol açar. Ayrıca `void` metotta dönen sonucu bir değişkene atamaya çalışmak (örn: `int a = hm.ekranaYazdir("..");`) hatadır.

> 🔑 **Ezber:** "`void` iş yapar susar, `return` değeri hesaplar ve sana fırlatır."
