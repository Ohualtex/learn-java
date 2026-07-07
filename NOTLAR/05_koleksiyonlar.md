# Bölüm 5: Koleksiyonlar (Gelişmiş Veri Yapıları)
*(Dizilerin yetmediği yerde dinamik yapılar)*

## 5.1) ArrayList Metotları ve Taşma Tuzakları

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

## 5.2) HashMap (Anahtar - Değer Çiftleri)

```java
HashMap<String, Integer> notlar = new HashMap<>();
notlar.put("Ali", 85);
notlar.put("Ayse", 92);
notlar.put("Ali", 90); // Ali'nin değerini GÜNCELLER, yeni eklemez (anahtarlar eşsizdir).

System.out.println(notlar.get("Ayse")); // 92
System.out.println(notlar.get("Veli")); // null (olmayan anahtar)

// Döngü ile gezmek:
for (String isim : notlar.keySet()) {
    System.out.println(isim + ": " + notlar.get(isim));
}
```

| Metot | Anlamı |
|-------|--------|
| `put(K, V)` | Anahtar-Değer ekler (anahtar varsa değerini günceller) |
| `get(K)` | Anahtara karşılık gelen değeri getirir (yoksa `null` döner) |
| `containsKey(K)` | Anahtar var mı diye bakar (`true`/`false`) |
| `remove(K)` | Anahtarı ve karşılığındaki değerini siler |

> 🔑 **Ezber:** "ArrayList index ile, HashMap anahtar (key) ile çalışır. Anahtar yoksa null döner."
