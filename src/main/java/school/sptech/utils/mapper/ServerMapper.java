package school.sptech.utils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import school.sptech.model.Server;

public class ServerMapper implements RowMapper<Server>{
    @Override
    public Server mapRow(ResultSet rs, int rowNum) throws SQLException {
        Server server = new Server();
        server.setIdServer(rs.getString("idServer"));
        server.setName(rs.getString("name"));
        server.setMacAddress(rs.getString("macAddress"));
        return server;
    }

    
}
