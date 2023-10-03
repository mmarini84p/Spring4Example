package net.viralpatel.spring.service;

import net.viralpatel.spring.controller.HelloController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    Logger LOGGER = LogManager.getLogger(TestService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @PostConstruct
    void init(){
        testQueryJdbcTemplate(1);
        testQuery();
    }


    private void testQueryJdbcTemplate(Integer id) {
        String query = "select * from forme where id = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query, id);
        LOGGER.info("Result with jdbcTemplate: {}/{}/{}", maps.get(0).get("id"), maps.get(0).get("name"), maps.get(0).get("description"));
    }

    private void testQuery(Integer id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = 0;
            PreparedStatement ps = dataSource.getConnection().prepareStatement("select * from forme where id = ?");
            ps.setInt(++i, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LOGGER.info(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("testQUeryEnded");
    }
}
