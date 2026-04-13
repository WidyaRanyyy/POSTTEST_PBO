package hotel.model;

/**
 * KamarDeluxe adalah subclass kedua dari Kamar.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM - Override                                       ║
 * ║  getFasilitas()           — override + super.getFasilitas()  ║
 * ║  tampilInfo()             — override + super.tampilInfo()    ║
 * ║  tampilInfo(boolean)      — override overload dari Kamar     ║
 * ║  toString()               — override                         ║
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

    public String getPemandangan()               { return pemandangan; }
    public void setPemandangan(String p)         { this.pemandangan = p; }

    // ─── POLYMORPHISM: OVERRIDE ──────────────────────────────────

    /**
     * [OVERRIDE getFasilitas()]
     * Menambahkan fasilitas khusus kamar deluxe ke fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas();
        return base + ", Mini Bar, Bathtub, Pemandangan " + pemandangan;
    }

    /**
     * [OVERRIDE tampilInfo()]
     * Menampilkan info kamar deluxe lengkap dengan info pemandangan.
     */
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Pemandangan: " + pemandangan);
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     * Override overload dari Kamar.tampilInfo(boolean).
     * Untuk kamar deluxe, juga menampilkan info pemandangan.
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        super.tampilInfo(tampilFasilitas);
        System.out.println("  Pemandangan: " + pemandangan);
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Deluxe (" + pemandangan + ")";
    }
}
