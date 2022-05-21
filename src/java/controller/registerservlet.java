package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Security;
import model.check;
import model.registermodel;
import model.insertmodel;
import nl.captcha.Captcha;

public class registerservlet extends HttpServlet {

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
        registermodel re = new registermodel();
        conn = re.getConnection(a, d, u, p, h, po, dbname);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String cpass = request.getParameter("cpass");
        String rol = request.getParameter("role");
        String enpass = Security.encrypt(pass);
        request.setCharacterEncoding("UTF-8");
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String rcap = request.getParameter("rcaptcha");
        check ck = new check();
        Boolean a = pass.equals(cpass);
        Boolean b = ck.checkinfo(username, conn);
        Boolean c = rol.equals("Student");
        Boolean d = rol.equals("Admin");
        Boolean e = rol.equals("Teacher");
        if (!captcha.isCorrect(rcap)) {
            response.sendRedirect("Register.jsp");
            return;
        }
        if ((b != true) && (a) && (c)) {
            registermodel re = new registermodel();
            int i = re.newinfo(username, enpass, rol, conn);
            insertmodel in = new insertmodel();
            int v = in.newstu(username, conn);
            if (v == 1) {

                response.sendRedirect("landingpage.jsp");
            } else {

                response.sendRedirect("error.jsp");

            }
        } else if ((b != true) && (a)) {
            registermodel re = new registermodel();
            int i = re.newinfo(username, enpass, rol, conn);

            if (i == 1) {

                response.sendRedirect("landingpage.jsp");
            } else {

                response.sendRedirect("error.jsp");

            }
        } else {

            response.sendRedirect("error.jsp");

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
