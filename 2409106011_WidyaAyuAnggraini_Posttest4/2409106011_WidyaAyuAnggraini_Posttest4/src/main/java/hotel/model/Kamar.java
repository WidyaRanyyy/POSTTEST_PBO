package hotel.model;

/**
 * Kelas Kamar merepresentasikan data kamar hotel.
 *
 * Penerapan Inheritance:
 *  - Kelas ini berperan sebagai SUPERCLASS (parent class).
 *  - Subclass: KamarStandard, KamarDeluxe, KamarSuite
 *
 * Encapsulation: semua field private/protected, akses hanya melalui getter/setter.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM                                                  ║
 * ║  Override : getFasilitas(), tampilInfo(), toString()          ║
 * ║             di-override oleh KamarStandard/Deluxe/Suite       ║
 * ║  Overload  : getFasilitas(boolean ringkas)                    ║
 * ║              tampilInfo(boolean tampilFasilitas)              ║
 * ╚══════════════════════════════════════════════════════════════╝
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

    // ─── POLYMORPHISM: METHOD OVERRIDE ───────────────────────────

    /**
     * [OVERRIDE - getFasilitas() versi dasar]
     * Menampilkan fasilitas kamar dasar.
     * Di-override oleh KamarStandard, KamarDeluxe, KamarSuite
     * untuk menambahkan fasilitas spesifik masing-masing tipe.
     */
    public String getFasilitas() {
        return "TV, AC, Kamar Mandi";
    }

    // ─── POLYMORPHISM: METHOD OVERLOAD ───────────────────────────

    /**
     * [OVERLOAD - getFasilitas(boolean ringkas)]
     * Overload dari getFasilitas() dengan parameter berbeda.
     *
     * Logis karena: sistem hotel membutuhkan dua format tampilan fasilitas:
     *  - Ringkas (satu baris) untuk tampilan tabel/daftar kamar
     *  - Detail (per item) untuk tampilan informasi kamar lengkap
     *
     * @param ringkas true = format satu baris, false = format per baris dengan bullet
     */
    public String getFasilitas(boolean ringkas) {
        String fasilitas = getFasilitas(); // panggil versi override subclass
        if (ringkas) {
            return fasilitas;
        } else {
            String[] items = fasilitas.split(", ");
            StringBuilder sb = new StringBuilder();
            for (String item : items) {
                sb.append("\n    • ").append(item.trim());
            }
            return sb.toString();
        }
    }

    /**
     * [OVERRIDE - tampilInfo() versi dasar]
     * Menampilkan informasi kamar secara lengkap.
     * Di-override oleh subclass untuk menambahkan info spesifik.
     */
    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                nomorKamar, tipeKamar, hargaPerMalam, kapasitasOrang,
                tersedia ? "TERSEDIA" : "TERPAKAI");
        System.out.println("  Fasilitas : " + getFasilitas());
    }

    /**
     * [OVERLOAD - tampilInfo(boolean tampilFasilitas)]
     * Overload dari tampilInfo() dengan parameter berbeda.
     *
     * Logis karena: di tampilan daftar kamar cukup info singkat,
     * sedangkan tampilan detail reservasi butuh info fasilitas lengkap per item.
     *
     * @param tampilFasilitas true = tampilkan detail fasilitas per item (format detail)
     */
    public void tampilInfo(boolean tampilFasilitas) {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | Kapasitas: %d orang | %s%n",
                nomorKamar, tipeKamar, hargaPerMalam, kapasitasOrang,
                tersedia ? "TERSEDIA" : "TERPAKAI");
        if (tampilFasilitas) {
            System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        }
    }

    @Override
    public String toString() {
        return nomorKamar + " - " + tipeKamar;
    }
}
