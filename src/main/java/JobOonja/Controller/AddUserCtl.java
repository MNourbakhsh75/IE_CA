package JobOonja.Controller;


import JobOonja.Entities.User;
import JobOonja.Services.AddUser;
import JobOonja.Services.GetAllUsers;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static JobOonja.Functions.Functions.createJsonResponse;

@WebServlet("/register")
public class AddUserCtl extends HttpServlet {

//    @RequestMapping(value = "/register",method= RequestMethod.POST,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String addUserHandler(HttpServletRequest request){
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(request.getParameter("data"),JsonObject.class);
        AddUser addUser = new AddUser();
        Boolean resp = addUser.addUSer(jsonObject);
        if(resp) {
            String out = createJsonResponse("عملیات موفقیت آمیز بود", 200, true).toString();
            PrintWriter outStream = response.getWriter();
            outStream.println(out);
        }
        else{
            String out = createJsonResponse("خطا در ثبت نام...دوباره تلاش کنید", 406, false).toString();
            PrintWriter outStream = response.getWriter();
            outStream.println(out);
        }
    }
}
