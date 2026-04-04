package hotel.service;

import hotel.model.Kamar;
import hotel.model.KamarDeluxe;
import hotel.model.KamarStandard;
import hotel.model.KamarSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data kamar hotel.
 * Menggunakan subclass-subclass Kamar: KamarStandard, KamarDeluxe, KamarSuite.
 */
public class KamarService {

    // === PRIVATE FIELD ===
    private final List<Kamar> daftarKamar = new ArrayList<>();

    public KamarService() {
        inisialisasiKamar();
    }

    private void inisialisasiKamar() {
        // Menggunakan subclass KamarStandard (single inheritance dari Kamar)
        daftarKamar.add(new KamarStandard(101, 350_000, false));
        daftarKamar.add(new KamarStandard(102, 400_000, true));   // B&B (include sarapan)

        // Menggunakan subclass KamarDeluxe (single inheritance dari Kamar)
        daftarKamar.add(new KamarDeluxe(201, 600_000, "Taman"));
        daftarKamar.add(new KamarDeluxe(202, 650_000, "Kolam Renang"));

        // Menggunakan subclass KamarSuite (single inheritance dari Kamar)
        daftarKamar.add(new KamarSuite(301, 1_200_000, 2, false));
        daftarKamar.add(new KamarSuite(302, 2_000_000, 3, true)); // dengan butler
    }

    // ─── PUBLIC METHODS ───────────────────────────────────────────

    public void tampilKamarTersedia() {
        System.out.println("\n  ┌────────────┬──────────────┬─────────────────┬──────────┬────────────┐");
        System.out.println("  │ No. Kamar  │ Tipe         │ Harga/Malam     │ Kapasitas│ Status     │");
        System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
        for (Kamar k : daftarKamar) {
            if (k.isTersedia()) {
                System.out.printf("  │ %-10d │ %-12s │ Rp %-12.0f │ %-8d │ %-10s │%n",
                        k.getNomorKamar(), k.getTipeKamar(),
                        k.getHargaPerMalam(), k.getKapasitasOrang(), "TERSEDIA");
                System.out.printf("  │            │ Fasilitas: %-53s │%n", k.getFasilitas());
                System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
            }
        }
        System.out.println("  └────────────┴──────────────┴─────────────────┴──────────┴────────────┘");
    }

    public boolean adaKamarTersedia() {
        return daftarKamar.stream().anyMatch(Kamar::isTersedia);
    }

    public Kamar cariKamarByNomor(int nomor) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor && k.isTersedia()) return k;
        return null;
    }
}
