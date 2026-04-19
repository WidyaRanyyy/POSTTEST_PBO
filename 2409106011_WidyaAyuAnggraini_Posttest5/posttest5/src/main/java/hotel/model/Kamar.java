package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  ABSTRACT CLASS: Kamar
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Kelas Kamar diubah menjadi ABSTRACT CLASS
 *   - Ditambahkan ABSTRACT METHOD: getFasilitas() dan tampilInfo()
 *   - Implements interface Laporan (minimal 2 method)
 *
 *  Mengapa dijadikan abstract?
 *   Kamar tidak pernah diinstansiasi langsung — selalu berupa
 *   KamarStandard, KamarDeluxe, atau KamarSuite. Setiap tipe
 *   kamar WAJIB mendefinisikan fasilitas dan cara tampil sendiri.
 *
 *  Penerapan Abstract Method:
 *   - getFasilitas()  → wajib di-override oleh subclass
 *   - tampilInfo()    → wajib di-override oleh subclass
 *
 *  Implements: Laporan
 *   - getRingkasan()     → implementasi default di sini
 *   - cetakDetail()      → implementasi default, memanggil tampilInfo()
 *   - getKategoriLaporan() → override dari default Laporan
 *
 *  Polymorphism (tetap dari Posttest 4):
 *   Override : getFasilitas(), tampilInfo(), toString()
 *   Overload  : getFasilitas(boolean), tampilInfo(boolean)
 * ╚══════════════════════════════════════════════════════════════╝
 */
public abstract class Kamar implements Laporan {

    // === PRIVATE FIELDS ===
    private int     nomorKamar;
    private String  tipeKamar;
    private double  hargaPerMalam;
    private boolean tersedia;

    // === PROTECTED FIELD (dapat diakses subclass) ===
    protected int kapasitasOrang;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam, int kapasitasOrang) {
        this.nomorKamar     = nomorKamar;
        this.tipeKamar      = tipeKamar;
        this.hargaPerMalam  = validasiHarga(hargaPerMalam);
        this.tersedia       = true;
        this.kapasitasOrang = kapasitasOrang;
    }

    // ─── PRIVATE VALIDATION ──────────────────────────────────────

    private double validasiHarga(double harga) {
        if (harga < 0)
            throw new IllegalArgumentException("Harga kamar tidak boleh negatif.");
        return harga;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int     getNomorKamar()            { return nomorKamar; }
    public String  getTipeKamar()             { return tipeKamar; }
    public double  getHargaPerMalam()         { return hargaPerMalam; }
    public boolean isTersedia()               { return tersedia; }
    public int     getKapasitasOrang()        { return kapasitasOrang; }

    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
    public void setHargaPerMalam(double h)    { this.hargaPerMalam = validasiHarga(h); }

    // ─── ABSTRACT METHODS (WAJIB diimplementasikan subclass) ─────

    /**
     * [ABSTRACT METHOD - getFasilitas()]
     * Setiap tipe kamar WAJIB mendefinisikan fasilitas spesifiknya.
     * KamarStandard, KamarDeluxe, KamarSuite harus meng-override ini.
     *
     * @return String daftar fasilitas kamar
     */
    public abstract String getFasilitas();

    /**
     * [ABSTRACT METHOD - tampilInfo()]
     * Setiap tipe kamar WAJIB mendefinisikan cara menampilkan infonya.
     * Memastikan setiap subclass menyesuaikan tampilan dengan atribut uniknya.
     */
    public abstract void tampilInfo();

    // ─── CONCRETE METHODS ────────────────────────────────────────

    /**
     * [OVERLOAD - getFasilitas(boolean ringkas)]
     * Memanggil getFasilitas() abstract yang sudah diimplementasikan subclass.
     *
     * @param ringkas true = format satu baris, false = format per item dengan bullet
     */
    public String getFasilitas(boolean ringkas) {
        String fasilitas = getFasilitas();
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
     * [OVERLOAD - tampilInfo(boolean tampilFasilitas)]
     * Versi overload dengan parameter boolean untuk kontrol tampilan fasilitas.
     */
    public void tampilInfo(boolean tampilFasilitas) {
        tampilInfo(); // panggil abstract method yang sudah diimplementasikan subclass
        if (tampilFasilitas) {
            System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        }
    }

    @Override
    public String toString() {
        return nomorKamar + " - " + tipeKamar;
    }

    // ─── IMPLEMENTASI INTERFACE Laporan ──────────────────────────

    /**
     * [Laporan.getRingkasan()]
     * Ringkasan kamar dalam satu baris — cocok untuk tampilan daftar.
     */
    @Override
    public String getRingkasan() {
        return String.format("Kamar %d | %-10s | Rp %-12.0f | Kapasitas: %d | %s",
                nomorKamar, tipeKamar, hargaPerMalam, kapasitasOrang,
                tersedia ? "TERSEDIA" : "TERPAKAI");
    }

    /**
     * [Laporan.cetakDetail()]
     * Mencetak detail lengkap kamar — memanggil tampilInfo() yang sudah di-override subclass.
     */
    @Override
    public void cetakDetail() {
        System.out.println("  ══════════════════════════════════════");
        System.out.println("  [LAPORAN DETAIL KAMAR]");
        tampilInfo();
        System.out.println("  Fasilitas (detail): " + getFasilitas(false));
        System.out.println("  ══════════════════════════════════════");
    }

    /**
     * [Laporan.getKategoriLaporan()]
     * Override default method dari Laporan.
     */
    @Override
    public String getKategoriLaporan() {
        return "KAMAR";
    }
}
