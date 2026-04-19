package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  ABSTRACT CLASS: Tamu
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Kelas Tamu diubah menjadi ABSTRACT CLASS
 *   - Ditambahkan ABSTRACT METHOD: tampilInfo() dan getStatusTamu()
 *   - Implements interface Laporan (minimal 2 method)
 *
 *  Mengapa dijadikan abstract?
 *   Sistem hotel membedakan tamu biasa dan tamu VIP dengan perilaku
 *   yang berbeda (diskon, poin, tampilan). Setiap jenis tamu WAJIB
 *   mendefinisikan cara tampil dan status mereka sendiri.
 *
 *  Abstract Methods:
 *   - tampilInfo()    → wajib di-override (Tamu biasa vs TamuVIP beda tampilannya)
 *   - getStatusTamu() → wajib di-override (mengembalikan label status tamu)
 *
 *  Implements: Laporan
 *   - getRingkasan()        → implementasi di sini
 *   - cetakDetail()         → implementasi di sini
 *   - getKategoriLaporan()  → override dari default Laporan
 *
 *  Polymorphism (tetap dari Posttest 4):
 *   Override : tampilInfo(), toString() di TamuVIP
 *   Overload  : tambahPoin(int) vs tambahPoin(int, String)
 * ╚══════════════════════════════════════════════════════════════╝
 */
public abstract class Tamu implements Laporan {

    // === PRIVATE FIELDS ===
    private int    id;
    private String nama;
    private long   noKtp;
    private long   noTelp;

    // === PROTECTED FIELD (dapat diakses subclass TamuVIP) ===
    protected int poinLoyalitas;

    // === STATIC COUNTER ===
    static int totalTamuDibuat = 0;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Tamu() {
        this.id            = 0;
        this.nama          = "Anomali";
        this.noKtp         = 0L;
        this.noTelp        = 0L;
        this.poinLoyalitas = 0;
        totalTamuDibuat++;
    }

    public Tamu(int id, String nama, long noKtp, long noTelp) {
        this.id            = id;
        this.nama          = validasiNama(nama);
        this.noKtp         = validasiNoKtp(noKtp);
        this.noTelp        = validasiNoTelp(noTelp);
        this.poinLoyalitas = 0;
        totalTamuDibuat++;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int    getId()                  { return id; }
    public void   setId(int id)            { this.id = id; }

    public String getNama()                { return nama; }
    public void   setNama(String nama)     { this.nama = validasiNama(nama); }

    public long   getNoKtp()               { return noKtp; }
    public void   setNoKtp(long noKtp)     { this.noKtp = validasiNoKtp(noKtp); }

    public long   getNoTelp()              { return noTelp; }
    public void   setNoTelp(long noTelp)   { this.noTelp = validasiNoTelp(noTelp); }

    public int    getPoinLoyalitas()       { return poinLoyalitas; }

    // ─── ABSTRACT METHODS (WAJIB diimplementasikan subclass) ─────

    /**
     * [ABSTRACT METHOD - tampilInfo()]
     * Setiap jenis tamu WAJIB mendefinisikan cara menampilkan informasinya.
     * TamuBiasa menampilkan info dasar, TamuVIP menambahkan info VIP.
     */
    public abstract void tampilInfo();

    /**
     * [ABSTRACT METHOD - getStatusTamu()]
     * Setiap jenis tamu WAJIB mengembalikan label statusnya.
     * Contoh: TamuBiasa → "Reguler", TamuVIP → "VIP-Gold"
     *
     * @return String label status tamu
     */
    public abstract String getStatusTamu();

    // ─── CONCRETE METHODS ────────────────────────────────────────

    /**
     * [OVERLOAD - tambahPoin() versi 1]
     * Menambah poin loyalitas tanpa keterangan alasan.
     */
    public void tambahPoin(int poin) {
        if (poin < 0) throw new IllegalArgumentException("Poin tidak boleh negatif.");
        this.poinLoyalitas += poin;
    }

    /**
     * [OVERLOAD - tambahPoin() versi 2]
     * Overload dari tambahPoin(int) dengan tambahan parameter alasan.
     */
    public void tambahPoin(int poin, String alasan) {
        if (poin < 0) throw new IllegalArgumentException("Poin tidak boleh negatif.");
        this.poinLoyalitas += poin;
        System.out.printf("  [+] Poin +%d untuk '%s' | Alasan: %s | Total: %d poin%n",
                poin, nama, alasan, poinLoyalitas);
    }

    // ─── PACKAGE-PRIVATE METHOD ──────────────────────────────────

    String formatInfo() {
        return String.format("[%d] %s | KTP: %d | Telp: %d | Poin: %d | Status: %s",
                id, nama, noKtp, noTelp, poinLoyalitas, getStatusTamu());
    }

    @Override
    public String toString() {
        return nama + " (ID: " + id + ")";
    }

    // ─── PRIVATE VALIDATION HELPERS ──────────────────────────────

    private String validasiNama(String nama) {
        if (nama == null || nama.isBlank())
            throw new IllegalArgumentException("Nama tamu tidak boleh kosong.");
        String trimmed = nama.trim();
        return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1);
    }

    private long validasiNoKtp(long ktp) {
        if (ktp <= 0)
            throw new IllegalArgumentException("No. KTP harus lebih dari 0.");
        return ktp;
    }

    private long validasiNoTelp(long telp) {
        if (telp <= 0)
            throw new IllegalArgumentException("No. Telp harus lebih dari 0.");
        return telp;
    }

    // ─── IMPLEMENTASI INTERFACE Laporan ──────────────────────────

    /**
     * [Laporan.getRingkasan()]
     * Ringkasan tamu dalam satu baris — memanggil getStatusTamu() abstract.
     */
    @Override
    public String getRingkasan() {
        return String.format("[%d] %-20s | KTP: %-16d | Poin: %-6d | %s",
                id, nama, noKtp, poinLoyalitas, getStatusTamu());
    }

    /**
     * [Laporan.cetakDetail()]
     * Mencetak detail lengkap tamu — memanggil tampilInfo() abstract.
     */
    @Override
    public void cetakDetail() {
        System.out.println("  ══════════════════════════════════════");
        System.out.println("  [LAPORAN DETAIL TAMU]");
        tampilInfo();
        System.out.println("  ══════════════════════════════════════");
    }

    /**
     * [Laporan.getKategoriLaporan()]
     * Override default method dari Laporan.
     */
    @Override
    public String getKategoriLaporan() {
        return "TAMU";
    }
}
