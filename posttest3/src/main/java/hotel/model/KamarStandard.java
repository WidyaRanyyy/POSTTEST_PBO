package hotel.model;

/**
 * KamarStandard adalah subclass (childclass) pertama dari Kamar.
 *
 * Tipe Inheritance: Single Inheritance (KamarStandard extends Kamar)
 *
 * KamarStandard mewarisi semua field dan method dari Kamar,
 * dan menambahkan atribut/perilaku khusus kamar standard:
 * - sarapanIncluded : apakah harga termasuk sarapan
 * - Override getFasilitas() untuk menampilkan fasilitas khas standard
 */
public class KamarStandard extends Kamar {

    // === PRIVATE FIELD tambahan khusus KamarStandard ===
    private boolean sarapanIncluded;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarStandard(int nomorKamar, double hargaPerMalam, boolean sarapanIncluded) {
        // Memanggil constructor superclass Kamar
        // Kamar Standard: kapasitas 2 orang
        super(nomorKamar, "Standard", hargaPerMalam, 2);
        this.sarapanIncluded = sarapanIncluded;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public boolean isSarapanIncluded()              { return sarapanIncluded; }
    public void setSarapanIncluded(boolean val)      { this.sarapanIncluded = val; }

    // ─── OVERRIDE METHOD dari superclass Kamar ────────────────────

    /**
     * Override getFasilitas() — fasilitas khusus kamar standard.
     * Menggunakan super.getFasilitas() untuk mewarisi fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas(); // warisan dari Kamar
        return base + (sarapanIncluded ? ", Sarapan Gratis" : "");
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Sarapan   : " + (sarapanIncluded ? "Termasuk" : "Tidak Termasuk"));
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Standard" + (sarapanIncluded ? " (B&B)" : "");
    }
}
