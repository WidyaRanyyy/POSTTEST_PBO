package hotel.model;

/**
 * Kelas Reservasi merepresentasikan data pemesanan kamar.
 * Encapsulation: semua field private, logika bisnis terisolasi dalam kelas.
 */
public class Reservasi {

    // === PRIVATE FIELDS ===
    private int    idReservasi;
    private Tamu   tamu;
    private Kamar  kamar;
    private int    jumlahMalam;
    private String statusReservasi; // AKTIF, SELESAI, DIBATALKAN

    // Konstanta status (public static final — dapat diakses dari mana saja)
    public static final String STATUS_AKTIF      = "AKTIF";
    public static final String STATUS_SELESAI    = "SELESAI";
    public static final String STATUS_DIBATALKAN = "DIBATALKAN";

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Reservasi() {
        this.idReservasi    = 0;
        this.tamu           = null;
        this.kamar          = null;
        this.jumlahMalam    = 0;
        this.statusReservasi = STATUS_AKTIF;
    }

    public Reservasi(int idReservasi, Tamu tamu, Kamar kamar, int jumlahMalam) {
        this.idReservasi    = idReservasi;
        this.tamu           = tamu;
        this.kamar          = kamar;
        this.jumlahMalam    = validasiMalam(jumlahMalam);
        this.statusReservasi = STATUS_AKTIF;
    }

    // ─── PRIVATE VALIDATION ──────────────────────────────────────

    private int validasiMalam(int malam) {
        if (malam <= 0)
            throw new IllegalArgumentException("Jumlah malam harus minimal 1.");
        return malam;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int    getIdReservasi()            { return idReservasi; }
    public Tamu   getTamu()                   { return tamu; }
    public Kamar  getKamar()                  { return kamar; }
    public int    getJumlahMalam()            { return jumlahMalam; }
    public String getStatusReservasi()        { return statusReservasi; }

    public void setStatusReservasi(String s)  { this.statusReservasi = s; }
    public void setJumlahMalam(int malam)     { this.jumlahMalam = validasiMalam(malam); }

    // ─── BUSINESS LOGIC ──────────────────────────────────────────

    /** Hitung total biaya, pertimbangkan diskon jika tamu adalah VIP */
    public double hitungTotalBiaya() {
        double harga = kamar.getHargaPerMalam() * jumlahMalam;
        if (tamu instanceof TamuVIP vip) {
            harga = vip.hitungHargaSetelahDiskon(harga);
        }
        return harga;
    }

    public boolean isAktif() {
        return STATUS_AKTIF.equals(statusReservasi);
    }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public void tampilInfo() {
        System.out.println("  ID Reservasi  : " + idReservasi);
        System.out.println("  Tamu          : " + tamu);
        System.out.println("  Kamar         : " + kamar);
        System.out.printf ("  Harga/Malam   : Rp %.0f%n", kamar.getHargaPerMalam());
        System.out.println("  Jumlah Malam  : " + jumlahMalam + " malam");
        if (tamu instanceof TamuVIP vip) {
            System.out.printf("  Diskon VIP    : %.0f%%%n", vip.getDiskonPersen());
        }
        System.out.printf ("  Total Biaya   : Rp %.0f%n", hitungTotalBiaya());
        System.out.println("  Status        : " + statusReservasi);
    }
}
