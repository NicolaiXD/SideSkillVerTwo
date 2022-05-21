package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registermodel {

    public int newinfo(String username, String password, String role, Connection conn) {

        try {
            if (conn != null) {
                String query = "Insert into users(username,password,role) values(?, ?,?)";

                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                int i = ps.executeUpdate();
                return i;
            }
        } catch (SQLException sqle) {
            System.out.println("None");
        }
        return 0;

    }

    public Connection getConnection(String a, String d, String u, String p, String h, String po, String dbname) {
        try {
            Class.forName(a);
            StringBuffer url = new StringBuffer(d)
                    .append("://")
                    .append(h)
                    .append(":")
                    .append(po)
                    .append("/")
                    .append(dbname);
            return DriverManager.getConnection(url.toString(), u, p);

        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
        }
        return null;
    }
}
