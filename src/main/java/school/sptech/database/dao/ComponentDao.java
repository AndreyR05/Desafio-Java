package school.sptech.database.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.database.DatabaseConnection;
import school.sptech.model.ComponentWithType;
import school.sptech.utils.mapper.ComponentWithTypeMapper;

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

    public List<ComponentWithType> searchComponentByMac(String mac){
        List<ComponentWithType> components = connection.query(
            """
                SELECT * FROM Component c 
                    JOIN Server s ON c.fkServer = s.idServer
                    JOIN ComponentType ct ON c.fkComponentType = ct.idComponentType
                WHERE s.macAddress = ?
            """,
            new ComponentWithTypeMapper(),
            mac
        );

        return components;
    }
    
}
