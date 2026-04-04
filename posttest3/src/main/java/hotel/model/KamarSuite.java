package hotel.model;

/**
 * KamarSuite adalah subclass (childclass) ketiga dari Kamar.
 *
 * Tipe Inheritance: Single Inheritance (KamarSuite extends Kamar)
 *
 * KamarSuite mewarisi semua field dan method dari Kamar,
 * dan menambahkan atribut/perilaku khusus kamar suite:
 * - jumlahRuangan : jumlah ruangan dalam suite (bedroom, living room, dll)
 * - termasukButler : apakah ada layanan butler pribadi
 * - Override getFasilitas() untuk menampilkan fasilitas khas suite
 * - Mengakses field PROTECTED kapasitasOrang dari superclass langsung
 */
public class KamarSuite extends Kamar {

    // === PRIVATE FIELD tambahan khusus KamarSuite ===
    private int     jumlahRuangan;
    private boolean termasukButler;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarSuite(int nomorKamar, double hargaPerMalam, int jumlahRuangan, boolean termasukButler) {
        // Memanggil constructor superclass Kamar
        // Kamar Suite: kapasitas 4 orang
        super(nomorKamar, "Suite", hargaPerMalam, 4);
        this.jumlahRuangan  = jumlahRuangan;
        this.termasukButler = termasukButler;
        // Akses langsung ke field protected kapasitasOrang dari superclass
        // Suite besar bisa menampung lebih banyak orang sesuai jumlah ruangan
        this.kapasitasOrang = jumlahRuangan * 2;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int     getJumlahRuangan()           { return jumlahRuangan; }
    public boolean isTermasukButler()           { return termasukButler; }
    public void    setTermasukButler(boolean b) { this.termasukButler = b; }

    // ─── OVERRIDE METHOD dari superclass Kamar ────────────────────

    /**
     * Override getFasilitas() — fasilitas khusus kamar suite.
     * Menggunakan super.getFasilitas() untuk mewarisi fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas(); // warisan dari Kamar
        String fasilitas = base + ", Jacuzzi, Dapur Kecil, Ruang Tamu, " + jumlahRuangan + " Ruangan";
        if (termasukButler) {
            fasilitas += ", Layanan Butler 24 Jam";
        }
        return fasilitas;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Ruangan    : " + jumlahRuangan + " ruangan");
        System.out.println("  Butler     : " + (termasukButler ? "Tersedia" : "Tidak Ada"));
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Suite (" + jumlahRuangan + " ruangan)";
    }
}
