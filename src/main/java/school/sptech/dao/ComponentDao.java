package school.sptech.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.Component;
import school.sptech.database.DatabaseConnection;
import school.sptech.mapper.ComponentMapper;

public class ComponentDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public void toggleComponent(String mac, boolean status, int type){
        connection.update(
            """
                UPDATE `Component` c
                    JOIN `Server` s  ON s.idServer = c.fkServer
                SET
                    c.enable = ?
                WHERE s.macAddress = ? AND c.fkComponentType = ?;
            """,
            status, mac, type
        );
    }

    public List<Component> searchComponentByMac(String mac){
        List<Component> components = connection.query(
            "SELECT * FROM Component c JOIN Server s ON c.fkServer = s.idServer WHERE s.macAddress = ?",
            new ComponentMapper(),
            mac
        );

        return components;
    }
    
}
