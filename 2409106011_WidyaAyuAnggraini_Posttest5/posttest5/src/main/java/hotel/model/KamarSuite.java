package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: KamarSuite (concrete class dari abstract Kamar)
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Wajib mengimplementasikan abstract method dari Kamar:
 *       • getFasilitas() → fasilitas kamar suite
 *       • tampilInfo()   → tampilan info lengkap kamar suite
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class KamarSuite extends Kamar {

    // === PRIVATE FIELD tambahan ===
    private int     jumlahRuangan;
    private boolean termasukButler;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarSuite(int nomorKamar, double hargaPerMalam, int jumlahRuangan, boolean termasukButler) {
        super(nomorKamar, "Suite", hargaPerMalam, 4);
        this.jumlahRuangan  = jumlahRuangan;
        this.termasukButler = termasukButler;
        this.kapasitasOrang = jumlahRuangan * 2;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int     getJumlahRuangan()           { return jumlahRuangan; }
    public boolean isTermasukButler()           { return termasukButler; }
    public void    setTermasukButler(boolean b) { this.termasukButler = b; }

    // ─── IMPLEMENTASI ABSTRACT METHODS ───────────────────────────

    /**
     * [Implementasi abstract getFasilitas() dari Kamar]
     * Fasilitas kamar suite: dasar + jacuzzi, dapur, butler (opsional).
     */
    @Override
    public String getFasilitas() {
        String fasilitas = "TV, AC, Kamar Mandi, Jacuzzi, Dapur Kecil, Ruang Tamu, "
                + jumlahRuangan + " Ruangan";
        if (termasukButler) {
            fasilitas += ", Layanan Butler 24 Jam";
        }
        return fasilitas;
    }

    /**
     * [Implementasi abstract tampilInfo() dari Kamar]
     * Menampilkan info kamar suite dengan info ruangan dan butler.
     */
    @Override
    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Ruangan    : " + jumlahRuangan + " ruangan");
        System.out.println("  Butler     : " + (termasukButler ? "Tersedia" : "Tidak Ada"));
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Ruangan    : " + jumlahRuangan + " ruangan");
        System.out.println("  Butler     : " + (termasukButler ? "Tersedia" : "Tidak Ada"));
        if (tampilFasilitas) {
            System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        }
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Suite (" + jumlahRuangan + " ruangan)";
    }
}
