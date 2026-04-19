package hotel;

import hotel.service.KamarService;
import hotel.service.ReservasiService;
import hotel.service.TamuService;
import hotel.util.InputHelper;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  SISTEM MANAJEMEN HOTEL — Posttest 5
 *  Konsep: Encapsulation, Inheritance, Polymorphism,
 *          ABSTRACT CLASS, ABSTRACT METHOD, INTERFACE
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * ═══════════════════════════════════════════════════════════════
 *  RINGKASAN ABSTRACT CLASS
 * ═══════════════════════════════════════════════════════════════
 *
 *  ┌──────────────┬────────────────────────────────────────────────┐
 *  │ Abstract Class│ Abstract Method                               │
 *  ├──────────────┼────────────────────────────────────────────────┤
 *  │ Kamar        │ getFasilitas()   — subclass wajib override     │
 *  │              │ tampilInfo()     — subclass wajib override     │
 *  ├──────────────┼────────────────────────────────────────────────┤
 *  │ Tamu         │ tampilInfo()     — subclass wajib override     │
 *  │              │ getStatusTamu()  — subclass wajib override     │
 *  └──────────────┴────────────────────────────────────────────────┘
 *
 * ═══════════════════════════════════════════════════════════════
 *  RINGKASAN INTERFACE
 * ═══════════════════════════════════════════════════════════════
 *
 *  Interface: Laporan
 *  Method    : getRingkasan()        — wajib diimplementasikan
 *              cetakDetail()         — wajib diimplementasikan
 *              getKategoriLaporan()  — default method (opsional override)
 *
 *  Diimplementasikan oleh:
 *  ┌───────────────┬──────────────────────────────────────────────┐
 *  │ Class         │ Keterangan                                   │
 *  ├───────────────┼──────────────────────────────────────────────┤
 *  │ Kamar (abs)   │ getRingkasan() + cetakDetail() + kategori    │
 *  │ Tamu (abs)    │ getRingkasan() + cetakDetail() + kategori    │
 *  │ Reservasi     │ getRingkasan() + cetakDetail() + kategori    │
 *  └───────────────┴──────────────────────────────────────────────┘
 *
 * ═══════════════════════════════════════════════════════════════
 *  RINGKASAN INHERITANCE & CONCRETE CLASSES
 * ═══════════════════════════════════════════════════════════════
 *
 *  ┌──────────────────┬──────────────┬─────────────────────────────┐
 *  │ Subclass         │ Superclass   │ Tambahan                    │
 *  ├──────────────────┼──────────────┼─────────────────────────────┤
 *  │ TamuBiasa (NEW)  │ Tamu (abs)   │ implements abstract methods │
 *  │ TamuVIP          │ Tamu (abs)   │ tingkatVIP, diskonPersen    │
 *  │ KamarStandard    │ Kamar (abs)  │ sarapanIncluded             │
 *  │ KamarDeluxe      │ Kamar (abs)  │ pemandangan                 │
 *  │ KamarSuite       │ Kamar (abs)  │ jumlahRuangan, butler       │
 *  └──────────────────┴──────────────┴─────────────────────────────┘
 */
public class HotelManagement {

    public static void main(String[] args) {
        TamuService      tamuSvc      = new TamuService();
        KamarService     kamarSvc     = new KamarService();
        ReservasiService reservasiSvc = new ReservasiService();

        boolean running = true;
        while (running) {
            tampilMenuUtama();
            int pilihan = InputHelper.inputInt("Pilih menu: ");

            switch (pilihan) {
                case 1 -> menuTamu(tamuSvc);
                case 2 -> menuReservasi(tamuSvc, kamarSvc, reservasiSvc);
                case 3 -> menuKamar(kamarSvc);
                case 4 -> menuLaporan(tamuSvc, kamarSvc, reservasiSvc);
                case 0 -> {
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║   Terima kasih! Program dihentikan.  ║");
                    System.out.println("╚══════════════════════════════════════╝\n");
                    running = false;
                }
                default -> System.out.println("  [!] Pilihan tidak valid. Coba lagi.\n");
            }
        }

        InputHelper.close();
    }

    private static void tampilMenuUtama() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║    SISTEM MANAJEMEN HOTEL - MENU UTAMA       ║");
        System.out.println("║    [ Posttest 5 - Abstract Class & Interface ]║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Manajemen Tamu                           ║");
        System.out.println("║  2. Manajemen Reservasi                      ║");
        System.out.println("║  3. Lihat Kamar                              ║");
        System.out.println("║  4. Laporan (Demo Interface Laporan)         ║");
        System.out.println("║  0. Keluar                                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private static void menuTamu(TamuService tamuSvc) {
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
            int pilihan = InputHelper.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> tamuSvc.tambahTamu();
                case 2 -> tamuSvc.lihatSemuaTamu();
                case 3 -> tamuSvc.updateTamu();
                case 4 -> tamuSvc.hapusTamu();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    private static void menuReservasi(TamuService tamuSvc,
                                      KamarService kamarSvc,
                                      ReservasiService reservasiSvc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│     MANAJEMEN DATA RESERVASI    │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Buat Reservasi (Create)     │");
            System.out.println("│  2. Lihat Semua Reservasi (Read)│");
            System.out.println("│  3. Update Reservasi            │");
            System.out.println("│  4. Batalkan Reservasi          │");
            System.out.println("│  0. Kembali ke Menu Utama       │");
            System.out.println("└─────────────────────────────────┘");
            int pilihan = InputHelper.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> reservasiSvc.buatReservasi(tamuSvc, kamarSvc);
                case 2 -> reservasiSvc.lihatSemuaReservasi();
                case 3 -> reservasiSvc.updateReservasi();
                case 4 -> reservasiSvc.batalkanReservasi();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    private static void menuKamar(KamarService kamarSvc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│              LIHAT KAMAR                 │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Semua Kamar Tersedia                 │");
            System.out.println("│  2. Filter Kamar by Tipe                 │");
            System.out.println("│  0. Kembali ke Menu Utama                │");
            System.out.println("└──────────────────────────────────────────┘");
            int pilihan = InputHelper.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> kamarSvc.tampilKamarTersedia();
                case 2 -> {
                    System.out.println("\n  Tipe: Standard / Deluxe / Suite");
                    String tipe = InputHelper.inputString("  Masukkan tipe kamar: ");
                    kamarSvc.tampilKamarTersedia(tipe);
                }
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }

    /**
     * Menu Laporan — demo penggunaan interface Laporan.
     * Menunjukkan polimorfisme melalui interface: semua objek yang
     * implements Laporan dapat dipanggil dengan cara yang seragam.
     */
    private static void menuLaporan(TamuService tamuSvc,
                                    KamarService kamarSvc,
                                    ReservasiService reservasiSvc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────────────────────────┐");
            System.out.println("│     LAPORAN (Demo Interface Laporan)             │");
            System.out.println("│  Kamar, Tamu, dan Reservasi implements Laporan   │");
            System.out.println("├──────────────────────────────────────────────────┤");
            System.out.println("│  1. Laporan Ringkasan Kamar                      │");
            System.out.println("│  2. Laporan Ringkasan Tamu                       │");
            System.out.println("│  3. Laporan Ringkasan Reservasi                  │");
            System.out.println("│  0. Kembali ke Menu Utama                        │");
            System.out.println("└──────────────────────────────────────────────────┘");
            int pilihan = InputHelper.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> kamarSvc.cetakLaporanKamar();
                case 2 -> tamuSvc.cetakLaporanTamu();
                case 3 -> reservasiSvc.cetakLaporanReservasi();
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }
}
