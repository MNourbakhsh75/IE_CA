package JobOonja.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JobOonja.Services.UniqueUser;
import JobOonja.itemException.itemNotFoundException;
import JobOonja.Entities.User;
import JobOonja.Services.ShowOneProject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static JobOonja.Functions.Functions.createJsonResponse;



@WebServlet("/user/unique")
public class UniqueUserCtl extends HttpServlet {

//    @RequestMapping(value = "/user/unique",method= RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String uniqueUserHandler(@RequestParam String username) {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    JsonElement jsonElement = null;
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        UniqueUser uniqueUser = new UniqueUser();
        Boolean resp = uniqueUser.uniqueUser(username);
        if (resp){
            String out = createJsonResponse("نام کاربری معتبر", 200, true).toString();
            PrintWriter outStream = response.getWriter();
            outStream.println(out);
        }
        else {
            String out = createJsonResponse("نام کاربری تکراری", 406, false).toString();
            PrintWriter outStream = response.getWriter();
            outStream.println(out);
        }
    }
}
