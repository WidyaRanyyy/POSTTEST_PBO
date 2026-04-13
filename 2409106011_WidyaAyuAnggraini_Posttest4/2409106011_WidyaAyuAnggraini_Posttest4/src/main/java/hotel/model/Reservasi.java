package hotel.model;

/**
 * Kelas Reservasi merepresentasikan data pemesanan kamar.
 * Encapsulation: semua field private, logika bisnis terisolasi dalam kelas.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM                                                  ║
 * ║  Overload: hitungTotalBiaya()                                 ║
 * ║            hitungTotalBiaya(double biayaTambahan)             ║
 * ╚══════════════════════════════════════════════════════════════╝
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

    // ─── POLYMORPHISM: METHOD OVERLOAD ───────────────────────────

    /**
     * [OVERLOAD - hitungTotalBiaya() versi 1]
     * Menghitung total biaya kamar saja (harga per malam × jumlah malam),
     * dengan mempertimbangkan diskon jika tamu adalah VIP.
     *
     * @return total biaya tanpa biaya tambahan
     */
    public double hitungTotalBiaya() {
        double harga = kamar.getHargaPerMalam() * jumlahMalam;
        if (tamu instanceof TamuVIP vip) {
            harga = vip.hitungHargaSetelahDiskon(harga);
        }
        return harga;
    }

    /**
     * [OVERLOAD - hitungTotalBiaya(double biayaTambahan) versi 2]
     * Overload dari hitungTotalBiaya() dengan tambahan parameter biaya layanan.
     *
     * Logis karena: tamu hotel sering memesan layanan tambahan seperti
     * room service, laundry, atau spa yang perlu ditambahkan ke tagihan akhir,
     * sehingga dibutuhkan versi perhitungan dengan biaya tambahan.
     *
     * @param biayaTambahan total biaya layanan tambahan (room service, laundry, dll)
     * @return total biaya kamar + biaya layanan tambahan
     */
    public double hitungTotalBiaya(double biayaTambahan) {
        if (biayaTambahan < 0)
            throw new IllegalArgumentException("Biaya tambahan tidak boleh negatif.");
        return hitungTotalBiaya() + biayaTambahan; // memanfaatkan versi 1
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

    /**
     * Menampilkan info reservasi lengkap termasuk biaya tambahan layanan.
     *
     * @param biayaTambahan biaya layanan tambahan yang dibebankan ke tamu
     */
    public void tampilInfo(double biayaTambahan) {
        tampilInfo(); // tampilkan info dasar dulu
        if (biayaTambahan > 0) {
            System.out.printf ("  Biaya Tambahan: Rp %.0f%n", biayaTambahan);
            System.out.printf ("  Total Tagihan : Rp %.0f%n", hitungTotalBiaya(biayaTambahan));
        }
    }
}
