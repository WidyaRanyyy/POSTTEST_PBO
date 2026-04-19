package hotel.model;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 *  INTERFACE: Laporan
 *  Diimplementasikan oleh kelas yang dapat menghasilkan laporan.
 *
 *  Penerapan Interface:
 *   - Kamar (abstract class) → implements Laporan
 *   - Tamu  (abstract class) → implements Laporan
 *   - Reservasi              → implements Laporan
 * ╚══════════════════════════════════════════════════════════════╝
 */
public interface Laporan {

    /**
     * Menampilkan ringkasan singkat (satu baris) dari objek.
     * Cocok untuk tampilan daftar/tabel.
     */
    String getRingkasan();

    /**
     * Menampilkan detail lengkap dari objek ke konsol.
     * Cocok untuk tampilan detail individu.
     */
    void cetakDetail();

    /**
     * Mengembalikan kategori/jenis laporan objek.
     * Contoh: "KAMAR", "TAMU", "RESERVASI"
     */
    default String getKategoriLaporan() {
        return "UMUM";
    }
}
