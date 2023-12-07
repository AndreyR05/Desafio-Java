package school.sptech.dao.captureDao;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.database.DatabaseConnection;
import school.sptech.model.Capture;

import java.sql.Connection;
import java.time.LocalDateTime;

public class CaptureDao {

    private final JdbcTemplate dataBase = new DatabaseConnection().getDatabaseConection();

    public void insertCapture(Capture capture, LocalDateTime moment){
        try {
            dataBase.update("INSERT INTO Capture VALUES (null, ?, ?, null)",
                    capture.getValue(),
                    moment
            );
        } catch (Exception e) {
            System.out.println("Database error : " + e.fillInStackTrace());
        }

    }


}
