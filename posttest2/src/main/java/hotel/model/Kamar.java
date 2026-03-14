package hotel.model;

/**
 * Kelas Kamar merepresentasikan data kamar hotel.
 * Encapsulation: semua field private, akses hanya melalui getter/setter.
 */
public class Kamar {

    // === PRIVATE FIELDS ===
    private int    nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private boolean tersedia;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar   = nomorKamar;
        this.tipeKamar    = tipeKamar;
        this.hargaPerMalam = validasiHarga(hargaPerMalam);
        this.tersedia     = true;
    }

    // ─── PRIVATE VALIDATION ──────────────────────────────────────

    private double validasiHarga(double harga) {
        if (harga < 0)
            throw new IllegalArgumentException("Harga kamar tidak boleh negatif.");
        return harga;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int getNomorKamar()               { return nomorKamar; }
    public String getTipeKamar()             { return tipeKamar; }
    public double getHargaPerMalam()         { return hargaPerMalam; }
    public boolean isTersedia()              { return tersedia; }

    public void setTersedia(boolean tersedia){ this.tersedia = tersedia; }
    public void setHargaPerMalam(double h)   { this.hargaPerMalam = validasiHarga(h); }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public void tampilInfo() {
        System.out.printf("  No. %-4d | %-10s | Rp %-12.0f | %s%n",
                nomorKamar, tipeKamar, hargaPerMalam,
                tersedia ? "TERSEDIA" : "TERPAKAI");
    }

    @Override
    public String toString() {
        return nomorKamar + " - " + tipeKamar;
    }
}
