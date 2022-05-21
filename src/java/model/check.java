 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class check {

    public Boolean checkinfo(String username, Connection conn) {
       
          try {

            String query = "select role from logindata where username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Boolean b= rs.next();
            return b;
        } catch (SQLException sqle) {
            System.out.println("None");
        }
        return false;

    }
    public String locate(String folder)
    {
        
         try {
  
            
  
             String absolute = System.getProperty("user.dir");
  
             return absolute;
            
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        return null;
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
