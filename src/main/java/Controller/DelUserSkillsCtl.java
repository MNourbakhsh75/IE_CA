package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import JoboonjaDB.Project;
import JoboonjaDB.User;
import Services.GetAllUsers;
import Services.ShowOneProject;
import itemException.*;
import static Functions.Functions.getTokenizeUrl;
import static Services.DeleteUserSkills.deleteUserSkills;

@WebServlet("/deluserskills")
public class DelUserSkillsCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String sName = request.getParameter("sName");
        String res;
        if(deleteUserSkills(userId,sName)){
            res = "Done :)";
        }else{
            res = "Failed :(";
        }
        request.setAttribute("res",res);
        request.getRequestDispatcher("/Response.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
