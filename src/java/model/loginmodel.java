package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginmodel {

    public String checklgn(String username,String role, Connection conn) {
        String passw="";
        try {
            String lgn = "Select password from logindata where username=? and role=?";
            PreparedStatement ps = conn.prepareStatement(lgn);
            ps.setString(1, username);
            ps.setString(2, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

               passw=rs.getString("password");
                   
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passw;

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
