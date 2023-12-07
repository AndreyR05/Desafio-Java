package school.sptech.serverDao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.Server;
import school.sptech.database.DatabaseConnection;
import school.sptech.mapper.ServerMapper;

public class ServerDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public List<Server> searchServerByMac(String mac){
        List<Server> servers = connection.query(
            "SELECT * FROM Server s WHERE s.macAddress = ?", 
            new ServerMapper(),
            mac
        );
        return servers;
    }

    public void insertServer(String mac, String name){
        connection.update(
            "INSERT INTO Server (macAddress, name) VALUES (?, ?)",
            mac, name
        );
    }
}
