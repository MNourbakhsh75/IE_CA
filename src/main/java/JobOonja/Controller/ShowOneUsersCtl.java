package JobOonja.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import JobOonja.itemException.itemNotFoundException;
import JobOonja.JoboonjaDB.User;
import JobOonja.Services.ShowOneProject;

import static JobOonja.Functions.Functions.getTokenizeUrl;

@WebServlet("/users/*")
public class ShowOneUsersCtl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        ArrayList<String> token = getTokenizeUrl(path);
        if(token.size() != 0 ){
            ShowOneProject showOneProject = new ShowOneProject();
            try {
                User user = showOneProject.getUserData(token.get(0));
                request.setAttribute("u",user);
                request.getRequestDispatcher("/OneUser.jsp").forward(request,response);
            }catch (itemNotFoundException ie){
                response.sendError(404, ie.getMessage());
            }
        }else{
            response.sendError(404, "incorrect url");
        }
    }
}
