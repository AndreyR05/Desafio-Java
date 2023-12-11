package school.sptech.utils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import school.sptech.model.Metric;
import school.sptech.utils.enums.ComponentEnum;

public class MetricMapper implements RowMapper<Metric> {

    @Override
    public Metric mapRow(ResultSet rs, int rowNum) throws SQLException {
        Metric metric = new Metric();
        metric.setType(ComponentEnum.valueOf(rs.getString("type")));
        metric.setValue(rs.getDouble("value"));
        return metric;
    }
}
