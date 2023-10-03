package net.viralpatel.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Controller
public class DbExampleController {

    Logger LOGGER = LogManager.getLogger(HelloController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @GetMapping("/dbquery")
    public void hello(Model model) {
        LOGGER.error("This is a query");
        LOGGER.debug("This is a debug");
        testQuery();
        testQueryJdbcTemplate(1);
    }

    private void testQueryJdbcTemplate(Integer id) {
        String query = "select * from forme where id = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(query, id);
        LOGGER.error("Result with jdbcTemplate: {}/{}/{}", maps.get(0).get("id"), maps.get(0).get("name"), maps.get(0).get("description"));
    }

    private void testQuery() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from forme");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
