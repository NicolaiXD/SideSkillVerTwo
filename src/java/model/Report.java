package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    String username = "";
    String role = "";

    public void Report(String username, String role, Connection conn) throws DocumentException {

        this.username = username;
        this.role = role;

        System.out.println("Report.java");
        System.out.println("Username: " + this.username);
        System.out.println("Role: " + this.role);

        try {
            String home = System.getProperty("home.dir");
            Document docs = new Document();
            PdfWriter.getInstance(docs, new FileOutputStream(home + "\\Downloads\\TestReport.pdf"));

            docs.open();
            PdfPTable table = new PdfPTable(2);

            PdfPCell h1 = new PdfPCell(new Phrase("Username"));
            PdfPCell h2 = new PdfPCell(new Phrase("Role"));

            table.addCell(h1);
            table.addCell(h2);

            if (role.equals("Admin")) {

                String query = "SELECT * FROM Users ";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet result = ps.executeQuery();

                while (result.next()) {

                    PdfPCell c1 = new PdfPCell(new Phrase(result.getString("username")));
                    PdfPCell c2 = new PdfPCell(new Phrase(result.getString("role")));

                    table.addCell(c1);
                    table.addCell(c2);

                }
            } else if (role.equals("Student")) {
                String query = "SELECT * FROM Students where username = ? ";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    PdfPCell c1 = new PdfPCell(new Phrase(username));
                    PdfPCell c2 = new PdfPCell(new Phrase(role));
                    PdfPCell c3 = new PdfPCell(new Phrase(result.getString("coursename1")));
                    PdfPCell c4 = new PdfPCell(new Phrase(result.getString("coursename2")));
                    PdfPCell c5 = new PdfPCell(new Phrase(result.getString("coursename3")));

                    table.addCell(c1);
                    table.addCell(c2);
                    table.addCell(c3);
                    table.addCell(c4);
                    table.addCell(c5);
                }
               
                
            
            }
             else if (role.equals("Teacher")){
                  String query = "SELECT * FROM Students where username = ? ";
             }
                docs.add(table);
                docs.close();
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
