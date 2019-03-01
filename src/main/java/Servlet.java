
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import JoboonjaDB.*;
import itemException.*;
import static JoboonjaDB.JDB.accessDataBase;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Project project = accessDataBase().getProjectBaseOnId("182491ec-ad35-4682-a061-2f13edce6899");
            out.println("<h3>"+project.getTitle()+"</h3>");
        }catch (itemNotFoundException ie){
            out.println("<h3>://///</h3>");
        }
    }
}
