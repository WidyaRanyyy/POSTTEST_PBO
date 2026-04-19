package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: TamuVIP (concrete class dari abstract Tamu)
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Wajib mengimplementasikan abstract method dari Tamu:
 *       • tampilInfo()    → override menambahkan info VIP
 *       • getStatusTamu() → mengembalikan "VIP-[Tingkat]"
 *
 *  Polymorphism (tetap dari Posttest 4):
 *   Override : tampilInfo(), toString()
 *   Override : tambahPoin(int, String) → poin VIP 2x
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class TamuVIP extends Tamu {

    // === PRIVATE FIELD ===
    private String tingkatVIP;   // "Silver", "Gold", "Platinum"
    private double diskonPersen;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public TamuVIP(int id, String nama, long noKtp, long noTelp, String tingkatVIP) {
        super(id, nama, noKtp, noTelp);
        this.tingkatVIP   = tingkatVIP;
        this.diskonPersen = tentukanDiskon(tingkatVIP);
    }

    // ─── PRIVATE HELPER ──────────────────────────────────────────

    private double tentukanDiskon(String tingkat) {
        return switch (tingkat.toUpperCase()) {
            case "SILVER"   -> 5.0;
            case "GOLD"     -> 10.0;
            case "PLATINUM" -> 20.0;
            default         -> 0.0;
        };
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public String getTingkatVIP()      { return tingkatVIP; }
    public double getDiskonPersen()    { return diskonPersen; }

    public void setTingkatVIP(String t) {
        this.tingkatVIP   = t;
        this.diskonPersen = tentukanDiskon(t);
    }

    // ─── IMPLEMENTASI ABSTRACT METHODS ───────────────────────────

    /**
     * [OVERRIDE/Implementasi tampilInfo() dari abstract Tamu]
     * Menampilkan info tamu VIP lengkap dengan data diskon dan tingkat.
     */
    @Override
    public void tampilInfo() {
        System.out.println("  ID       : " + getId());
        System.out.println("  Nama     : " + getNama());
        System.out.println("  No. KTP  : " + getNoKtp());
        System.out.println("  No. Telp : " + getNoTelp());
        System.out.println("  Poin     : " + getPoinLoyalitas());
        System.out.println("  Status   : " + getStatusTamu());
        System.out.println("  VIP      : " + tingkatVIP);
        System.out.printf ("  Diskon   : %.0f%%%n", diskonPersen);
    }

    /**
     * [Implementasi abstract getStatusTamu() dari Tamu]
     * TamuVIP mengembalikan status dengan tingkatannya.
     */
    @Override
    public String getStatusTamu() {
        return "VIP-" + tingkatVIP;
    }

    // ─── POLYMORPHISM: OVERRIDE tambahPoin ───────────────────────

    /**
     * [OVERRIDE versi overload tambahPoin(int, String)]
     * Tamu VIP mendapat 2x poin dari setiap aktivitas.
     */
    @Override
    public void tambahPoin(int poin, String alasan) {
        int poinVIP = poin * 2;
        this.poinLoyalitas += poinVIP;
        System.out.printf("  [+] Poin +%d (x2 VIP %s) untuk '%s' | Alasan: %s | Total: %d poin%n",
                poinVIP, tingkatVIP, getNama(), alasan, poinLoyalitas);
    }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public double hitungHargaSetelahDiskon(double hargaAsli) {
        return hargaAsli * (1 - diskonPersen / 100.0);
    }

    @Override
    public String toString() {
        return "[VIP-" + tingkatVIP + "] " + getNama() + " (ID: " + getId() + ")";
    }
}
