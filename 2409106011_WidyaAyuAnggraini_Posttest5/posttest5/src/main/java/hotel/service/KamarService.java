package hotel.service;

import hotel.model.Kamar;
import hotel.model.KamarDeluxe;
import hotel.model.KamarStandard;
import hotel.model.KamarSuite;
import hotel.model.Laporan;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data kamar hotel.
 * Posttest 5: Kamar sekarang abstract class yang implements Laporan.
 */
public class KamarService {

    private final List<Kamar> daftarKamar = new ArrayList<>();

    public KamarService() {
        inisialisasiKamar();
    }

    private void inisialisasiKamar() {
        // Kamar tidak bisa di-new langsung (abstract) — harus pakai subclass konkret
        daftarKamar.add(new KamarStandard(101, 350_000, false));
        daftarKamar.add(new KamarStandard(102, 400_000, true));
        daftarKamar.add(new KamarDeluxe(201, 600_000, "Taman"));
        daftarKamar.add(new KamarDeluxe(202, 650_000, "Kolam Renang"));
        daftarKamar.add(new KamarSuite(301, 1_200_000, 2, false));
        daftarKamar.add(new KamarSuite(302, 2_000_000, 3, true));
    }

    public void tampilKamarTersedia() {
        System.out.println("\n  ┌────────────┬──────────────┬─────────────────┬──────────┬────────────┐");
        System.out.println("  │ No. Kamar  │ Tipe         │ Harga/Malam     │ Kapasitas│ Status     │");
        System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
        for (Kamar k : daftarKamar) {
            if (k.isTersedia()) {
                System.out.printf("  │ %-10d │ %-12s │ Rp %-12.0f │ %-8d │ %-10s │%n",
                        k.getNomorKamar(), k.getTipeKamar(),
                        k.getHargaPerMalam(), k.getKapasitasOrang(), "TERSEDIA");
                System.out.printf("  │            │ Fasilitas: %-53s │%n", k.getFasilitas(true));
                System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
            }
        }
        System.out.println("  └────────────┴──────────────┴─────────────────┴──────────┴────────────┘");
    }

    public void tampilKamarTersedia(String tipe) {
        System.out.println("\n  Kamar tersedia tipe: " + tipe.toUpperCase());
        System.out.println("  ┌────────────┬──────────────┬─────────────────┬──────────┬────────────┐");
        System.out.println("  │ No. Kamar  │ Tipe         │ Harga/Malam     │ Kapasitas│ Status     │");
        System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
        boolean ada = false;
        for (Kamar k : daftarKamar) {
            if (k.isTersedia() && k.getTipeKamar().equalsIgnoreCase(tipe)) {
                ada = true;
                System.out.printf("  │ %-10d │ %-12s │ Rp %-12.0f │ %-8d │ %-10s │%n",
                        k.getNomorKamar(), k.getTipeKamar(),
                        k.getHargaPerMalam(), k.getKapasitasOrang(), "TERSEDIA");
                System.out.printf("  │            │ Fasilitas: %-53s │%n", k.getFasilitas(true));
                System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
            }
        }
        if (!ada) {
            System.out.printf("  │ Tidak ada kamar tipe %-43s │%n", tipe + " yang tersedia.");
            System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
        }
        System.out.println("  └────────────┴──────────────┴─────────────────┴──────────┴────────────┘");
    }

    /**
     * Demo interface Laporan pada Kamar — mencetak ringkasan semua kamar.
     */
    public void cetakLaporanKamar() {
        System.out.println("\n--- LAPORAN RINGKASAN KAMAR ---");
        System.out.println("  [Interface: Laporan.getRingkasan()]");
        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────────");
        for (Kamar k : daftarKamar) {
            Laporan laporan = k; // Kamar implements Laporan
            System.out.println("  [" + laporan.getKategoriLaporan() + "] " + laporan.getRingkasan());
        }
        System.out.println("  ─────────────────────────────────────────────────────────────────────────────────────────");
    }

    public boolean adaKamarTersedia() {
        return daftarKamar.stream().anyMatch(Kamar::isTersedia);
    }

    public Kamar cariKamarByNomor(int nomor) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor && k.isTersedia()) return k;
        return null;
    }

    public Kamar cariKamarByNomor(int nomor, String tipe) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor && k.isTersedia()
                    && k.getTipeKamar().equalsIgnoreCase(tipe)) return k;
        return null;
    }
}
