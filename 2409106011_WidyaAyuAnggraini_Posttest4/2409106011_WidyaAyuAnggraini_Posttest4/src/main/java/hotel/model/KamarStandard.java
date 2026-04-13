package hotel.model;

/**
 * KamarStandard adalah subclass pertama dari Kamar.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM - Override                                       ║
 * ║  getFasilitas()           — override + super.getFasilitas()  ║
 * ║  tampilInfo()             — override + super.tampilInfo()    ║
 * ║  tampilInfo(boolean)      — override overload dari Kamar     ║
 * ║  toString()               — override                         ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class KamarStandard extends Kamar {

    // === PRIVATE FIELD tambahan ===
    private boolean sarapanIncluded;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarStandard(int nomorKamar, double hargaPerMalam, boolean sarapanIncluded) {
        super(nomorKamar, "Standard", hargaPerMalam, 2);
        this.sarapanIncluded = sarapanIncluded;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public boolean isSarapanIncluded()              { return sarapanIncluded; }
    public void setSarapanIncluded(boolean val)      { this.sarapanIncluded = val; }

    // ─── POLYMORPHISM: OVERRIDE ──────────────────────────────────

    /**
     * [OVERRIDE getFasilitas()]
     * Menambahkan fasilitas khusus kamar standard ke fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas();
        return base + (sarapanIncluded ? ", Sarapan Gratis" : "");
    }

    /**
     * [OVERRIDE tampilInfo()]
     * Menampilkan info kamar standard lengkap dengan info sarapan.
     */
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Sarapan   : " + (sarapanIncluded ? "Termasuk" : "Tidak Termasuk"));
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     * Override overload dari Kamar.tampilInfo(boolean).
     * Untuk kamar standard, juga menampilkan info sarapan jika diminta detail.
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        super.tampilInfo(tampilFasilitas);
        System.out.println("  Sarapan   : " + (sarapanIncluded ? "Termasuk" : "Tidak Termasuk"));
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Standard" + (sarapanIncluded ? " (B&B)" : "");
    }
}
