import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/users_owu?serverTimezone=UTC","root","rootroot");

            PreparedStatement ps = connection.prepareStatement
                    ("insert into users_owu.user(login, password) values(?, ?)");

            ps.setString(1, login);
            ps.setString(2, password);
            int i = ps.executeUpdate();

            if(i > 0) {
                out.println("You are sucessfully registered");
            }

        }
        catch(Exception se) {
            se.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
