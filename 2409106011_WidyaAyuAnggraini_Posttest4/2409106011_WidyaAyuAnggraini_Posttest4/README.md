# 🏨 Sistem Manajemen Hotel — Posttest 2

Lanjutan dari Posttest 1, dikembangkan dengan menerapkan konsep **Encapsulation**, semua jenis **Access Modifier**, **Getter/Setter**, dan build tool **Maven** dengan library eksternal.

---

## 📁 Struktur Project

```
Posttest2/
├── pom.xml                          ← Maven build config + dependensi eksternal
├── README.md
└── src/
    └── main/
        └── java/
            └── hotel/
                ├── HotelManagement.java        ← Main class (entry point)
                ├── model/
                │   ├── Tamu.java               ← Kelas tamu biasa
                │   ├── TamuVIP.java            ← Subkelas tamu VIP (extends Tamu)
                │   ├── Kamar.java              ← Kelas kamar hotel
                │   └── Reservasi.java          ← Kelas data reservasi
                ├── service/
                │   ├── TamuService.java        ← Logika CRUD tamu
                │   ├── KamarService.java       ← Logika manajemen kamar
                │   └── ReservasiService.java   ← Logika CRUD reservasi
                └── util/
                    ├── InputHelper.java        ← Input dari keyboard
                    └── AppLogger.java          ← Logging aktivitas (Slf4j via Maven)
```

---

## ✅ Konsep yang Diterapkan

### 1. Encapsulation
Semua field di setiap kelas model bersifat **`private`** — tidak dapat diakses langsung dari luar kelas. Akses hanya melalui method **getter** dan **setter** yang disediakan secara publik.

Contoh di `Tamu.java`:
```java
private int    id;       // tidak bisa diakses langsung
private String nama;
private long   noKtp;
private long   noTelp;

// Akses melalui getter/setter
public String getNama()          { return nama; }
public void   setNama(String n)  { this.nama = validasiNama(n); }
```

Setter bahkan dilengkapi **validasi internal** (method `private`) sehingga data tidak bisa diisi sembarangan dari luar.

---

### 2. Empat Jenis Access Modifier

| Modifier      | Lokasi Contoh                                 | Penjelasan                                              |
|---------------|-----------------------------------------------|---------------------------------------------------------|
| `public`      | Semua getter/setter, constructor, `tampilInfo()` | Dapat diakses dari mana saja                         |
| `private`     | Field `id`, `nama`, `noKtp`, method `validasiNama()` | Hanya bisa diakses dalam kelas yang sama         |
| `protected`   | Field `poinLoyalitas` di `Tamu.java`          | Dapat diakses oleh subkelas `TamuVIP`                   |
| `default`     | Method `formatInfo()` & field `totalTamuDibuat` di `Tamu.java` | Hanya dalam package `hotel.model` |

#### Contoh `protected` — diakses dari subkelas:
```java
// Di Tamu.java
protected int poinLoyalitas;

// Di TamuVIP.java (subkelas)
public void tambahPoinDouble(int poin) {
    this.poinLoyalitas += poin * 2; // akses langsung ke field protected
}
```

#### Contoh `default` (package-private):
```java
// Di Tamu.java — method ini HANYA bisa dipanggil dari dalam package hotel.model
String formatInfo() {
    return String.format("[%d] %s | KTP: %d | ...", id, nama, noKtp, ...);
}
```

---

### 3. Getter & Setter

Setiap field private dilengkapi getter dan setter. Setter tidak sekedar mengisi nilai, tapi juga menjalankan validasi:

```java
public void setNama(String nama) {
    this.nama = validasiNama(nama);  // validasi dijalankan sebelum set
}

private String validasiNama(String nama) {
    if (nama == null || nama.isBlank())
        throw new IllegalArgumentException("Nama tamu tidak boleh kosong.");
    return Character.toUpperCase(nama.trim().charAt(0)) + nama.trim().substring(1);
}
```

---

### 4. Inheritance & Polymorphism (bonus dari encapsulation)

Kelas `TamuVIP` meng-extend `Tamu` dan memanfaatkan field `protected`:

```java
public class TamuVIP extends Tamu {
    private String tingkatVIP;   // private — hanya di TamuVIP
    private double diskonPersen;

    public double hitungHargaSetelahDiskon(double hargaAsli) {
        return hargaAsli * (1 - diskonPersen / 100.0);
    }
}
```

Di `Reservasi.java`, diskon diterapkan secara otomatis menggunakan **pattern matching**:
```java
public double hitungTotalBiaya() {
    double harga = kamar.getHargaPerMalam() * jumlahMalam;
    if (tamu instanceof TamuVIP vip) {
        harga = vip.hitungHargaSetelahDiskon(harga);
    }
    return harga;
}
```

---

## 📦 Maven & Library Eksternal (Poin Plus)

Project ini menggunakan **Apache Maven** sebagai build tool dengan dua dependensi eksternal:

### `pom.xml` — Dependensi:
```xml
<!-- Apache Commons Lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.14.0</version>
</dependency>

<!-- Slf4j Simple Logging -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.12</version>
</dependency>
```

| Library            | Kegunaan                                                       |
|--------------------|----------------------------------------------------------------|
| `commons-lang3`    | `StringUtils.isBlank()` dan `StringUtils.capitalize()` di `Tamu.java` |
| `slf4j-simple`     | Logging aktivitas sistem di `AppLogger.java`                   |

### Cara Build & Run dengan Maven:
```bash
# Build dan package
mvn package

# Jalankan dari folder output/
java -jar output/hotel-management-1.0.0.jar
```

### Cara Compile Manual (tanpa Maven):
```bash
# Compile
javac --enable-preview --release 21 -d out $(find src -name "*.java")

# Jalankan
java --enable-preview -cp out hotel.HotelManagement
```

---

## 🚀 Fitur Aplikasi

### Manajemen Tamu
- ➕ Tambah tamu biasa atau tamu VIP (Silver/Gold/Platinum)
- 📋 Lihat semua tamu beserta poin loyalitas
- ✏️ Update data tamu (nama, KTP, telp, level VIP)
- 🗑️ Hapus tamu

### Manajemen Reservasi
- 🛎️ Buat reservasi — kamar otomatis berubah status jadi TERPAKAI
- 📋 Lihat semua reservasi dengan total biaya
- ✏️ Update jumlah malam (hanya status AKTIF)
- ❌ Batalkan reservasi — kamar otomatis kembali TERSEDIA

### Fitur Tambahan
- 🌟 **Diskon otomatis** untuk Tamu VIP (Silver 5%, Gold 10%, Platinum 20%)
- 🏆 **Poin loyalitas** — diperoleh setiap menginap (VIP mendapat 2x poin)
- 🛡️ **Validasi input** di semua setter dan constructor
- 📝 **Logging** setiap aktivitas CRUD via `AppLogger`

---

## 📊 Ringkasan Access Modifier

```
┌──────────────────────────────────────────────────────────────────┐
│                   RINGKASAN ACCESS MODIFIER                      │
├──────────────┬───────────────────────┬───────────────────────────┤
│   Modifier   │       Lokasi          │        Keterangan         │
├──────────────┼───────────────────────┼───────────────────────────┤
│   public     │ Tamu.getNama()        │ Akses dari mana saja      │
│              │ Tamu.setNama()        │                           │
│              │ Kamar.isTersedia()    │                           │
│              │ Reservasi.isAktif()   │                           │
├──────────────┼───────────────────────┼───────────────────────────┤
│   private    │ Tamu.id               │ Hanya dalam kelas sendiri │
│              │ Tamu.validasiNama()   │                           │
│              │ Kamar.nomorKamar      │                           │
│              │ InputHelper.sc        │                           │
├──────────────┼───────────────────────┼───────────────────────────┤
│   protected  │ Tamu.poinLoyalitas    │ Dapat diakses subkelas    │
│              │                       │ TamuVIP                   │
├──────────────┼───────────────────────┼───────────────────────────┤
│   default    │ Tamu.formatInfo()     │ Hanya dalam package       │
│  (package)   │ Tamu.totalTamuDibuat  │ hotel.model               │
└──────────────┴───────────────────────┴───────────────────────────┘
```

---

## 👤 Informasi

| Item        | Detail                        |
|-------------|-------------------------------|
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Tugas       | Posttest 2                    |
| Topik       | Encapsulation & Access Modifier |
| Build Tool  | Apache Maven 3.x              |
| Java Version| Java 17+ / Java 21            |
