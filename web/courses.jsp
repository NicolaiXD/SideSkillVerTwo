
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.SQLException"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset=UTF-8">


        <link rel="stylesheet" href="css/st.css"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </head>
    <body>
        <% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            if (session.getAttribute("Username") == null) {
                response.sendRedirect("login.jsp");
            }
        %>
      
<div class="table-container">
  <br>
  <br>
  <br>
     <table class="table" >
        <thead>
            <tr>
                <th>CourseName</th>
                <th>Teacher</th>
                <th>Image</th>
                <th>link</th>
                
            </tr>
        </thead>
            <%! Connection conn;
                PreparedStatement ps;

                public void jspInit() {
                    try {
                        ServletConfig config = getServletConfig();
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
                        ps = conn.prepareStatement("select * from courses");
                    } catch (SQLException sqle) {
                        System.out.println("SQLException error occured - "
                                + sqle.getMessage());
                    } catch (ClassNotFoundException nfe) {
                        System.out.println("ClassNotFoundException error occured - "
                                + nfe.getMessage());
                    }
                }%>          
             <tbody>
                <%
                ResultSet rs = ps.executeQuery();
         
                while (rs.next()) {%>
             
                <tr>
                <td><a href="player.jsp?id=<%=rs.getInt("id")%>"><%=rs.getString(2)%></a></td>
                <td><%=rs.getString(3)%></td>
                <td><a href="player.jsp?id=<%=rs.getInt("id")%>"><img src="folder/<%=rs.getString(4)%>" style="width:300px;height:250px"></a></td>
                <td><%=rs.getString(5)%></td>
                
                
               
            </tr>

            <% }%>
             </tbody>
</table>
         </div>
        
        
    </body>
</html>