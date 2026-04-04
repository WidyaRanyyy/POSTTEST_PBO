package hotel.service;

import hotel.model.Tamu;
import hotel.model.TamuVIP;
import hotel.util.AppLogger;
import hotel.util.InputHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer untuk manajemen data Tamu.
 * Berisi logika CRUD dan menjadi perantara antara UI dan model.
 *
 * Access Modifier:
 *  - private  : field daftarTamu, logger, idCounter
 *  - public   : semua method yang dipanggil dari HotelManagement
 */
public class TamuService {

    // === PRIVATE FIELDS ===
    private final List<Tamu> daftarTamu = new ArrayList<>();
    private final AppLogger  logger     = new AppLogger(TamuService.class);
    private int idCounter = 1;

    // ─── CONSTRUCTOR / INISIALISASI ──────────────────────────────

    public TamuService() {
        inisialisasiDataAwal();
    }

    private void inisialisasiDataAwal() {
        daftarTamu.add(new Tamu(idCounter++, "Dewi",  2409106007L, 81234567890L));
        daftarTamu.add(new Tamu(idCounter++, "Sari",  3202924890L, 82222222222L));
        daftarTamu.add(new TamuVIP(idCounter++, "Raka", 1234567890L, 81298765432L, "Gold"));
    }

    // ─── PUBLIC CRUD METHODS ─────────────────────────────────────

    public void tambahTamu() {
        System.out.println("\n--- TAMBAH TAMU BARU ---");
        System.out.println("  [1] Tamu Biasa");
        System.out.println("  [2] Tamu VIP");
        int tipe = InputHelper.inputInt("  Pilih tipe tamu: ");

        try {
            String nama = InputHelper.inputString("  Nama       : ");
            long   ktp  = InputHelper.inputLong("  No. KTP    : ");
            long   telp = InputHelper.inputLong("  No. Telp   : ");

            Tamu tamu;
            if (tipe == 2) {
                System.out.println("  Level VIP: [1] Silver  [2] Gold  [3] Platinum");
                int lvl = InputHelper.inputInt("  Pilih level: ");
                String level = switch (lvl) {
                    case 1 -> "Silver";
                    case 3 -> "Platinum";
                    default -> "Gold";
                };
                tamu = new TamuVIP(idCounter++, nama, ktp, telp, level);
            } else {
                tamu = new Tamu(idCounter++, nama, ktp, telp);
            }

            daftarTamu.add(tamu);
            logger.info("Tamu baru ditambahkan: " + tamu);
            System.out.println("  [✓] Tamu '" + tamu.getNama() + "' berhasil ditambahkan (ID: " + tamu.getId() + ").");

        } catch (IllegalArgumentException e) {
            System.out.println("  [!] Error: " + e.getMessage());
            logger.warn("Gagal tambah tamu: " + e.getMessage());
        }
    }

    public void lihatSemuaTamu() {
        System.out.println("\n--- DAFTAR TAMU ---");
        if (daftarTamu.isEmpty()) {
            System.out.println("  Belum ada data tamu.");
            return;
        }
        for (Tamu t : daftarTamu) {
            System.out.println("  ────────────────────────");
            t.tampilInfo();
        }
        System.out.println("  ────────────────────────");
    }

    public void updateTamu() {
        System.out.println("\n--- UPDATE DATA TAMU ---");
        lihatSemuaTamu();
        int id     = InputHelper.inputInt("  Masukkan ID tamu yang ingin diupdate: ");
        Tamu target = cariTamuById(id);

        if (target == null) {
            System.out.println("  [!] Tamu dengan ID " + id + " tidak ditemukan.");
            return;
        }

        try {
            String namaBaru = InputHelper.inputOptional("  Nama baru [" + target.getNama() + "]: ");
            if (namaBaru != null) target.setNama(namaBaru);

            String ktpStr = InputHelper.inputOptional("  No. KTP baru [" + target.getNoKtp() + "]: ");
            if (ktpStr != null) {
                try { target.setNoKtp(Long.parseLong(ktpStr)); }
                catch (NumberFormatException e) { System.out.println("  [!] KTP tidak valid, nilai tidak diubah."); }
            }

            String telpStr = InputHelper.inputOptional("  No. Telp baru [" + target.getNoTelp() + "]: ");
            if (telpStr != null) {
                try { target.setNoTelp(Long.parseLong(telpStr)); }
                catch (NumberFormatException e) { System.out.println("  [!] No.Telp tidak valid, nilai tidak diubah."); }
            }

            if (target instanceof TamuVIP vip) {
                String lvlBaru = InputHelper.inputOptional("  Level VIP baru [" + vip.getTingkatVIP() + "] (Silver/Gold/Platinum): ");
                if (lvlBaru != null) vip.setTingkatVIP(lvlBaru);
            }

            logger.info("Data tamu diperbarui: " + target);
            System.out.println("  [✓] Data tamu berhasil diperbarui.");

        } catch (IllegalArgumentException e) {
            System.out.println("  [!] Error: " + e.getMessage());
        }
    }

    public void hapusTamu() {
        System.out.println("\n--- HAPUS TAMU ---");
        lihatSemuaTamu();
        int id     = InputHelper.inputInt("  Masukkan ID tamu yang ingin dihapus: ");
        Tamu target = cariTamuById(id);

        if (target == null) {
            System.out.println("  [!] Tamu tidak ditemukan.");
            return;
        }

        daftarTamu.remove(target);
        logger.info("Tamu dihapus: " + target);
        System.out.println("  [✓] Tamu '" + target.getNama() + "' berhasil dihapus.");
    }

    // ─── PUBLIC HELPER ────────────────────────────────────────────

    public Tamu cariTamuById(int id) {
        for (Tamu t : daftarTamu)
            if (t.getId() == id) return t;
        return null;
    }

    public List<Tamu> getDaftarTamu() {
        return daftarTamu;
    }
}
