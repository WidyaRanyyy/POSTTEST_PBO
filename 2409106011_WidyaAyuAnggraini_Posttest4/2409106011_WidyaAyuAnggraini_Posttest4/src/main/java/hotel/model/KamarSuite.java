package hotel.model;

/**
 * KamarSuite adalah subclass ketiga dari Kamar.
 *
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ POLYMORPHISM - Override                                       ║
 * ║  getFasilitas()           — override + super.getFasilitas()  ║
 * ║  tampilInfo()             — override + super.tampilInfo()    ║
 * ║  tampilInfo(boolean)      — override overload dari Kamar     ║
 * ║  toString()               — override                         ║
 * ╚══════════════════════════════════════════════════════════════╝
 */
public class KamarSuite extends Kamar {

    // === PRIVATE FIELD tambahan ===
    private int     jumlahRuangan;
    private boolean termasukButler;

    // ─── CONSTRUCTOR ──────────────────────────────────────────────

    public KamarSuite(int nomorKamar, double hargaPerMalam, int jumlahRuangan, boolean termasukButler) {
        super(nomorKamar, "Suite", hargaPerMalam, 4);
        this.jumlahRuangan  = jumlahRuangan;
        this.termasukButler = termasukButler;
        this.kapasitasOrang = jumlahRuangan * 2;
    }

    // ─── GETTER & SETTER ─────────────────────────────────────────

    public int     getJumlahRuangan()           { return jumlahRuangan; }
    public boolean isTermasukButler()           { return termasukButler; }
    public void    setTermasukButler(boolean b) { this.termasukButler = b; }

    // ─── POLYMORPHISM: OVERRIDE ──────────────────────────────────

    /**
     * [OVERRIDE getFasilitas()]
     * Menambahkan fasilitas khusus kamar suite ke fasilitas dasar.
     */
    @Override
    public String getFasilitas() {
        String base = super.getFasilitas();
        String fasilitas = base + ", Jacuzzi, Dapur Kecil, Ruang Tamu, " + jumlahRuangan + " Ruangan";
        if (termasukButler) {
            fasilitas += ", Layanan Butler 24 Jam";
        }
        return fasilitas;
    }

    /**
     * [OVERRIDE tampilInfo()]
     * Menampilkan info kamar suite lengkap dengan info ruangan dan butler.
     */
    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("  Ruangan    : " + jumlahRuangan + " ruangan");
        System.out.println("  Butler     : " + (termasukButler ? "Tersedia" : "Tidak Ada"));
    }

    /**
     * [OVERRIDE tampilInfo(boolean)]
     * Override overload dari Kamar.tampilInfo(boolean).
     * Untuk kamar suite, juga menampilkan info ruangan dan butler.
     */
    @Override
    public void tampilInfo(boolean tampilFasilitas) {
        super.tampilInfo(tampilFasilitas);
        System.out.println("  Ruangan    : " + jumlahRuangan + " ruangan");
        System.out.println("  Butler     : " + (termasukButler ? "Tersedia" : "Tidak Ada"));
    }

    @Override
    public String toString() {
        return getNomorKamar() + " - Suite (" + jumlahRuangan + " ruangan)";
    }
}
