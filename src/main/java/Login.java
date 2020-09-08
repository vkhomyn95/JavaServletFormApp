import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users_owu?serverTimezone=UTC", "root","rootroot");

            PreparedStatement ps = connection.prepareStatement("select * from users_owu.user where login=? and password=?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet i = ps.executeQuery();
            User u = null;
            while (i.next()){
                u = new User();
                u.login = i.getString(2);
                u.password = i.getString(3);
            }
            if (u != null){
                out.println("You are logged in sfly" + u);
            }else {
                out.println("Wrong password or login");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
