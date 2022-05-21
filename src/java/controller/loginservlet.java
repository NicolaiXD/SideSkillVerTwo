package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import model.Security;
import model.ConnectionManager;
import nl.captcha.Captcha;
import javax.servlet.ServletContext;

public class loginservlet extends HttpServlet {

    Connection conn;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String classname = config.getInitParameter("jdbcClassName");
        String username = config.getInitParameter("dbUserName");
        String password = config.getInitParameter("dbPassword");
        String url = config.getInitParameter("jdbcDriverURL");
        String hostname = config.getInitParameter("dbHostName");
        String dbport = config.getInitParameter("dbPort");
        String dbname = config.getInitParameter("databaseName");
        ConnectionManager lgn = new ConnectionManager();
        conn = lgn.establishConn(classname, username, password, url, hostname, dbport, dbname);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String role = request.getParameter("role");
        ConnectionManager lgn = new ConnectionManager();
        //String lgnn =  .checklgn(username, role, conn);
        //String depass = Security.decrypt(lgnn);

        ServletContext ctr = getServletContext();
        ctr.setAttribute("conn", conn);

        request.setCharacterEncoding("UTF-8");
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

        Security sec = new Security();
        //String decryptpassword = sec.decrypt(lgn.checklgn(username, conn));

        //boolean con = password.equals(decryptpassword);
        String rcap = request.getParameter("rcaptcha");
        if (captcha.isCorrect(rcap)) {
            String decryptpassword = sec.decrypt(lgn.checklgn(username, conn));
            boolean con = password.equals(decryptpassword);
            if (con) {

                session.setAttribute("Username", username);

                String role = lgn.getRole(username, conn);
                session.setAttribute("role", role);

                if (role.equals("Admin")) {
                    response.sendRedirect("admin/adminhome.jsp");
                }

                if (role.equals("Student")) {
                    response.sendRedirect("student/studenthome.jsp");
                }

                if (role.equals("Teacher")) {
                    response.sendRedirect("teacher/teacherhome.jsp");
                }

//                RequestDispatcher rd = request.getRequestDispatcher("landingpage.jsp");
//                rd.forward(request, response);
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
