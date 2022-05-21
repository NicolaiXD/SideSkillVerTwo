package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertmodel {

    public int newstu(String username, Connection conn) {

        try {
            if (conn != null) {
                String query = "Insert into students(username) values(?)";

                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1, username);
                
                int i = ps.executeUpdate();
                return i;
            }
        } catch (SQLException sqle) {
            System.out.println("None");
        }
        return 0;

    }
    
    public int newcourse(String coursename, String teacher, String image,String link, Connection conn) {

        try {
            if (conn != null) {
                String query = "Insert into courses(coursename,teacher,image, link ) values(?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1, coursename);
                ps.setString(2, teacher);
                ps.setString(3, image);
                ps.setString(4, link);
                
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
