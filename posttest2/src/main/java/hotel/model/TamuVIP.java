package hotel.model;

/**
 * TamuVIP adalah subkelas dari Tamu.
 *
 * Mendemonstrasikan akses ke field 'protected' (poinLoyalitas)
 * yang diwarisi dari kelas induk Tamu.
 */
public class TamuVIP extends Tamu {

    // === PRIVATE FIELD ===
    private String tingkatVIP; // "Silver", "Gold", "Platinum"
    private double diskonPersen;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public TamuVIP(int id, String nama, long noKtp, long noTelp, String tingkatVIP) {
        super(id, nama, noKtp, noTelp);
        this.tingkatVIP   = tingkatVIP;
        this.diskonPersen = tentukandiskon(tingkatVIP);
    }

    // ─── PRIVATE HELPER ──────────────────────────────────────────

    private double tentukandiskon(String tingkat) {
        return switch (tingkat.toUpperCase()) {
            case "SILVER"   -> 5.0;
            case "GOLD"     -> 10.0;
            case "PLATINUM" -> 20.0;
            default         -> 0.0;
        };
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public String getTingkatVIP()        { return tingkatVIP; }
    public double getDiskonPersen()      { return diskonPersen; }

    public void setTingkatVIP(String t) {
        this.tingkatVIP   = t;
        this.diskonPersen = tentukandiskon(t);
    }

    /**
     * Mengakses field PROTECTED 'poinLoyalitas' dari superclass Tamu.
     * Tamu VIP mendapat poin dua kali lipat.
     */
    public void tambahPoinDouble(int poin) {
        this.poinLoyalitas += poin * 2; // akses langsung ke protected field
    }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public double hitungHargaSetelahDiskon(double hargaAsli) {
        return hargaAsli * (1 - diskonPersen / 100.0);
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  VIP      : " + tingkatVIP);
        System.out.printf ("  Diskon   : %.0f%%%n", diskonPersen);
    }

    @Override
    public String toString() {
        return "[VIP-" + tingkatVIP + "] " + getNama() + " (ID: " + getId() + ")";
    }
}
