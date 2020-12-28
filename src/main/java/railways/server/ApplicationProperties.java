package railways.server;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class ApplicationProperties {
    private final static Logger logger = Logger.getLogger(ApplicationProperties.class);

    private static int port;
    private static String mailUser;
    private static String mailPassword;
    private static String adminLogin;
    private static String adminPassword;
    private static int poolSize;
    private static long timeToKeepAlive;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));

            port = Integer.valueOf(properties.getProperty("port"));
            mailUser = properties.getProperty("mail.login");
            mailPassword = properties.getProperty("mail.password");
            adminLogin = properties.getProperty("admin.login");
            adminPassword = properties.getProperty("admin.password");
            poolSize = Integer.valueOf(properties.getProperty("poolSize"));
            timeToKeepAlive = Long.valueOf(properties.getProperty("timeToKeepAlive"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getMailUser() {
        return mailUser;
    }

    public static String getMailPassword() {
        return mailPassword;
    }

    public static int getPort() {
        return port;
    }

    public static String getAdminLogin() {
        return adminLogin;
    }

    public static int getPoolSize() {
        return poolSize;
    }

    public static long getTimeToKeepAlive() {
        return timeToKeepAlive;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static boolean isAdminCredentials(String login, String password) {
        return login.equals(adminLogin) && password.equals(adminPassword);
    }
}
