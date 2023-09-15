package submission.project.core.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ConnectionUtil {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        Properties properties = new Properties();
        try (InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            Objects.requireNonNull(inputStream, "Input Stream untuk application.properties kosong");
            properties.load(inputStream);
        } catch (IOException exception){
            throw new RuntimeException("Failed to load Database", exception);
        }
        config.setDriverClassName(properties.getProperty("database.driver"));
        config.setJdbcUrl(properties.getProperty("database.url"));
        config.setUsername(properties.getProperty("database.username"));
        config.setPassword(properties.getProperty("database.password"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(15 * 60_000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
