package hotel.model;

/**
 * TamuVIP adalah subkelas dari Tamu.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM - Override                                       ║
 * ║  tampilInfo()  — override + super.tampilInfo()               ║
 * ║  toString()    — override untuk menampilkan label VIP        ║
 * ║  tambahPoin(int poin, String alasan) — override versi        ║
 * ║     overload dari Tamu: poin VIP otomatis dilipatgandakan    ║
 * ╚══════════════════════════════════════════════════════════════╝
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

    // ─── POLYMORPHISM: OVERRIDE tambahPoin(int poin, String alasan) ──

    /**
     * [OVERRIDE versi 2 dari tambahPoin(int poin, String alasan)]
     * Override terhadap overload tambahPoin(int, String) di superclass Tamu.
     *
     * Logis karena: Tamu VIP secara otomatis mendapat 2x poin dari setiap
     * aktivitas dibandingkan tamu biasa — perilaku yang berbeda dari superclass.
     *
     * @param poin   jumlah poin dasar (akan dilipatgandakan untuk VIP)
     * @param alasan keterangan alasan penambahan poin
     */
    @Override
    public void tambahPoin(int poin, String alasan) {
        int poinVIP = poin * 2; // VIP mendapat 2x poin
        this.poinLoyalitas += poinVIP; // akses protected field
        System.out.printf("  [+] Poin +%d (x2 VIP %s) untuk '%s' | Alasan: %s | Total: %d poin%n",
                poinVIP, tingkatVIP, getNama(), alasan, poinLoyalitas);
    }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public double hitungHargaSetelahDiskon(double hargaAsli) {
        return hargaAsli * (1 - diskonPersen / 100.0);
    }

    // ─── POLYMORPHISM: OVERRIDE tampilInfo() ─────────────────────

    /**
     * [OVERRIDE tampilInfo()]
     * Override dari Tamu.tampilInfo() — menambahkan info VIP di bawah info dasar.
     */
    @Override
    public void tampilInfo() {
        super.tampilInfo(); // panggil tampilInfo() milik Tamu
        System.out.println("  VIP      : " + tingkatVIP);
        System.out.printf ("  Diskon   : %.0f%%%n", diskonPersen);
    }

    /**
     * [OVERRIDE toString()]
     * Override dari Tamu.toString() — menambahkan label VIP pada string representasi.
     */
    @Override
    public String toString() {
        return "[VIP-" + tingkatVIP + "] " + getNama() + " (ID: " + getId() + ")";
    }
}
