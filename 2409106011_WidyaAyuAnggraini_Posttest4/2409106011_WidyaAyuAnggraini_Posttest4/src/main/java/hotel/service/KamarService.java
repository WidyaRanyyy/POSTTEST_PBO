package hotel.service;

import hotel.model.Kamar;
import hotel.model.KamarDeluxe;
import hotel.model.KamarStandard;
import hotel.model.KamarSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Service untuk manajemen data kamar hotel.
 *
 * Memanfaatkan Polymorphism:
 *  - getFasilitas()              — override di tiap subclass Kamar
 *  - getFasilitas(boolean)       — overload, digunakan untuk tampilan ringkas/detail
 *  - cariKamarByNomor(int)       — satu versi (cari by nomor saja)
 *  - cariKamarByNomor(int,String)— OVERLOAD: cari by nomor + filter tipe kamar
 */
public class KamarService {

    // === PRIVATE FIELD ===
    private final List<Kamar> daftarKamar = new ArrayList<>();

    public KamarService() {
        inisialisasiKamar();
    }

    private void inisialisasiKamar() {
        daftarKamar.add(new KamarStandard(101, 350_000, false));
        daftarKamar.add(new KamarStandard(102, 400_000, true));
        daftarKamar.add(new KamarDeluxe(201, 600_000, "Taman"));
        daftarKamar.add(new KamarDeluxe(202, 650_000, "Kolam Renang"));
        daftarKamar.add(new KamarSuite(301, 1_200_000, 2, false));
        daftarKamar.add(new KamarSuite(302, 2_000_000, 3, true));
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
                // getFasilitas(true) = overload ringkas untuk tampilan tabel
                System.out.printf("  │            │ Fasilitas: %-53s │%n", k.getFasilitas(true));
                System.out.println("  ├────────────┼──────────────┼─────────────────┼──────────┼────────────┤");
            }
        }
        System.out.println("  └────────────┴──────────────┴─────────────────┴──────────┴────────────┘");
    }

    /**
     * Menampilkan kamar tersedia berdasarkan tipe tertentu.
     * Memanfaatkan getFasilitas(true) untuk format ringkas.
     *
     * @param tipe tipe kamar yang ingin ditampilkan ("Standard", "Deluxe", "Suite")
     */
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

    public boolean adaKamarTersedia() {
        return daftarKamar.stream().anyMatch(Kamar::isTersedia);
    }

    /**
     * [OVERLOAD - cariKamarByNomor() versi 1]
     * Mencari kamar yang tersedia berdasarkan nomor kamar saja.
     *
     * @param nomor nomor kamar yang dicari
     */
    public Kamar cariKamarByNomor(int nomor) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor && k.isTersedia()) return k;
        return null;
    }

    /**
     * [OVERLOAD - cariKamarByNomor() versi 2]
     * Overload dari cariKamarByNomor(int) dengan tambahan filter tipe kamar.
     *
     * Logis karena: operator hotel perlu memverifikasi bahwa nomor kamar
     * yang dimasukkan sesuai dengan tipe yang dipesan oleh tamu — mencegah
     * penginputan nomor kamar yang salah tipe.
     *
     * @param nomor nomor kamar yang dicari
     * @param tipe  tipe kamar yang diharapkan ("Standard", "Deluxe", "Suite")
     */
    public Kamar cariKamarByNomor(int nomor, String tipe) {
        for (Kamar k : daftarKamar)
            if (k.getNomorKamar() == nomor && k.isTersedia()
                    && k.getTipeKamar().equalsIgnoreCase(tipe)) return k;
        return null;
    }
}
