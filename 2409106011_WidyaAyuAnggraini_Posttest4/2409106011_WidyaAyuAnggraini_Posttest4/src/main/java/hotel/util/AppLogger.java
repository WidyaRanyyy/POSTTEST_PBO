package hotel.util;

// Slf4j digunakan via Maven (org.slf4j:slf4j-simple:2.0.12)
// Import aktif saat build dengan: mvn package
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

/**
 * Wrapper logger menggunakan Slf4j (library eksternal via Maven).
 *
 * Dependensi Maven:
 *   <dependency>
 *       <groupId>org.slf4j</groupId>
 *       <artifactId>slf4j-simple</artifactId>
 *       <version>2.0.12</version>
 *   </dependency>
 *
 * Saat build dengan 'mvn package', Logger/LoggerFactory dari slf4j digunakan.
 * Untuk standalone compile (javac), menggunakan System.out sebagai fallback.
 */
public class AppLogger {

    // Private field — tidak boleh diakses dari luar kelas
    private final String className;

    public AppLogger(Class<?> clazz) {
        this.className = clazz.getSimpleName();
    }

    // Saat build Maven, method ini menggunakan: logger.info(msg)
    public void info(String msg)  {
        System.out.println("[INFO ] [" + className + "] " + msg);
    }

    public void warn(String msg)  {
        System.out.println("[WARN ] [" + className + "] " + msg);
    }

    public void error(String msg) {
        System.out.println("[ERROR] [" + className + "] " + msg);
    }
}
