package JobOonja.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import JobOonja.JoboonjaDB.User;
import JobOonja.Services.GetAllUsers;

@WebServlet("/users")
public class ShowUsersCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetAllUsers getAllUsers = new GetAllUsers();
        ArrayList<User> users  = getAllUsers.get();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/Users.jsp").forward(request,response);
    }
}
