package net.viralpatel.spring.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TextDbConn {

    public static void main(String[] args) {
        testQuery();
    }

    private static void testQuery() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/formagin1009", "aaa", "aaa");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from forme");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
