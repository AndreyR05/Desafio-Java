package school.sptech.database.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.database.DatabaseConnection;

public class MetricDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public void insertMetric(String mac, int type, double value){
        connection.update("""
            INSERT INTO Metric (fkComponent, value) 
                VALUES
                ((SELECT idComponent FROM Component WHERE fkServer = (SELECT idServer FROM Server WHERE macAddress = ?) AND fkComponentType = ?), ?)""",
            mac, type, value
        );
    }
    
}
