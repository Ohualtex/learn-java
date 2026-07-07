# 📘 Java OOP — Notlar (Fihrist & Özet)

Bu klasör, Java OOP öğrenme sürecindeki temel kavramları bölümler halinde barındırır.
Çalışan tam örnekler için → [`../ORNEKLER/`](../ORNEKLER/)
Alıştırmalar için → [`../ALISTIRMALAR/`](../ALISTIRMALAR/)

---

## 🗂️ Konu Başlıkları (Detaylı Anlatımlar)

0. [Java 101 (Programlamaya Giriş)](00_java_101_temeller.md)
1. [Akış Kontrolü, Diziler ve String](01_akis_kontrolu_diziler_string.md)
2. [Bellek, Veri Tipleri ve Referanslar](02_bellek_ve_veri_tipleri.md)
3. [Nesne Yönelimli Programlamaya (OOP) Giriş](03_oop_giris.md)
4. [İleri Seviye OOP (Kalıtım ve Polimorfizm)](04_kalitim_ve_polimorfizm.md)
5. [Koleksiyonlar (Gelişmiş Veri Yapıları)](05_koleksiyonlar.md)
6. [Hata Yönetimi (Exceptions)](06_hata_yonetimi.md)

---

## 🔑 Hızlı Tekrar: Altın Ezber Cümleleri (Kopya Kağıdı)

Sınav veya mülakat öncesi hızlıca göz gezdirmek için her bölümün en önemli kuralları:

- **Yazdırma ve Kaçış:** "`print` yan yana yazar, `println` yazıp alt satıra geçer. `\n` yeni satır, `\t` sekme (tab) demektir."
- **Operatörler:** "`=` atama yapar, `==` eşit mi diye sorar. `%` (mod) kalanı bulur, çift-tek sayı kontrolünde hayat kurtarır."
- **if-else:** "if'in sonuna noktalı virgül konmaz. Zincirdeki ilk doğru şart çalışır, gerisi atlanır."
- **Scanner:** "Sayıdan sonra metin (`nextLine`) okuyacaksan, araya bir boş `nextLine()` atıp çöpü temizle."
- **Metotlar:** "`void` iş yapar susar, `return` değeri hesaplar ve sana fırlatır."
- **Akış Kontrolü:** "switch'te break unutma, düşer." "do-while önce çalışır, sonra sorar; while önce sorar, sonra çalışır."
- **Diziler:** "dizide `.length` özelliktir (parantezsiz); `ArrayList`'te `.size()` metottur (parantezli)."
- **String:** "String değişmez, StringBuilder değişir." `==` referans karşılaştırır, `.equals()` içerik karşılaştırır.
- **Parametre Gönderimi:** "İlkel tipte (int) kopyayı verirsin, orijinal değişmez. Referans tipte (nesne) kumandayı verirsin, televizyon (orijinal) değişir."
- **Casting:** "(int) kesme yapar, yuvarlama yapmaz." "Wrapper karşılaştırmasında `==` değil `.equals()` kullan."
- **Kıyaslama:** "String'de equals içerik kıyaslar çünkü zaten ezilmiştir. Kendi sınıfında içeriği kıyaslamak istiyorsan, equals'ı kendin ezmelisin."
- **Constructor (Yapıcılar):** "`this(...)` = kardeş constructor, `super(...)` = baba constructor." "En eski ata önce çalışır." (yukarıdan aşağıya)
- **static & final:** "static = nesneye değil sınıfa ait, herkes aynı kopyayı paylaşır." "final değişkende sabittir, metotta ezilemez, sınıfta kısırdır (miras vermez)."
- **Soyutlama (OOP):** abstract = "yarım sınıf, nesnesi olmaz". override = "ata metodu eziyorum". interface = "söz veriyorum bu metotları yazacağım". private = "kimse görmesin".
- **Koleksiyonlar:** "Çıktıyı izlerken: her satırdan sonra listeyi `[ ]` çiz, her index'i kontrol et. Taşma varsa Exception." "ArrayList index ile, HashMap anahtar (key) ile çalışır. Anahtar yoksa null döner."
- **Hatalar (Exceptions):** "finally her zaman çalışır." "Checked exception'ı görmezden gelemezsin, derleyici seni durdurur."
