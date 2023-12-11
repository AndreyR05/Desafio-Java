package school.sptech.database.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.database.DatabaseConnection;

public class CaptureDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public void insertCapture(String mac, int type, double value){
        connection.update("""
            INSERT INTO Capture (fkComponent, value) 
                VALUES
                ((SELECT idComponent FROM Component WHERE fkServer = (SELECT idServer FROM Server WHERE macAddress = ?) AND fkComponentType = ?), ?)""",
            mac, type, value
        );
    }
}
