package hotel.model;

/**
 * KamarDeluxe adalah subclass (childclass) kedua dari Kamar.
 *
 * Tipe Inheritance: Single Inheritance (KamarDeluxe extends Kamar)
 *
 * KamarDeluxe mewarisi semua field dan method dari Kamar,
 * dan menambahkan atribut/perilaku khusus kamar deluxe:
 * - pemandangan  : jenis pemandangan kamar (misal: "Taman", "Kota", "Kolam Renang")
 * - Override getFasilitas() untuk menampilkan fasilitas khas deluxe
 */
public class KamarDeluxe extends Kamar {

    // === PRIVATE FIELD tambahan khusus KamarDeluxe ===
    private String pemandangan;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarDeluxe(int nomorKamar, double hargaPerMalam, String pemandangan) {
        // Memanggil constructor superclass Kamar
        // Kamar Deluxe: kapasitas 2 orang
        super(nomorKamar, "Deluxe", hargaPerMalam, 2);
        this.pemandangan = pemandangan;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public String getPemandangan()               { return pemandangan; }
    public void setPemandangan(String p)         { this.pemandangan = p; }

    // ─── OVERRIDE METHOD dari superclass Kamar ────────────────────

    /**
     * Override getFasilitas() — fasilitas khusus kamar deluxe.
     * Menggunakan super.getFasilitas() untuk mewarisi fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas(); // warisan dari Kamar
        return base + ", Mini Bar, Bathtub, Pemandangan " + pemandangan;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Pemandangan: " + pemandangan);
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Deluxe (" + pemandangan + ")";
    }
}
