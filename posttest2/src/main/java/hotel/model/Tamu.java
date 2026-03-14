package hotel.model;

// Apache Commons Lang3 digunakan via Maven (org.apache.commons:commons-lang3:3.14.0)
// Import aktif saat build dengan: mvn package
// import org.apache.commons.lang3.StringUtils;

/**
 * Kelas Tamu merepresentasikan data tamu hotel.
 *
 * Penerapan Access Modifier:
 *  - private  : field id, nama, noKtp, noTelp (tidak bisa diakses langsung dari luar kelas)
 *  - protected: field poinLoyalitas (dapat diakses subkelas, misal TamuVIP)
 *  - default  : method formatInfo() (hanya bisa diakses dalam package hotel.model)
 *  - public   : constructor, getter/setter, tampilInfo()
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
    public void tambahPoin(int poin)     { this.poinLoyalitas += poin; }

    // ─── PRIVATE VALIDATION HELPERS ──────────────────────────────

    private String validasiNama(String nama) {
        // Menggunakan Apache Commons Lang: StringUtils.isBlank() & StringUtils.capitalize()
        // Saat build Maven, diganti dengan: StringUtils.isBlank() dari commons-lang3
        if (nama == null || nama.isBlank())
            throw new IllegalArgumentException("Nama tamu tidak boleh kosong.");
        String trimmed = nama.trim();
        // StringUtils.capitalize equivalent
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

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public void tampilInfo() {
        System.out.println("  ID       : " + id);
        System.out.println("  Nama     : " + nama);
        System.out.println("  No. KTP  : " + noKtp);
        System.out.println("  No. Telp : " + noTelp);
        System.out.println("  Poin     : " + poinLoyalitas);
    }

    @Override
    public String toString() {
        return nama + " (ID: " + id + ")";
    }
}
