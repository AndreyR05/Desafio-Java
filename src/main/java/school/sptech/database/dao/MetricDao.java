package school.sptech.database.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import school.sptech.database.DatabaseConnection;
import school.sptech.model.Metric;
import school.sptech.utils.mapper.MetricMapper;

public class MetricDao {
    private JdbcTemplate connection = new DatabaseConnection().getDatabaseConection();

    public void insertMetric(String mac, int type, double value){
        connection.update("""
            INSERT INTO Metric (fkComponent, value) 
                VALUES
                ((SELECT idComponent FROM Component WHERE fkServer = (SELECT idServer FROM Server WHERE macAddress = ?) AND fkComponentType = ?), ?)""",
            mac, type, value
        );
    }

    public List<Metric> getMetricByServer(String mac){
        List<Metric> metrics = connection.query("""
            SELECT 
                mt.type, 
                m.value 
            FROM 
                Metric m 
            INNER JOIN 
                Component c ON m.fkComponent = c.idComponent 
            INNER JOIN 
                ComponentType mt ON c.fkComponentType = mt.idComponentType 
            WHERE 
                c.fkServer = (SELECT idServer FROM Server WHERE macAddress = ?)""",
            new MetricMapper(),
            mac
        );

        return metrics;
    }
    
}
