<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    String id = request.getParameter("id");
    Connection conn;

    ResultSet resultSet = null;
    Statement statement = null;
    try {

        String a = config.getInitParameter("jdbcClassName");
        Class.forName(a);
        //System.out.println("jdbcClassName: " + config.getInitParameter("jdbcClassName"));
        String username = config.getInitParameter("dbUserName");
        String password = config.getInitParameter("dbPassword");
        StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                .append("://")
                .append(config.getInitParameter("dbHostName"))
                .append(":")
                .append(config.getInitParameter("dbPort"))
                .append("/")
                .append(config.getInitParameter("databaseName"));
        conn = DriverManager.getConnection(url.toString(), username, password);

        statement = conn.createStatement();
        String sql = "Select * from Courses where id="+ id;
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
%>

<html>
    <head>
         <meta charset=UTF-8">
        <link rel="stylesheet" href="css/login.css"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
    </head>
    <body>
     
  <iframe width="560" height="315" src="<%=resultSet.getString("link")%>"  frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe> 
     <h1><%=resultSet.getString(2)%> </h1>
      <h2><%=resultSet.getString(3)%> </h2>
   
  <%
                                }
                                conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
 </body>
</html>
