package school.sptech.utils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import school.sptech.model.Component;

public class ComponentMapper implements RowMapper<Component>{
    @Override
    public Component mapRow(ResultSet rs, int rowNum) throws SQLException {
        Component component = new Component();
        component.setIdComponent(rs.getInt("idComponent"));
        component.setEnable(rs.getBoolean("enable"));
        component.setFkComponentType(rs.getInt("fkComponentType"));
        component.setFkServer(rs.getInt("fkServer"));
        return component;
    }

}