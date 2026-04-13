package hotel;

import hotel.service.KamarService;
import hotel.service.ReservasiService;
import hotel.service.TamuService;
import hotel.util.InputHelper;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  SISTEM MANAJEMEN HOTEL — Posttest 4
 *  Konsep: Encapsulation, Access Modifier, Inheritance,
 *          Getter/Setter, Maven + External Libraries,
 *          POLYMORPHISM (Override & Overload)
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * ═══════════════════════════════════════════════════════════════
 *  RINGKASAN POLYMORPHISM
 * ═══════════════════════════════════════════════════════════════
 *
 *  ┌──────────┬──────────────────────────────┬──────────────────────────────────┐
 *  │ Jenis    │ Method                        │ Kelas                            │
 *  ├──────────┼──────────────────────────────┼──────────────────────────────────┤
 *  │ OVERRIDE │ getFasilitas()               │ Kamar → Standard/Deluxe/Suite    │
 *  │ OVERRIDE │ tampilInfo()                 │ Kamar → Standard/Deluxe/Suite    │
 *  │ OVERRIDE │ tampilInfo(boolean)          │ Kamar → Standard/Deluxe/Suite    │
 *  │ OVERRIDE │ tampilInfo()                 │ Tamu  → TamuVIP                  │
 *  │ OVERRIDE │ toString()                   │ Kamar → subclass; Tamu → TamuVIP │
 *  │ OVERRIDE │ tambahPoin(int, String)      │ Tamu  → TamuVIP (2x poin VIP)    │
 *  ├──────────┼──────────────────────────────┼──────────────────────────────────┤
 *  │ OVERLOAD │ getFasilitas()               │ Kamar: tanpa param vs (boolean)  │
 *  │ OVERLOAD │ tampilInfo()                 │ Kamar: tanpa param vs (boolean)  │
 *  │ OVERLOAD │ tambahPoin()                 │ Tamu: (int) vs (int, String)     │
 *  │ OVERLOAD │ hitungTotalBiaya()           │ Reservasi: () vs (double)        │
 *  │ OVERLOAD │ cariKamarByNomor()           │ KamarService: (int) vs (int,Str) │
 *  │ OVERLOAD │ tampilKamarTersedia()        │ KamarService: () vs (String tipe)│
 *  └──────────┴──────────────────────────────┴──────────────────────────────────┘
 *
 *  Ringkasan Inheritance:
 *  ┌──────────────────────┬──────────────┬─────────────────────────────┐
 *  │ Subclass             │ Superclass   │ Tambahan                    │
 *  ├──────────────────────┼──────────────┼─────────────────────────────┤
 *  │ KamarStandard        │ Kamar        │ sarapanIncluded             │
 *  │ KamarDeluxe          │ Kamar        │ pemandangan                 │
 *  │ KamarSuite           │ Kamar        │ jumlahRuangan, butler       │
 *  │ TamuVIP              │ Tamu         │ tingkatVIP, diskonPersen    │
 *  └──────────────────────┴──────────────┴─────────────────────────────┘
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

    // ─── MENU UTAMA ───────────────────────────────────────────────

    private static void tampilMenuUtama() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║    SISTEM MANAJEMEN HOTEL - MENU UTAMA       ║");
        System.out.println("║      [ Posttest 4 - Polymorphism ]           ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Manajemen Tamu                           ║");
        System.out.println("║  2. Manajemen Reservasi                      ║");
        System.out.println("║  3. Lihat Kamar (Demo Polymorphism)          ║");
        System.out.println("║  0. Keluar                                   ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // ─── MENU TAMU ────────────────────────────────────────────────

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

    // ─── MENU RESERVASI ───────────────────────────────────────────

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

    // ─── MENU KAMAR (Demo Polymorphism) ──────────────────────────

    private static void menuKamar(KamarService kamarSvc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│     LIHAT KAMAR (Demo Polymorphism)      │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Semua Kamar Tersedia                 │");
            System.out.println("│  2. Filter Kamar by Tipe                 │");
            System.out.println("│     (Demo Overload tampilKamarTersedia)  │");
            System.out.println("│  0. Kembali ke Menu Utama                │");
            System.out.println("└──────────────────────────────────────────┘");
            int pilihan = InputHelper.inputInt("Pilih: ");

            switch (pilihan) {
                case 1 -> {
                    System.out.println("\n  [Memanggil tampilKamarTersedia() — tanpa parameter]");
                    kamarSvc.tampilKamarTersedia();
                }
                case 2 -> {
                    System.out.println("\n  Tipe: Standard / Deluxe / Suite");
                    String tipe = InputHelper.inputString("  Masukkan tipe kamar: ");
                    System.out.println("  [Memanggil tampilKamarTersedia(String) — OVERLOAD dengan parameter tipe]");
                    kamarSvc.tampilKamarTersedia(tipe); // OVERLOAD versi 2
                }
                case 0 -> back = true;
                default -> System.out.println("  [!] Pilihan tidak valid.");
            }
        }
    }
}
