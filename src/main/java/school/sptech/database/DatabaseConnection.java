package school.sptech.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

// This class is to connection the aplication with MySQL Workbanch;

public class DatabaseConnection {

    private final JdbcTemplate databaseConnection;

    public DatabaseConnection(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("DRIVER_DB");
        dataSource.setUrl("URL_DB");
        dataSource.setUsername("USUARIO_DB");
        dataSource.setPassword("SENHA_DB");

        databaseConnection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getDatabaseConection() {
        return databaseConnection;
    }

}
