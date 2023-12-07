package school.sptech.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.database.DatabaseConnection;

public class ComponentDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public void toggleComponent(String mac, boolean status, int type){
        connection.update(
            "UPDATE Component c JOIN Server s ON c.fkServer = s.idServer SET c.enable = ? WHERE s.mac = ? AND c.fkComponentType = ?",
            status, mac, type
        );
    }
    
}
