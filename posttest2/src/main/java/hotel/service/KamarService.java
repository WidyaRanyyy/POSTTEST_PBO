package hotel.service;

import hotel.model.Kamar;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data kamar hotel.
 */
public class KamarService {

    // === PRIVATE FIELD ===
    private final List<Kamar> daftarKamar = new ArrayList<>();

    public KamarService() {
        inisialisasiKamar();
    }

    private void inisialisasiKamar() {
        daftarKamar.add(new Kamar(101, "Standard", 350_000));
        daftarKamar.add(new Kamar(102, "Standard", 350_000));
        daftarKamar.add(new Kamar(201, "Deluxe",   600_000));
        daftarKamar.add(new Kamar(202, "Deluxe",   600_000));
        daftarKamar.add(new Kamar(301, "Suite",  1_200_000));
    }

    // ─── PUBLIC METHODS ───────────────────────────────────────────

    public void tampilKamarTersedia() {
        System.out.println("\n  ┌────────────┬──────────────┬─────────────────┬──────────┐");
        System.out.println("  │ No. Kamar  │ Tipe         │ Harga/Malam     │ Status   │");
        System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┤");
        for (Kamar k : daftarKamar) {
            if (k.isTersedia()) {
                System.out.printf("  │ %-10d │ %-12s │ Rp %-12.0f │ %-8s │%n",
                        k.getNomorKamar(), k.getTipeKamar(),
                        k.getHargaPerMalam(), "TERSEDIA");
            }
        }
        System.out.println("  └────────────┴──────────────┴─────────────────┴──────────┘");
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
