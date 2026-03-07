# POSTTEST 1 - Sistem Manajemen Hotel

## Identitas Mahasiswa

Nama : WIdya Ayu Anggraini
NIM  : 2409106011
POSTTEST 1 PBO

---

# Deskripsi Program

Program ini memungkinkan pengguna untuk mengelola:

* Data tamu hotel
* Data kamar hotel
* Data reservasi hotel

Program menerapkan konsep **Object Oriented Programming (OOP)** seperti:

* Class dan Object
* Constructor
* Encapsulation (getter dan setter)
* Relasi antar class
* ArrayList sebagai penyimpanan data

---

# Struktur Class Program

Program terdiri dari **3 class utama** dan **1 class utama (Main Program)**.

## 1. Class Tamu

Class `Tamu` digunakan untuk menyimpan data tamu hotel.

Atribut:

* id
* nama
* noKtp
* noTelp

Method utama:

* getter dan setter
* tampilInfo()

Class ini digunakan untuk menyimpan dan menampilkan informasi tamu yang melakukan reservasi.

---

## 2. Class Kamar

Class `Kamar` digunakan untuk menyimpan data kamar hotel.

Atribut:

* nomorKamar
* tipeKamar
* hargaPerMalam
* tersedia

Method utama:

* getter dan setter
* tampilInfo()

Class ini menyimpan informasi kamar seperti tipe kamar dan harga per malam.

---

## 3. Class Reservasi

Class `Reservasi` digunakan untuk menyimpan data reservasi tamu.

Atribut:

* idReservasi
* tamu
* kamar
* tanggalCheckIn
* tanggalCheckOut
* jumlahMalam
* statusReservasi

Method utama:

* hitungTotalBiaya()
* tampilInfo()

Class ini menghubungkan **Tamu dan Kamar** dalam sebuah reservasi hotel.

---

## 4. Class HotelManagement (Main Program)

Class ini berisi:

* Menu utama program
* Manajemen data tamu
* Manajemen data kamar
* Manajemen reservasi

Program menggunakan **ArrayList** untuk menyimpan data secara dinamis.

ArrayList yang digunakan:

* daftarTamu
* daftarKamar
* daftarReservasi

---

# Fitur Program

Program memiliki beberapa fitur utama:

### 1. Manajemen Tamu

* Tambah tamu
* Lihat semua tamu
* Update data tamu
* Hapus tamu

### 2. Manajemen Kamar

* Tambah kamar
* Lihat semua kamar
* Update data kamar
* Hapus kamar

### 3. Manajemen Reservasi

* Membuat reservasi
* Melihat semua reservasi
* Update reservasi
* Membatalkan reservasi

---

# Menu Utama Program

Program memiliki tampilan menu seperti berikut:

```
SISTEM MANAJEMEN HOTEL - MENU UTAMA
1. Manajemen Tamu
2. Manajemen Kamar
3. Manajemen Reservasi
0. Keluar
```

