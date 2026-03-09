## Nama : Widya Ayu Anggraini
## NIM : 2409106011    

# 🏨 Sistem Manajemen Pengelolaan & Reservasi Hotel

Program berbasis **Java** untuk mengelola data tamu dan reservasi hotel melalui antarmuka menu berbasis teks (CLI). Program ini dibuat sebagai tugas praktikum **Pemrograman Berorientasi Objek (PBO)** menggunakan konsep **Class, Object, Constructor, Getter/Setter**, dan **CRUD dengan ArrayList**.

---

## 📋 Daftar Isi

- [Fitur Program](#-fitur-program)
- [Struktur Class](#-struktur-class)
- [Cara Menjalankan](#-cara-menjalankan)
- [Alur Program](#-alur-program)
- [Contoh Penggunaan](#-contoh-penggunaan)
- [Konsep OOP yang Digunakan](#-konsep-oop-yang-digunakan)

---

## ✨ Fitur Program

### 👤 Manajemen Tamu
| Fitur | Keterangan |
|-------|-----------|
| Tambah Tamu | Menambahkan data tamu baru ke dalam sistem |
| Lihat Semua Tamu | Menampilkan seluruh daftar tamu yang terdaftar |
| Update Data Tamu | Mengubah nama, No. KTP, atau No. Telp tamu |
| Hapus Tamu | Menghapus data tamu dari sistem |

### 🛎️ Manajemen Reservasi
| Fitur | Keterangan |
|-------|-----------|
| Buat Reservasi | Membuat reservasi baru dengan memilih tamu dan kamar yang tersedia |
| Lihat Semua Reservasi | Menampilkan seluruh data reservasi beserta statusnya |
| Update Reservasi | Mengubah jumlah malam pada reservasi yang berstatus AKTIF |
| Batalkan Reservasi | Membatalkan reservasi dan mengembalikan kamar menjadi tersedia |

### 🏠 Data Kamar (Tetap)
Kamar tidak dapat ditambah/dihapus oleh pengguna. Data kamar sudah tersedia secara default:

| No. Kamar | Tipe | Harga/Malam |
|-----------|------|-------------|
| 101 | Standard | Rp 350.000 |
| 102 | Standard | Rp 350.000 |
| 201 | Deluxe | Rp 600.000 |
| 202 | Deluxe | Rp 600.000 |
| 301 | Suite | Rp 1.200.000 |

---

## 🗂️ Struktur Class

### Class `Tamu`
Menyimpan data identitas tamu hotel.

| Properti | Tipe | Keterangan |
|----------|------|-----------|
| `id` | `int` | ID unik tamu (auto-increment) |
| `nama` | `String` | Nama lengkap tamu |
| `noKtp` | `long` | Nomor KTP (16 digit) |
| `noTelp` | `long` | Nomor telepon tamu |

**Constructor:**
- `Tamu()` — Non-argument constructor, nilai default
- `Tamu(int id, String nama, long noKtp, long noTelp)` — Parameterized constructor

---

### Class `Reservasi`
Menyimpan data reservasi kamar oleh tamu.

| Properti | Tipe | Keterangan |
|----------|------|-----------|
| `idReservasi` | `int` | ID unik reservasi (auto-increment) |
| `tamu` | `Tamu` | Objek tamu yang melakukan reservasi |
| `nomorKamar` | `int` | Nomor kamar yang dipesan |
| `tipeKamar` | `String` | Tipe kamar (Standard/Deluxe/Suite) |
| `hargaPerMalam` | `double` | Harga kamar per malam |
| `jumlahMalam` | `int` | Lama menginap (dalam malam) |
| `statusReservasi` | `String` | Status: `AKTIF`, `SELESAI`, atau `DIBATALKAN` |

**Constructor:**
- `Reservasi()` — Non-argument constructor, nilai default
- `Reservasi(int, Tamu, int, String, double, int)` — Parameterized constructor

**Method Penting:**
- `hitungTotalBiaya()` — Mengembalikan hasil `hargaPerMalam × jumlahMalam`

---

### Class `HotelManagement` *(Main Program)*
Class utama yang menjalankan seluruh program.

| Komponen | Keterangan |
|----------|-----------|
| `daftarTamu` | `ArrayList<Tamu>` — menyimpan semua data tamu |
| `daftarReservasi` | `ArrayList<Reservasi>` — menyimpan semua data reservasi |
| `nomorKamarList[]` | Array data nomor kamar |
| `tipeKamarList[]` | Array data tipe kamar |
| `hargaKamarList[]` | Array data harga kamar |
| `statusKamar[]` | Array status ketersediaan kamar |

---

## 🚀 Cara Menjalankan

### Persyaratan
- **Java JDK** versi 14 atau lebih baru (mendukung switch expression `->`)

### Langkah Kompilasi dan Eksekusi

```bash
# 1. Kompilasi program
javac HotelManagement.java

# 2. Jalankan program
java HotelManagement
```

---

## 🔄 Alur Program

```
Program Dimulai
      │
      ▼
┌─────────────────────────┐
│     MENU UTAMA          │
│  1. Manajemen Tamu      │
│  2. Manajemen Reservasi │
│  0. Keluar              │
└─────────────────────────┘
      │
      ├──► [1] MENU TAMU
      │         ├── Tambah Tamu
      │         ├── Lihat Semua Tamu
      │         ├── Update Data Tamu
      │         ├── Hapus Tamu
      │         └── Kembali
      │
      ├──► [2] MENU RESERVASI
      │         ├── Buat Reservasi  ──► Pilih Tamu ──► Pilih Kamar ──► Input Malam
      │         ├── Lihat Semua Reservasi
      │         ├── Update Reservasi (hanya status AKTIF)
      │         ├── Batalkan Reservasi (hanya status AKTIF)
      │         └── Kembali
      │
      └──► [0] Program Berhenti
```

---

## 💡 Contoh Penggunaan

### Membuat Reservasi Baru
```
--- BUAT RESERVASI BARU ---

  ────────────────────────
  ID       : 1
  Nama     : Dewi
  No. KTP  : 2409106007
  No. Telp : 81234567890
  ────────────────────────

  Pilih ID Tamu: 1

  Kamar yang tersedia:
  ┌────────────┬──────────────┬─────────────────┐
  │ No. Kamar  │ Tipe         │ Harga/Malam     │
  ├────────────┼──────────────┼─────────────────┤
  │ 101        │ Standard     │ Rp 350000       │
  │ 102        │ Standard     │ Rp 350000       │
  │ 201        │ Deluxe       │ Rp 600000       │
  │ 202        │ Deluxe       │ Rp 600000       │
  │ 301        │ Suite        │ Rp 1200000      │
  └────────────┴──────────────┴─────────────────┘

  Masukkan No. Kamar yang dipilih: 201
  Tipe Kamar    : Deluxe
  Harga/Malam   : Rp 600000
  Jumlah Malam  : 3

  [✓] Reservasi berhasil dibuat!
  Total Biaya: Rp 1800000
```

### Error Handling saat Update No. KTP
```
  No. KTP baru [2409106007]: abcdef
  [!] No. KTP harus berupa angka. Coba lagi.
  No. KTP baru [2409106007]: 1234567890
  [✓] Data tamu berhasil diperbarui.
```

---

## 📚 Konsep OOP yang Digunakan

| Konsep | Implementasi |
|--------|-------------|
| **Class** | `Tamu`, `Reservasi`, `HotelManagement` |
| **Object** | `new Tamu(...)`, `new Reservasi(...)` |
| **Non-argument Constructor** | `Tamu()`, `Reservasi()` |
| **Parameterized Constructor** | `Tamu(id, nama, noKtp, noTelp)`, `Reservasi(...)` |
| **Getter & Setter** | `getId()`, `setNama()`, `getNoKtp()`, dll. |
| **Keyword `this`** | Digunakan di seluruh constructor untuk membedakan properti dan parameter |
| **ArrayList** | `daftarTamu`, `daftarReservasi` untuk menyimpan data dinamis |
| **CRUD** | Create, Read, Update, Delete pada data Tamu dan Reservasi |

---

## 👩‍💻 Informasi Program

| | |
|--|--|
| **Mata Kuliah** | Pemrograman Berorientasi Objek |
| **Bahasa** | Java |
| **Tipe Program** | CLI (Command Line Interface) |
