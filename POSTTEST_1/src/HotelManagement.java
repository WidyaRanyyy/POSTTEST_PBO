import java.util.ArrayList;
import java.util.Scanner;

// CLASS 1 : Tamu (Guest)

class Tamu {
    private int id;
    private String nama;
    private String noKtp;
    private String noTelp;

    // Non-argument constructor
    Tamu() {
        this.id = 0;
        this.nama = "Anomali";
        this.noKtp = "-";
        this.noTelp = "-";
    }

    // Parameterized constructor
    Tamu(int id, String nama, String noKtp, String noTelp) {
        this.id = id;
        this.nama = nama;
        this.noKtp = noKtp;
        this.noTelp = noTelp;
    }

    // mengambil dan mengisi variabel
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNoKtp() { return noKtp; }
    public void setNoKtp(String noKtp) { this.noKtp = noKtp; }

    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }

    public void tampilInfo() {
        System.out.println("  ID       : " + id);
        System.out.println("  Nama     : " + nama);
        System.out.println("  No. KTP  : " + noKtp);
        System.out.println("  No. Telp : " + noTelp);
    }
}


// CLASS 2 : Kamar (Room)

class Kamar {
    private int nomorKamar;
    private String tipeKamar;   // Standard, Deluxe, Suite
    private double hargaPerMalam;
    private boolean tersedia;

    Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        this.tersedia = true;
    }

    public int getNomorKamar() { return nomorKamar; }
    public String getTipeKamar() { return tipeKamar; }
    public double getHargaPerMalam() { return hargaPerMalam; }
    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
    public void setHargaPerMalam(double harga) { this.hargaPerMalam = harga; }
    public void setTipeKamar(String tipe) { this.tipeKamar = tipe; }

    public void tampilInfo() {
        System.out.println("  No. Kamar     : " + nomorKamar);
        System.out.println("  Tipe          : " + tipeKamar);
        System.out.printf ("  Harga/Malam   : Rp %.0f%n", hargaPerMalam);
        System.out.println("  Status        : " + (tersedia ? "Tersedia" : "Tidak Tersedia"));
    }
}

// CLASS 3 : Reservasi (Reservation)

class Reservasi {
    private int idReservasi;
    private Tamu tamu;
    private Kamar kamar;
    private String tanggalCheckIn;
    private String tanggalCheckOut;
    private int jumlahMalam;
    private String statusReservasi; // AKTIF, SELESAI, DIBATALKAN

    Reservasi(int idReservasi, Tamu tamu, Kamar kamar,
              String tanggalCheckIn, String tanggalCheckOut, int jumlahMalam) {
        this.idReservasi = idReservasi;
        this.tamu = tamu;
        this.kamar = kamar;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.jumlahMalam = jumlahMalam;
        this.statusReservasi = "AKTIF";
    }

    public int getIdReservasi() { return idReservasi; }
    public Tamu getTamu() { return tamu; }
    public Kamar getKamar() { return kamar; }
    public String getTanggalCheckIn() { return tanggalCheckIn; }
    public String getTanggalCheckOut() { return tanggalCheckOut; }
    public int getJumlahMalam() { return jumlahMalam; }
    public String getStatusReservasi() { return statusReservasi; }
    public void setStatusReservasi(String status) { this.statusReservasi = status; }
    public void setTanggalCheckIn(String tgl) { this.tanggalCheckIn = tgl; }
    public void setTanggalCheckOut(String tgl) { this.tanggalCheckOut = tgl; }
    public void setJumlahMalam(int malam) { this.jumlahMalam = malam; }

    public double hitungTotalBiaya() {
        return kamar.getHargaPerMalam() * jumlahMalam;
    }

    public void tampilInfo() {
        System.out.println("  ID Reservasi  : " + idReservasi);
        System.out.println("  Tamu          : " + tamu.getNama() + " (ID: " + tamu.getId() + ")");
        System.out.println("  Kamar         : No." + kamar.getNomorKamar() + " - " + kamar.getTipeKamar());
        System.out.println("  Check-In      : " + tanggalCheckIn);
        System.out.println("  Check-Out     : " + tanggalCheckOut);
        System.out.println("  Jumlah Malam  : " + jumlahMalam + " malam");
        System.out.printf ("  Total Biaya   : Rp %.0f%n", hitungTotalBiaya());
        System.out.println("  Status        : " + statusReservasi);
    }
}


// Main Program [HotelManagement]

public class HotelManagement {

    static ArrayList<Tamu> daftarTamu = new ArrayList<>();
    static ArrayList<Kamar> daftarKamar = new ArrayList<>();
    static ArrayList<Reservasi> daftarReservasi = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int idTamuCounter = 1;
    static int idReservasiCounter = 1;

    public static void main(String[] args) {
        inisialisasiDataAwal();

        boolean running = true;
        while (running) {
            tampilMenuUtama();
            int pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {
                case 1 -> menuTamu();
                case 2 -> menuKamar();
                case 3 -> menuReservasi();
                case 0 -> {
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║   Terima kasih! Program dihentikan.  ║");
                    System.out.println("╚══════════════════════════════════════╝\n");
                    running = false;
                }
                default -> System.out.println("  [!] Pilihan tidak valid. Coba lagi.\n");
            }
        }
        sc.close();
    }


    //  DATA AWAL

    static void inisialisasiDataAwal() {
        daftarKamar.add(new Kamar(101, "Standard",  350_000));
        daftarKamar.add(new Kamar(102, "Standard",  350_000));
        daftarKamar.add(new Kamar(201, "Deluxe",    600_000));
        daftarKamar.add(new Kamar(202, "Deluxe",    600_000));
        daftarKamar.add(new Kamar(301, "Suite",   1_200_000));

        daftarTamu.add(new Tamu(idTamuCounter++, "Dewi",  "2409106007", "0812345678"));
        daftarTamu.add(new Tamu(idTamuCounter++, "Sari",   "320292489", "08222222"));
    }


    //  MENU UTAMA

    static void tampilMenuUtama() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║    SISTEM MANAJEMEN HOTEL - MENU UTAMA       ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Manajemen Tamu                           ║");
        System.out.println("║  2. Manajemen Kamar                          ║");
        System.out.println("║  3. Manajemen Reservasi                      ║");
        System.out.println("║  0. Keluar                                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }


    //  MENU TAMU

    static void menuTamu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│       MANAJEMEN DATA TAMU       │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Tambah Tamu (Create)        │");
            System.out.println("│  2. Lihat Semua Tamu (Read)     │");
            System.out.println("│  3. Update Data Tamu            │");
            System.out.println("│  4. Hapus Tamu (Delete)         │");
            System.out.println("│  0. Kembali ke Menu Utama       │");
            System.out.println("└─────────────────────────────────┘");
            int pilihan = inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> tambahTamu();
                case 2 -> lihatSemuaTamu();
                case 3 -> updateTamu();
                case 4 -> hapusTamu();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    static void tambahTamu() {
        System.out.println("\n--- TAMBAH TAMU BARU ---");
        sc.nextLine();
        System.out.print("  Nama       : ");
        String nama = sc.nextLine();
        System.out.print("  No. KTP    : ");
        String ktp = sc.nextLine();
        System.out.print("  No. Telp   : ");
        String telp = sc.nextLine();

        Tamu tamu = new Tamu(idTamuCounter++, nama, ktp, telp);
        daftarTamu.add(tamu);
        System.out.println("  [✓] Tamu '" + nama + "' berhasil ditambahkan (ID: " + tamu.getId() + ").");
    }

    static void lihatSemuaTamu() {
        System.out.println("\n--- DAFTAR TAMU ---");
        if (daftarTamu.isEmpty()) {
            System.out.println("  Belum ada data tamu.");
            return;
        }
        for (Tamu t : daftarTamu) {
            System.out.println("  ────────────────────────");
            t.tampilInfo();
        }
        System.out.println("  ────────────────────────");
    }

    static void updateTamu() {
        System.out.println("\n--- UPDATE DATA TAMU ---");
        lihatSemuaTamu();
        int id = inputInt("  Masukkan ID tamu yang ingin diupdate: ");
        Tamu target = cariTamuById(id);

        if (target == null) {
            System.out.println("  [!] Tamu dengan ID " + id + " tidak ditemukan.");
            return;
        }

        sc.nextLine();
        System.out.print("  Nama baru [" + target.getNama() + "]: ");
        String nama = sc.nextLine();
        System.out.print("  No. KTP baru [" + target.getNoKtp() + "]: ");
        String ktp = sc.nextLine();
        System.out.print("  No. Telp baru [" + target.getNoTelp() + "]: ");
        String telp = sc.nextLine();

        if (!nama.isBlank()) target.setNama(nama);
        if (!ktp.isBlank()) target.setNoKtp(ktp);
        if (!telp.isBlank()) target.setNoTelp(telp);

        System.out.println("  [✓] Data tamu berhasil diperbarui.");
    }

    static void hapusTamu() {
        System.out.println("\n--- HAPUS TAMU ---");
        lihatSemuaTamu();
        int id = inputInt("  Masukkan ID tamu yang ingin dihapus: ");
        Tamu target = cariTamuById(id);

        if (target == null) {
            System.out.println("  [!] Tamu tidak ditemukan.");
            return;
        }

        daftarTamu.remove(target);
        System.out.println("  [✓] Tamu '" + target.getNama() + "' berhasil dihapus.");
    }


    //  MENU KAMAR

    static void menuKamar() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│       MANAJEMEN DATA KAMAR      │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Tambah Kamar (Create)       │");
            System.out.println("│  2. Lihat Semua Kamar (Read)    │");
            System.out.println("│  3. Update Data Kamar           │");
            System.out.println("│  4. Hapus Kamar (Delete)        │");
            System.out.println("│  0. Kembali ke Menu Utama       │");
            System.out.println("└─────────────────────────────────┘");
            int pilihan = inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> tambahKamar();
                case 2 -> lihatSemuaKamar();
                case 3 -> updateKamar();
                case 4 -> hapusKamar();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    static void tambahKamar() {
        System.out.println("\n--- TAMBAH KAMAR BARU ---");
        int nomor = inputInt("  No. Kamar  : ");

        if (cariKamarByNomor(nomor) != null) {
            System.out.println("  [!] Kamar dengan nomor " + nomor + " sudah ada.");
            return;
        }

        sc.nextLine();
        System.out.print("  Tipe (Standard/Deluxe/Suite): ");
        String tipe = sc.nextLine();
        double harga = inputDouble("  Harga/Malam (Rp): ");

        daftarKamar.add(new Kamar(nomor, tipe, harga));
        System.out.println("  [✓] Kamar " + nomor + " berhasil ditambahkan.");
    }

    static void lihatSemuaKamar() {
        System.out.println("\n--- DAFTAR KAMAR ---");
        if (daftarKamar.isEmpty()) {
            System.out.println("  Belum ada data kamar.");
            return;
        }
        for (Kamar k : daftarKamar) {
            System.out.println("  ────────────────────────");
            k.tampilInfo();
        }
        System.out.println("  ────────────────────────");
    }

    static void updateKamar() {
        System.out.println("\n--- UPDATE DATA KAMAR ---");
        lihatSemuaKamar();
        int nomor = inputInt("  Masukkan No. Kamar yang ingin diupdate: ");
        Kamar target = cariKamarByNomor(nomor);

        if (target == null) {
            System.out.println("  [!] Kamar tidak ditemukan.");
            return;
        }

        sc.nextLine();
        System.out.print("  Tipe baru [" + target.getTipeKamar() + "]: ");
        String tipe = sc.nextLine();
        System.out.print("  Harga baru [" + target.getHargaPerMalam() + "]: ");
        String hargaStr = sc.nextLine();

        if (!tipe.isBlank()) target.setTipeKamar(tipe);
        if (!hargaStr.isBlank()) target.setHargaPerMalam(Double.parseDouble(hargaStr));

        System.out.println("  [✓] Data kamar berhasil diperbarui.");
    }

    static void hapusKamar() {
        System.out.println("\n--- HAPUS KAMAR ---");
        lihatSemuaKamar();
        int nomor = inputInt("  Masukkan No. Kamar yang ingin dihapus: ");
        Kamar target = cariKamarByNomor(nomor);

        if (target == null) {
            System.out.println("  [!] Kamar tidak ditemukan.");
            return;
        }

        daftarKamar.remove(target);
        System.out.println("  [✓] Kamar " + nomor + " berhasil dihapus.");
    }


    //  MENU RESERVASI

    static void menuReservasi() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│     MANAJEMEN DATA RESERVASI    │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Buat Reservasi (Create)     │");
            System.out.println("│  2. Lihat Semua Reservasi (Read)│");
            System.out.println("│  3. Update Reservasi            │");
            System.out.println("│  4. Batalkan Reservasi (Delete) │");
            System.out.println("│  0. Kembali ke Menu Utama       │");
            System.out.println("└─────────────────────────────────┘");
            int pilihan = inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> buatReservasi();
                case 2 -> lihatSemuaReservasi();
                case 3 -> updateReservasi();
                case 4 -> batalkanReservasi();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    static void buatReservasi() {
        System.out.println("\n--- BUAT RESERVASI BARU ---");

        // Pilih tamu
        lihatSemuaTamu();
        int idTamu = inputInt("  Pilih ID Tamu: ");
        Tamu tamu = cariTamuById(idTamu);
        if (tamu == null) {
            System.out.println("  [!] Tamu tidak ditemukan.");
            return;
        }

        // Tampilkan kamar tersedia
        System.out.println("\n  Kamar yang tersedia:");
        boolean adaKamar = false;
        for (Kamar k : daftarKamar) {
            if (k.isTersedia()) {
                System.out.println("  ────────────────────────");
                k.tampilInfo();
                adaKamar = true;
            }
        }
        if (!adaKamar) {
            System.out.println("  [!] Tidak ada kamar yang tersedia saat ini.");
            return;
        }

        int noKamar = inputInt("  Pilih No. Kamar: ");
        Kamar kamar = cariKamarByNomor(noKamar);
        if (kamar == null || !kamar.isTersedia()) {
            System.out.println("  [!] Kamar tidak tersedia.");
            return;
        }

        sc.nextLine();
        System.out.print("  Tanggal Check-In  (dd/mm/yyyy): ");
        String checkIn = sc.nextLine();
        System.out.print("  Tanggal Check-Out (dd/mm/yyyy): ");
        String checkOut = sc.nextLine();
        int malam = inputInt("  Jumlah Malam: ");

        Reservasi reservasi = new Reservasi(
                idReservasiCounter++, tamu, kamar, checkIn, checkOut, malam
        );
        kamar.setTersedia(false);
        daftarReservasi.add(reservasi);

        System.out.println("\n  [✓] Reservasi berhasil dibuat!");
        System.out.printf ("  Total Biaya: Rp %.0f%n", reservasi.hitungTotalBiaya());
    }

    static void lihatSemuaReservasi() {
        System.out.println("\n--- DAFTAR RESERVASI ---");
        if (daftarReservasi.isEmpty()) {
            System.out.println("  Belum ada data reservasi.");
            return;
        }
        for (Reservasi r : daftarReservasi) {
            System.out.println("  ════════════════════════════════");
            r.tampilInfo();
        }
        System.out.println("  ════════════════════════════════");
    }

    static void updateReservasi() {
        System.out.println("\n--- UPDATE RESERVASI ---");
        lihatSemuaReservasi();
        int id = inputInt("  Masukkan ID Reservasi yang ingin diupdate: ");
        Reservasi target = cariReservasiById(id);

        if (target == null) {
            System.out.println("  [!] Reservasi tidak ditemukan.");
            return;
        }
        if (!target.getStatusReservasi().equals("AKTIF")) {
            System.out.println("  [!] Hanya reservasi AKTIF yang dapat diupdate.");
            return;
        }

        sc.nextLine();
        System.out.print("  Check-In baru [" + target.getTanggalCheckIn() + "]: ");
        String ci = sc.nextLine();
        System.out.print("  Check-Out baru [" + target.getTanggalCheckOut() + "]: ");
        String co = sc.nextLine();
        System.out.print("  Jumlah malam baru [" + target.getJumlahMalam() + "]: ");
        String malamStr = sc.nextLine();

        if (!ci.isBlank()) target.setTanggalCheckIn(ci);
        if (!co.isBlank()) target.setTanggalCheckOut(co);
        if (!malamStr.isBlank()) target.setJumlahMalam(Integer.parseInt(malamStr));

        System.out.println("  [✓] Reservasi berhasil diperbarui.");
        System.out.printf ("  Total Biaya terbaru: Rp %.0f%n", target.hitungTotalBiaya());
    }

    static void batalkanReservasi() {
        System.out.println("\n--- BATALKAN RESERVASI ---");
        lihatSemuaReservasi();
        int id = inputInt("  Masukkan ID Reservasi yang ingin dibatalkan: ");
        Reservasi target = cariReservasiById(id);

        if (target == null) {
            System.out.println("  [!] Reservasi tidak ditemukan.");
            return;
        }
        if (!target.getStatusReservasi().equals("AKTIF")) {
            System.out.println("  [!] Reservasi ini sudah " + target.getStatusReservasi() + ".");
            return;
        }

        target.setStatusReservasi("DIBATALKAN");
        target.getKamar().setTersedia(true); // kamar kembali tersedia
        System.out.println("  [✓] Reservasi ID " + id + " berhasil dibatalkan. Kamar kembali tersedia.");
    }


    //  HELPER METHODS

    static Tamu cariTamuById(int id) {
        for (Tamu t : daftarTamu)
            if (t.getId() == id) return t;
        return null;
    }

    static Kamar cariKamarByNomor(int nomor) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor) return k;
        return null;
    }

    static Reservasi cariReservasiById(int id) {
        for (Reservasi r : daftarReservasi)
            if (r.getIdReservasi() == id) return r;
        return null;
    }

    static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("  [!] Input harus berupa angka. Coba lagi.");
            }
        }
    }

    static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("  [!] Input harus berupa angka. Coba lagi.");
            }
        }
    }
}