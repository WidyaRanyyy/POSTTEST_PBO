package hotel.model;

/**
 * Kelas Tamu merepresentasikan data tamu hotel.
 *
 * Penerapan Access Modifier:
 *  - private  : field id, nama, noKtp, noTelp
 *  - protected: field poinLoyalitas (dapat diakses subkelas TamuVIP)
 *  - default  : method formatInfo() (hanya bisa diakses dalam package hotel.model)
 *  - public   : constructor, getter/setter, tampilInfo()
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM                                                  ║
 * ║  Override : tampilInfo(), toString() di-override TamuVIP     ║
 * ║  Overload  : tambahPoin(int)                                 ║
 * ║              tambahPoin(int poin, String alasan)             ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class Tamu {

    // === PRIVATE FIELDS (Encapsulation) ===
    private int    id;
    private String nama;
    private long   noKtp;
    private long   noTelp;

    // === PROTECTED FIELD (accessible by subclass TamuVIP) ===
    protected int poinLoyalitas;

    // === STATIC COUNTER (package-private / default) ===
    static int totalTamuDibuat = 0;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    /** Non-argument constructor */
    public Tamu() {
        this.id             = 0;
        this.nama           = "Anomali";
        this.noKtp          = 0L;
        this.noTelp         = 0L;
        this.poinLoyalitas  = 0;
        totalTamuDibuat++;
    }

    /** Parameterized constructor */
    public Tamu(int id, String nama, long noKtp, long noTelp) {
        this.id             = id;
        this.nama           = validasiNama(nama);
        this.noKtp          = validasiNoKtp(noKtp);
        this.noTelp         = validasiNoTelp(noTelp);
        this.poinLoyalitas  = 0;
        totalTamuDibuat++;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int getId()                   { return id; }
    public void setId(int id)            { this.id = id; }

    public String getNama()              { return nama; }
    public void setNama(String nama)     { this.nama = validasiNama(nama); }

    public long getNoKtp()               { return noKtp; }
    public void setNoKtp(long noKtp)     { this.noKtp = validasiNoKtp(noKtp); }

    public long getNoTelp()              { return noTelp; }
    public void setNoTelp(long noTelp)   { this.noTelp = validasiNoTelp(noTelp); }

    public int getPoinLoyalitas()        { return poinLoyalitas; }

    // ─── POLYMORPHISM: METHOD OVERLOAD ───────────────────────────

    /**
     * [OVERLOAD - tambahPoin() versi 1]
     * Menambah poin loyalitas tanpa keterangan alasan.
     *
     * @param poin jumlah poin yang ditambahkan
     */
    public void tambahPoin(int poin) {
        if (poin < 0) throw new IllegalArgumentException("Poin tidak boleh negatif.");
        this.poinLoyalitas += poin;
    }

    /**
     * [OVERLOAD - tambahPoin() versi 2]
     * Overload dari tambahPoin(int) dengan tambahan parameter alasan.
     *
     * Logis karena: sistem hotel perlu mencatat alasan penambahan poin
     * (misalnya: dari reservasi, dari event promosi, dari ulasan positif)
     * agar bisa ditampilkan ke tamu sebagai riwayat reward.
     *
     * @param poin   jumlah poin yang ditambahkan
     * @param alasan keterangan alasan penambahan poin (misal: "Reservasi 3 malam")
     */
    public void tambahPoin(int poin, String alasan) {
        if (poin < 0) throw new IllegalArgumentException("Poin tidak boleh negatif.");
        this.poinLoyalitas += poin;
        System.out.printf("  [+] Poin +%d untuk '%s' | Alasan: %s | Total: %d poin%n",
                poin, nama, alasan, poinLoyalitas);
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

    // ─── DEFAULT (PACKAGE-PRIVATE) METHOD ────────────────────────

    /** Hanya bisa dipanggil dalam package hotel.model */
    String formatInfo() {
        return String.format("[%d] %s | KTP: %d | Telp: %d | Poin: %d",
                id, nama, noKtp, noTelp, poinLoyalitas);
    }

    // ─── POLYMORPHISM: METHOD OVERRIDE ───────────────────────────

    /**
     * [OVERRIDE - tampilInfo() versi dasar]
     * Menampilkan informasi tamu.
     * Di-override oleh TamuVIP untuk menambahkan info VIP.
     */
    public void tampilInfo() {
        System.out.println("  ID       : " + id);
        System.out.println("  Nama     : " + nama);
        System.out.println("  No. KTP  : " + noKtp);
        System.out.println("  No. Telp : " + noTelp);
        System.out.println("  Poin     : " + poinLoyalitas);
    }

    /**
     * [OVERRIDE - toString() versi dasar]
     * Di-override oleh TamuVIP untuk menambahkan label VIP.
     */
    @Override
    public String toString() {
        return nama + " (ID: " + id + ")";
    }
}
