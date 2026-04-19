package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: TamuBiasa (concrete class dari abstract Tamu)
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Karena Tamu sekarang abstract, dibutuhkan concrete class untuk
 *  tamu reguler. TamuBiasa mengimplementasikan abstract method:
 *   - tampilInfo()    → menampilkan info tamu biasa
 *   - getStatusTamu() → mengembalikan "Reguler"
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class TamuBiasa extends Tamu {

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public TamuBiasa() {
        super();
    }

    public TamuBiasa(int id, String nama, long noKtp, long noTelp) {
        super(id, nama, noKtp, noTelp);
    }

    // ─── IMPLEMENTASI ABSTRACT METHODS ───────────────────────────

    /**
     * [OVERRIDE/Implementasi tampilInfo() dari abstract Tamu]
     * Menampilkan informasi tamu biasa.
     */
    @Override
    public void tampilInfo() {
        System.out.println("  ID       : " + getId());
        System.out.println("  Nama     : " + getNama());
        System.out.println("  No. KTP  : " + getNoKtp());
        System.out.println("  No. Telp : " + getNoTelp());
        System.out.println("  Poin     : " + getPoinLoyalitas());
        System.out.println("  Status   : " + getStatusTamu());
    }

    /**
     * [Implementasi abstract getStatusTamu() dari Tamu]
     * Tamu biasa memiliki status "Reguler".
     */
    @Override
    public String getStatusTamu() {
        return "Reguler";
    }

    @Override
    public String toString() {
        return getNama() + " (ID: " + getId() + ")";
    }
}
