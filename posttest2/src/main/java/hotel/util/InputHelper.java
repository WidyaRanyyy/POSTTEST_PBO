package hotel.util;

import java.util.Scanner;

/**
 * Kelas utilitas untuk input dari pengguna.
 * Semua method static agar bisa dipanggil tanpa membuat objek.
 *
 * Access Modifier:
 *  - public  : kelas dan method yang digunakan lintas package
 *  - private : helper internal kelas ini saja
 */
public class InputHelper {

    // === PRIVATE STATIC FIELD ===
    private static final Scanner sc = new Scanner(System.in);

    // Private constructor → kelas ini tidak dimaksudkan untuk di-instansiasi
    private InputHelper() {}

    // ─── PUBLIC STATIC METHODS ───────────────────────────────────

    public static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harus berupa angka. Coba lagi.");
            }
        }
    }

    public static long inputLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harus berupa angka. Coba lagi.");
            }
        }
    }

    public static String inputString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    /** Input opsional — kembalikan null jika dikosongkan */
    public static String inputOptional(String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();
        return s.isBlank() ? null : s;
    }

    public static Integer inputIntOptional(String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        if (s.isBlank()) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("  [!] Input tidak valid, nilai tidak diubah.");
            return null;
        }
    }

    public static void close() {
        sc.close();
    }
}
