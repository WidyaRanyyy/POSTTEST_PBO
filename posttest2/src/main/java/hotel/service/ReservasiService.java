package hotel.service;

import hotel.model.Kamar;
import hotel.model.Reservasi;
import hotel.model.Tamu;
import hotel.model.TamuVIP;
import hotel.util.AppLogger;
import hotel.util.InputHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data reservasi hotel.
 */
public class ReservasiService {

    // === PRIVATE FIELDS ===
    private final List<Reservasi> daftarReservasi = new ArrayList<>();
    private final AppLogger       logger          = new AppLogger(ReservasiService.class);
    private int idCounter = 1;

    // ─── PUBLIC CRUD METHODS ─────────────────────────────────────

    public void buatReservasi(TamuService tamuSvc, KamarService kamarSvc) {
        System.out.println("\n--- BUAT RESERVASI BARU ---");

        tamuSvc.lihatSemuaTamu();
        int idTamu = InputHelper.inputInt("  Pilih ID Tamu: ");
        Tamu tamu  = tamuSvc.cariTamuById(idTamu);

        if (tamu == null) {
            System.out.println("  [!] Tamu tidak ditemukan.");
            return;
        }

        if (!kamarSvc.adaKamarTersedia()) {
            System.out.println("  [!] Tidak ada kamar yang tersedia saat ini.");
            return;
        }

        System.out.println("\n  Kamar yang tersedia:");
        kamarSvc.tampilKamarTersedia();

        int noKamar = InputHelper.inputInt("  Masukkan No. Kamar yang dipilih: ");
        Kamar kamar = kamarSvc.cariKamarByNomor(noKamar);

        if (kamar == null) {
            System.out.println("  [!] Kamar tidak tersedia atau tidak ditemukan.");
            return;
        }

        System.out.println("  Tipe Kamar    : " + kamar.getTipeKamar());
        System.out.printf ("  Harga/Malam   : Rp %.0f%n", kamar.getHargaPerMalam());
        if (tamu instanceof TamuVIP vip) {
            System.out.printf("  Diskon VIP    : %.0f%%%n", vip.getDiskonPersen());
        }

        try {
            int malam = InputHelper.inputInt("  Jumlah Malam  : ");
            Reservasi reservasi = new Reservasi(idCounter++, tamu, kamar, malam);
            kamar.setTersedia(false);
            daftarReservasi.add(reservasi);

            // Beri poin loyalitas ke tamu
            int poin = malam * 10;
            if (tamu instanceof TamuVIP vip) {
                vip.tambahPoinDouble(poin);
            } else {
                tamu.tambahPoin(poin);
            }

            logger.info("Reservasi dibuat: ID " + reservasi.getIdReservasi() + " oleh " + tamu);
            System.out.println("\n  [✓] Reservasi berhasil dibuat!");
            System.out.printf ("  Total Biaya   : Rp %.0f%n", reservasi.hitungTotalBiaya());
            System.out.println("  Poin Loyalitas diperoleh: " + (tamu instanceof TamuVIP ? poin * 2 : poin));

        } catch (IllegalArgumentException e) {
            System.out.println("  [!] Error: " + e.getMessage());
        }
    }

    public void lihatSemuaReservasi() {
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

    public void updateReservasi() {
        System.out.println("\n--- UPDATE RESERVASI ---");
        lihatSemuaReservasi();
        int id       = InputHelper.inputInt("  Masukkan ID Reservasi yang ingin diupdate: ");
        Reservasi target = cariReservasiById(id);

        if (target == null) {
            System.out.println("  [!] Reservasi tidak ditemukan.");
            return;
        }
        if (!target.isAktif()) {
            System.out.println("  [!] Hanya reservasi AKTIF yang dapat diupdate.");
            return;
        }

        try {
            Integer malamBaru = InputHelper.inputIntOptional(
                    "  Jumlah malam baru [" + target.getJumlahMalam() + "]: ");
            if (malamBaru != null) target.setJumlahMalam(malamBaru);

            logger.info("Reservasi diupdate: ID " + id);
            System.out.println("  [✓] Reservasi berhasil diperbarui.");
            System.out.printf ("  Total Biaya terbaru: Rp %.0f%n", target.hitungTotalBiaya());

        } catch (IllegalArgumentException e) {
            System.out.println("  [!] Error: " + e.getMessage());
        }
    }

    public void batalkanReservasi() {
        System.out.println("\n--- BATALKAN RESERVASI ---");
        lihatSemuaReservasi();
        int id       = InputHelper.inputInt("  Masukkan ID Reservasi yang ingin dibatalkan: ");
        Reservasi target = cariReservasiById(id);

        if (target == null) {
            System.out.println("  [!] Reservasi tidak ditemukan.");
            return;
        }
        if (!target.isAktif()) {
            System.out.println("  [!] Reservasi ini sudah " + target.getStatusReservasi() + ".");
            return;
        }

        target.getKamar().setTersedia(true);
        target.setStatusReservasi(Reservasi.STATUS_DIBATALKAN);

        logger.info("Reservasi dibatalkan: ID " + id);
        System.out.println("  [✓] Reservasi ID " + id + " berhasil dibatalkan. Kamar kembali tersedia.");
    }

    // ─── PRIVATE HELPER ──────────────────────────────────────────

    private Reservasi cariReservasiById(int id) {
        for (Reservasi r : daftarReservasi)
            if (r.getIdReservasi() == id) return r;
        return null;
    }
}
