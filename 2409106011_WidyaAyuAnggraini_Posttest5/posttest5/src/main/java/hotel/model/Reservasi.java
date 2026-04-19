package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  CLASS: Reservasi
 *  Posttest 5 — Abstract Class & Interface
 *
 *  Perubahan dari Posttest 4:
 *   - Implements interface Laporan (minimal 2 method)
 *       • getRingkasan()        → ringkasan satu baris reservasi
 *       • cetakDetail()         → detail lengkap reservasi
 *       • getKategoriLaporan()  → "RESERVASI"
 *
 *  Polymorphism (tetap dari Posttest 4):
 *   Overload: hitungTotalBiaya() vs hitungTotalBiaya(double)
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class Reservasi implements Laporan {

    // === PRIVATE FIELDS ===
    private int    idReservasi;
    private Tamu   tamu;
    private Kamar  kamar;
    private int    jumlahMalam;
    private String statusReservasi;

    public static final String STATUS_AKTIF      = "AKTIF";
    public static final String STATUS_SELESAI    = "SELESAI";
    public static final String STATUS_DIBATALKAN = "DIBATALKAN";

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public Reservasi() {
        this.idReservasi     = 0;
        this.tamu            = null;
        this.kamar           = null;
        this.jumlahMalam     = 0;
        this.statusReservasi = STATUS_AKTIF;
    }

    public Reservasi(int idReservasi, Tamu tamu, Kamar kamar, int jumlahMalam) {
        this.idReservasi     = idReservasi;
        this.tamu            = tamu;
        this.kamar           = kamar;
        this.jumlahMalam     = validasiMalam(jumlahMalam);
        this.statusReservasi = STATUS_AKTIF;
    }

    // ─── PRIVATE VALIDATION ──────────────────────────────────────

    private int validasiMalam(int malam) {
        if (malam <= 0)
            throw new IllegalArgumentException("Jumlah malam harus minimal 1.");
        return malam;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int    getIdReservasi()           { return idReservasi; }
    public Tamu   getTamu()                  { return tamu; }
    public Kamar  getKamar()                 { return kamar; }
    public int    getJumlahMalam()           { return jumlahMalam; }
    public String getStatusReservasi()       { return statusReservasi; }

    public void setStatusReservasi(String s) { this.statusReservasi = s; }
    public void setJumlahMalam(int malam)    { this.jumlahMalam = validasiMalam(malam); }

    // ─── POLYMORPHISM: METHOD OVERLOAD ───────────────────────────

    /**
     * [OVERLOAD - hitungTotalBiaya() versi 1]
     * Total biaya kamar dengan diskon VIP jika berlaku.
     */
    public double hitungTotalBiaya() {
        double harga = kamar.getHargaPerMalam() * jumlahMalam;
        if (tamu instanceof TamuVIP vip) {
            harga = vip.hitungHargaSetelahDiskon(harga);
        }
        return harga;
    }

    /**
     * [OVERLOAD - hitungTotalBiaya(double) versi 2]
     * Total biaya kamar + biaya tambahan layanan.
     */
    public double hitungTotalBiaya(double biayaTambahan) {
        if (biayaTambahan < 0)
            throw new IllegalArgumentException("Biaya tambahan tidak boleh negatif.");
        return hitungTotalBiaya() + biayaTambahan;
    }

    public boolean isAktif() {
        return STATUS_AKTIF.equals(statusReservasi);
    }

    // ─── PUBLIC METHOD ────────────────────────────────────────────

    public void tampilInfo() {
        System.out.println("  ID Reservasi  : " + idReservasi);
        System.out.println("  Tamu          : " + tamu);
        System.out.println("  Status Tamu   : " + tamu.getStatusTamu());
        System.out.println("  Kamar         : " + kamar);
        System.out.printf ("  Harga/Malam   : Rp %.0f%n", kamar.getHargaPerMalam());
        System.out.println("  Jumlah Malam  : " + jumlahMalam + " malam");
        if (tamu instanceof TamuVIP vip) {
            System.out.printf("  Diskon VIP    : %.0f%%%n", vip.getDiskonPersen());
        }
        System.out.printf ("  Total Biaya   : Rp %.0f%n", hitungTotalBiaya());
        System.out.println("  Status        : " + statusReservasi);
    }

    public void tampilInfo(double biayaTambahan) {
        tampilInfo();
        if (biayaTambahan > 0) {
            System.out.printf ("  Biaya Tambahan: Rp %.0f%n", biayaTambahan);
            System.out.printf ("  Total Tagihan : Rp %.0f%n", hitungTotalBiaya(biayaTambahan));
        }
    }

    // ─── IMPLEMENTASI INTERFACE Laporan ──────────────────────────

    /**
     * [Laporan.getRingkasan()]
     * Ringkasan reservasi dalam satu baris.
     */
    @Override
    public String getRingkasan() {
        return String.format("Reservasi #%d | Tamu: %-20s | Kamar: %-6s | %d malam | Rp %-12.0f | %s",
                idReservasi, tamu.getNama(), kamar.toString(),
                jumlahMalam, hitungTotalBiaya(), statusReservasi);
    }

    /**
     * [Laporan.cetakDetail()]
     * Mencetak detail lengkap reservasi.
     */
    @Override
    public void cetakDetail() {
        System.out.println("  ══════════════════════════════════════");
        System.out.println("  [LAPORAN DETAIL RESERVASI]");
        tampilInfo();
        System.out.println("  ══════════════════════════════════════");
    }

    /**
     * [Laporan.getKategoriLaporan()]
     */
    @Override
    public String getKategoriLaporan() {
        return "RESERVASI";
    }
}
