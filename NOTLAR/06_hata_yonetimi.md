# Bölüm 6: Hata Yönetimi

## 6.1) Exception Hiyerarşisi

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
