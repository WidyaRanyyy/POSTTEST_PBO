package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: KamarDeluxe (concrete class dari abstract Kamar)
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Wajib mengimplementasikan abstract method dari Kamar:
 *       • getFasilitas() → fasilitas kamar deluxe
 *       • tampilInfo()   → tampilan info lengkap kamar deluxe
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class KamarDeluxe extends Kamar {

    // === PRIVATE FIELD tambahan ===
    private String pemandangan;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarDeluxe(int nomorKamar, double hargaPerMalam, String pemandangan) {
        super(nomorKamar, "Deluxe", hargaPerMalam, 2);
        this.pemandangan = pemandangan;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public String getPemandangan()           { return pemandangan; }
    public void   setPemandangan(String p)   { this.pemandangan = p; }

    // ─── IMPLEMENTASI ABSTRACT METHODS ───────────────────────────

    /**
     * [Implementasi abstract getFasilitas() dari Kamar]
     * Fasilitas kamar deluxe: dasar + mini bar, bathtub, pemandangan.
     */
    @Override
    public String getFasilitas() {
        return "TV, AC, Kamar Mandi, Mini Bar, Bathtub, Pemandangan " + pemandangan;
    }

    /**
     * [Implementasi abstract tampilInfo() dari Kamar]
     * Menampilkan info kamar deluxe dengan info pemandangan.
     */
    @Override
    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Pemandangan: " + pemandangan);
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Pemandangan: " + pemandangan);
        if (tampilFasilitas) {
            System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        }
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Deluxe (" + pemandangan + ")";
    }
}
