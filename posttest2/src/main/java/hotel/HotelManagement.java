package hotel;

import hotel.service.KamarService;
import hotel.service.ReservasiService;
import hotel.service.TamuService;
import hotel.util.InputHelper;

/**
 * ╔════════════════════════════════════════════════════╗
 *  SISTEM MANAJEMEN HOTEL — Posttest 2
 *  Konsep: Encapsulation, semua jenis Access Modifier,
 *          Getter/Setter, Maven + External Libraries
 * ╚════════════════════════════════════════════════════╝
 *
 * Ringkasan Access Modifier yang diterapkan:
 *  ┌──────────────┬──────────────────────────────────────────────────┐
 *  │ Modifier     │ Contoh                                           │
 *  ├──────────────┼──────────────────────────────────────────────────┤
 *  │ public       │ Semua getter/setter, constructor, method utama   │
 *  │ private      │ Field data (id, nama, noKtp, dll), helper method │
 *  │ protected    │ poinLoyalitas di Tamu (diakses TamuVIP)          │
 *  │ default      │ formatInfo() di Tamu, totalTamuDibuat di Tamu    │
 *  └──────────────┴──────────────────────────────────────────────────┘
 *
 * External Libraries via Maven:
 *  - commons-lang3  : StringUtils.isBlank(), StringUtils.capitalize()
 *  - slf4j-simple   : logging aktivitas sistem
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
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║  1. Manajemen Tamu                           ║");
        System.out.println("║  2. Manajemen Reservasi                      ║");
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
}
