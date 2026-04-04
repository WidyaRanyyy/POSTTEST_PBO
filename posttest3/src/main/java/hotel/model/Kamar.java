package hotel.model;

/**
 * Kelas Kamar merepresentasikan data kamar hotel.
 *
 * Penerapan Inheritance:
 *  - Kelas ini berperan sebagai SUPERCLASS (parent class).
 *  - Subclass: KamarStandard, KamarDeluxe, KamarSuite
 *
 * Encapsulation: semua field private/protected, akses hanya melalui getter/setter.
 */
public class Kamar {

    // === PRIVATE FIELDS ===
    private int    nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private boolean tersedia;

    // === PROTECTED FIELD (dapat diakses subclass) ===
    protected int kapasitasOrang;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam, int kapasitasOrang) {
        this.nomorKamar      = nomorKamar;
        this.tipeKamar       = tipeKamar;
        this.hargaPerMalam   = validasiHarga(hargaPerMalam);
        this.tersedia        = true;
        this.kapasitasOrang  = kapasitasOrang;
    }

    // ─── PRIVATE VALIDATION ──────────────────────────────────────

    private double validasiHarga(double harga) {
        if (harga < 0)
            throw new IllegalArgumentException("Harga kamar tidak boleh negatif.");
        return harga;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int     getNomorKamar()              { return nomorKamar; }
    public String  getTipeKamar()               { return tipeKamar; }
    public double  getHargaPerMalam()           { return hargaPerMalam; }
    public boolean isTersedia()                 { return tersedia; }
    public int     getKapasitasOrang()          { return kapasitasOrang; }

    public void setTersedia(boolean tersedia)   { this.tersedia = tersedia; }
    public void setHargaPerMalam(double h)      { this.hargaPerMalam = validasiHarga(h); }

    // ─── PUBLIC METHOD (dapat di-override subclass) ───────────────

    /**
     * Menampilkan fasilitas kamar.
     * Method ini di-override oleh setiap subclass untuk menambahkan fasilitas spesifik.
     */
    public String getFasilitas() {
        return "TV, AC, Kamar Mandi";
    }

    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                nomorKamar, tipeKamar, hargaPerMalam, kapasitasOrang,
                tersedia ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Fasilitas : " + getFasilitas());
    }

    @Override
    public String toString() {
        return nomorKamar + " - " + tipeKamar;
    }
}
