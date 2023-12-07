package school.sptech.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import school.sptech.ComponentWithType;
import school.sptech.enums.ComponentEnum;

public class ComponentWithTypeMapper implements RowMapper<ComponentWithType>{
    @Override
    public ComponentWithType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ComponentWithType component = new ComponentWithType();
        component.setIdComponent(rs.getInt("idComponent"));
        component.setEnable(rs.getBoolean("enable"));
        component.setFkComponentType(rs.getInt("fkComponentType"));
        component.setFkServer(rs.getInt("fkServer"));
        component.setComponentType(
            ComponentEnum.valueOf(rs.getString("componentType"))
        );

        return component;
    }
}