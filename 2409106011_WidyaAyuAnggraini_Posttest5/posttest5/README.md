# Sistem Manajemen Hotel — Posttest 5
**Nama:** Widya Ayu Anggraini  
**NIM:** 2409106011  
**Konsep:** Abstract Class, Abstract Method, Interface

---

## Perubahan dari Posttest 4 → Posttest 5

### 1. Abstract Class

#### `Kamar` (sebelumnya: concrete class)
```
public abstract class Kamar implements Laporan { ... }
```
- Dijadikan **abstract** karena Kamar tidak pernah diinstansiasi langsung
- Ditambahkan **2 abstract method**:
  - `getFasilitas()` → wajib di-override setiap subclass
  - `tampilInfo()` → wajib di-override setiap subclass

#### `Tamu` (sebelumnya: concrete class)
```
public abstract class Tamu implements Laporan { ... }
```
- Dijadikan **abstract** karena sistem hotel membedakan TamuBiasa dan TamuVIP
- Ditambahkan **2 abstract method**:
  - `tampilInfo()` → wajib di-override setiap subclass
  - `getStatusTamu()` → wajib mengembalikan label status (misal: "Reguler", "VIP-Gold")

---

### 2. Class Baru: `TamuBiasa`
Karena `Tamu` sudah abstract, dibutuhkan concrete class untuk tamu reguler:
```
public class TamuBiasa extends Tamu { ... }
```
Mengimplementasikan kedua abstract method dari `Tamu`.

---

### 3. Interface: `Laporan`

```java
public interface Laporan {
    String getRingkasan();          // method 1 — wajib
    void   cetakDetail();           // method 2 — wajib
    default String getKategoriLaporan() { return "UMUM"; } // default method
}
```

**Diimplementasikan oleh:**

| Class       | getRingkasan()                        | cetakDetail()                  | getKategoriLaporan() |
|-------------|---------------------------------------|--------------------------------|----------------------|
| `Kamar`     | Ringkasan kamar satu baris            | Cetak detail kamar + fasilitas | "KAMAR"              |
| `Tamu`      | Ringkasan tamu satu baris             | Cetak detail tamu              | "TAMU"               |
| `Reservasi` | Ringkasan reservasi satu baris        | Cetak detail reservasi         | "RESERVASI"          |

---

## Struktur File

```
src/main/java/hotel/
├── HotelManagement.java           ← Main class (+ menu Laporan baru)
├── model/
│   ├── Laporan.java               ← ★ NEW: Interface dengan 2 method + 1 default
│   ├── Kamar.java                 ← ★ UPDATED: Abstract class, 2 abstract method
│   ├── KamarStandard.java         ← implements abstract Kamar
│   ├── KamarDeluxe.java           ← implements abstract Kamar
│   ├── KamarSuite.java            ← implements abstract Kamar
│   ├── Tamu.java                  ← ★ UPDATED: Abstract class, 2 abstract method
│   ├── TamuBiasa.java             ← ★ NEW: Concrete class dari abstract Tamu
│   ├── TamuVIP.java               ← implements abstract Tamu
│   └── Reservasi.java             ← ★ UPDATED: implements Laporan
└── service/
    ├── KamarService.java
    ├── TamuService.java
    └── ReservasiService.java
```

---

## Ringkasan Konsep OOP yang Diterapkan

| Konsep               | Penerapan                                                                 |
|----------------------|---------------------------------------------------------------------------|
| **Abstract Class**   | `Kamar`, `Tamu` — tidak bisa diinstansiasi langsung                       |
| **Abstract Method**  | `getFasilitas()`, `tampilInfo()` di Kamar; `tampilInfo()`, `getStatusTamu()` di Tamu |
| **Interface**        | `Laporan` — diimplementasikan Kamar, Tamu, Reservasi                      |
| **Default Method**   | `getKategoriLaporan()` di interface Laporan                               |
| **Inheritance**      | KamarStandard/Deluxe/Suite → Kamar; TamuBiasa/VIP → Tamu                 |
| **Polymorphism**     | Override & Overload (dipertahankan dari Posttest 4)                       |
| **Encapsulation**    | Semua field private/protected dengan getter/setter                        |

---

## Cara Build & Run

```bash
# Compile
javac -cp src/main/java -d out $(find src -name "*.java")

# Run
java -cp out hotel.HotelManagement

# Atau dengan Maven
mvn package
java -jar target/hotel-management-1.0-SNAPSHOT.jar
```
