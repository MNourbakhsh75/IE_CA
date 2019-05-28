package JobOonja.Controller;


import JobOonja.Entities.User;
import JobOonja.Services.GetAllUsers;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/search/user")
public class SearchUserCtl extends HttpServlet {

//    @RequestMapping(value = "/user/search",method= RequestMethod.GET,
//            produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String searchUserHandler(@RequestParam(value = "q") String name, HttpServletRequest request){
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("q");
    System.out.println("q: "+name);
    String userName = request.getAttribute("userName").toString();
        SearchUser searchUser = new SearchUser();
        ArrayList<User> users  = searchUser.getSearchReasult(userName,name);
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
