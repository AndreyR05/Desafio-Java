package school.sptech.dao.MetricDao;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.database.DatabaseConnection;

public class MetricDao {

    private final JdbcTemplate dataBase = new DatabaseConnection().getDatabaseConection();


    public void insertServer(String name, String macAddress){
        try {
            dataBase.update("INSERT INTO Server VALUES (null, ?, ?)",
                    name,
                    macAddress
            );
        } catch (Exception e) {
            System.out.println("Database error : " + e.fillInStackTrace());
        }
    }
}
