# ✍️ ALIŞTIRMALAR — Part 5

Konular karışık. Her sorunun cevabı **hemen altında gizli** — önce kendin çöz, sonra "Cevap"a tıkla.
Konu anlatımı için → [`NOTLAR.md`](../NOTLAR.md)

---

**1. Soru**
```java
String a = "merhaba";
String b = "merhaba";
String c = new String("merhaba");
System.out.println(a == b);
System.out.println(a == c);
System.out.println(a.equals(c));
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
true
false
true
```
`a == b` → ikisi de string havuzundaki aynı nesne = true; `a == c` → `new String` ayrı nesne = false; `a.equals(c)` → içerik aynı = true. Kural: içerik `equals()` ile, referans `==` ile karşılaştırılır.
</details>

**2. Soru**
```java
int[] d = new int[3];
boolean[] b = new boolean[2];
String[] s = new String[2];
System.out.println(d[0] + " " + b[0] + " " + s[0]);
// Çıktı?
```
<details><summary>Cevap</summary>

`0 false null`. Dizi oluşturulunca elemanlar otomatik varsayılan değer alır: `int`→0, `boolean`→false, nesne (`String`)→null.
</details>

**3. Soru**
```java
int x = 5;
System.out.println(x++);
System.out.println(x);
System.out.println(++x);
// Çıktı (3 satır)?
```
<details><summary>Cevap</summary>

```
5
6
7
```
`x++` önce değeri (5) kullanır, sonra artırır → x=6; sonra x=6 yazılır; `++x` önce artırır (7), sonra kullanır.
</details>
