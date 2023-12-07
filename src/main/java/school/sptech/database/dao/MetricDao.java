package school.sptech.database.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.database.DatabaseConnection;

public class MetricDao {

    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();





}
