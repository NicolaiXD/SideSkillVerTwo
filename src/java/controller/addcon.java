package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.check;

import model.insertmodel;

@MultipartConfig

public class addcon extends HttpServlet {

    private static final long serialVersionUID = 1L;
 public addcon() {
        super();
    }
    Connection conn;

   

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String a = config.getInitParameter("jdbcClassName");

        String u = config.getInitParameter("dbUserName");
        String p = config.getInitParameter("dbPassword");
        String d = config.getInitParameter("jdbcDriverURL");
        String h = config.getInitParameter("dbHostName");
        String po = config.getInitParameter("dbPort");
        String dbname = config.getInitParameter("databaseName");
        insertmodel ins = new insertmodel();
        conn = ins.getConnection(a, d, u, p, h, po, dbname);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String coursename = request.getParameter("coursename");
        String link = request.getParameter("link");
        String folder ="folder"; 
        Part part = request.getPart("image");
        String imageFileName = part.getSubmittedFileName();
        check ck = new check();
        String paaath= ck.locate(folder);
        
//        ServletContext context = request.getServletContext();
        String Path ="/SideSkills/web/folder"  +File.separator+ imageFileName;
        
       
       
        String teacher = request.getParameter("teacher");

        

        if (conn != null) {

            FileOutputStream fos = new FileOutputStream(Path);
            InputStream is = part.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();

     
            insertmodel ins = new insertmodel();
            int i = ins.newcourse(coursename, teacher, imageFileName, link, conn);

            if (i == 1) {

                response.sendRedirect("delete.jsp");
            } else {

                response.sendRedirect("error.jsp");

            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
