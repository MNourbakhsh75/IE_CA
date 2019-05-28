package JobOonja.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import JobOonja.Entities.User;
import JobOonja.Services.GetAllUsers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
@WebServlet("/user")
public class ShowAllUserCtl extends HttpServlet {

//    @RequestMapping(value = "/user",method= RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String getUsersHandler(HttpServletRequest request) {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getAttribute("userName").toString();
        System.out.println("All User : "+userName);
        GetAllUsers getAllUsers = new GetAllUsers();
        ArrayList<User> users  = getAllUsers.get(userName);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();

        for(User u : users){
            JsonElement jsonElement = gson.toJsonTree(u);
            jsonArray.add(jsonElement);
        }

        String out = jsonArray.toString();
        PrintWriter outStream = response.getWriter();
        outStream.println(out);
    }
}
