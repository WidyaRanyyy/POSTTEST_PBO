package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: KamarStandard (concrete class dari abstract Kamar)
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Wajib mengimplementasikan abstract method dari Kamar:
 *       • getFasilitas() → fasilitas kamar standard
 *       • tampilInfo()   → tampilan info lengkap kamar standard
 *
 *  Polymorphism (tetap dari Posttest 4):
 *   Override : getFasilitas(), tampilInfo(), tampilInfo(boolean), toString()
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

    public boolean isSarapanIncluded()           { return sarapanIncluded; }
    public void    setSarapanIncluded(boolean v) { this.sarapanIncluded = v; }

    // ─── IMPLEMENTASI ABSTRACT METHODS ───────────────────────────

    /**
     * [Implementasi abstract getFasilitas() dari Kamar]
     * Fasilitas kamar standard: dasar + opsional sarapan.
     */
    @Override
    public String getFasilitas() {
        return "TV, AC, Kamar Mandi" + (sarapanIncluded ? ", Sarapan Gratis" : "");
    }

    /**
     * [Implementasi abstract tampilInfo() dari Kamar]
     * Menampilkan info kamar standard lengkap dengan info sarapan.
     */
    @Override
    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Sarapan   : " + (sarapanIncluded ? "Termasuk" : "Tidak Termasuk"));
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     * Override overload dari Kamar.tampilInfo(boolean).
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                getNomorKamar(), getTipeKamar(), getHargaPerMalam(), getKapasitasOrang(),
                isTersedia() ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Sarapan   : " + (sarapanIncluded ? "Termasuk" : "Tidak Termasuk"));
        if (tampilFasilitas) {
            System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        }
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Standard" + (sarapanIncluded ? " (B&B)" : "");
    }
}
