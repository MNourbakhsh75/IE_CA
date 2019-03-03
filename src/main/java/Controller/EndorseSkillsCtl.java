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

@WebServlet("/endorse/*")
public class EndorseSkillsCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> token = getTokenizeUrl(request.getPathInfo());
        if(token.size() != 0){

        }else{
            response.sendError(404, "incorrect url");
        }
    }
}
