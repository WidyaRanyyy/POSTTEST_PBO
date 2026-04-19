package hotel.service;

import hotel.model.Kamar;
import hotel.model.Laporan;
import hotel.model.Reservasi;
import hotel.model.Tamu;
import hotel.model.TamuVIP;
import hotel.util.AppLogger;
import hotel.util.InputHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data reservasi hotel.
 * Posttest 5: mendemonstrasikan interface Laporan pada Reservasi.
 */
public class ReservasiService {

    private final List<Reservasi> daftarReservasi = new ArrayList<>();
    private final AppLogger       logger          = new AppLogger(ReservasiService.class);
    private int idCounter = 1;

    public void buatReservasi(TamuService tamuSvc, KamarService kamarSvc) {
        System.out.println("\n--- BUAT RESERVASI BARU ---");

        tamuSvc.lihatSemuaTamu();
        int idTamu = InputHelper.inputInt("  Pilih ID Tamu: ");
        Tamu tamu  = tamuSvc.cariTamuById(idTamu);

        if (tamu == null) {
            System.out.println("  [!] Tamu tidak ditemukan.");
            return;
        }

        // Demo abstract method getStatusTamu()
        System.out.println("  Status Tamu   : " + tamu.getStatusTamu());

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

        System.out.println("\n  Detail kamar yang dipilih:");
        kamar.tampilInfo(true);

        System.out.printf("  Harga/Malam   : Rp %.0f%n", kamar.getHargaPerMalam());
        if (tamu instanceof TamuVIP vip) {
            System.out.printf("  Diskon VIP    : %.0f%%%n", vip.getDiskonPersen());
        }

        try {
            int malam = InputHelper.inputInt("  Jumlah Malam  : ");
            Reservasi reservasi = new Reservasi(idCounter++, tamu, kamar, malam);
            kamar.setTersedia(false);

            System.out.println("\n  Biaya layanan tambahan (room service, laundry, dll):");
            System.out.println("  Masukkan 0 jika tidak ada biaya tambahan.");
            double biayaTambahan = InputHelper.inputDouble("  Biaya Tambahan (Rp): ");

            daftarReservasi.add(reservasi);

            int poinDasar = malam * 10;
            String alasanPoin = "Reservasi " + malam + " malam kamar " + kamar.getTipeKamar();
            tamu.tambahPoin(poinDasar, alasanPoin);

            logger.info("Reservasi dibuat: ID " + reservasi.getIdReservasi() + " oleh " + tamu);
            System.out.println("\n  [✓] Reservasi berhasil dibuat!");

            if (biayaTambahan > 0) {
                System.out.printf("  Total Kamar    : Rp %.0f%n", reservasi.hitungTotalBiaya());
                System.out.printf("  Biaya Tambahan : Rp %.0f%n", biayaTambahan);
                System.out.printf("  Total Tagihan  : Rp %.0f%n", reservasi.hitungTotalBiaya(biayaTambahan));
            } else {
                System.out.printf("  Total Biaya    : Rp %.0f%n", reservasi.hitungTotalBiaya());
            }

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

    /**
     * Demo interface Laporan pada Reservasi — mencetak ringkasan semua reservasi.
     */
    public void cetakLaporanReservasi() {
        System.out.println("\n--- LAPORAN RINGKASAN RESERVASI ---");
        System.out.println("  [Interface: Laporan.getRingkasan()]");
        if (daftarReservasi.isEmpty()) {
            System.out.println("  Belum ada data reservasi.");
            return;
        }
        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────────");
        for (Reservasi r : daftarReservasi) {
            Laporan laporan = r; // Reservasi implements Laporan
            System.out.println("  [" + laporan.getKategoriLaporan() + "] " + laporan.getRingkasan());
        }
        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────────");
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

    private Reservasi cariReservasiById(int id) {
        for (Reservasi r : daftarReservasi)
            if (r.getIdReservasi() == id) return r;
        return null;
    }
}
