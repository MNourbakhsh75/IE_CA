package HttpServer;

import AuctionData.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import itemException.*;

import static Functions.Functions.*;

public class AllProjectHandler implements HttpHandler {
    private MyAuction myAuction;
    public AllProjectHandler(MyAuction myAuction){
        this.myAuction = myAuction;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println("All Project");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Projects</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            text-align: center;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        td {\n" +
                "            min-width: 300px;\n" +
                "            margin: 5px 5px 5px 5px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>id</th>\n" +
                "            <th>title</th>\n" +
                "            <th>budget</th>\n" +
                "        </tr>\n");
        ArrayList<Project> projects = this.myAuction.getProjects();
        try {
            User user = this.myAuction.getUserBaseOnId("1");
            for(Project p : projects){
                try {
                    checkForEnoughSkills(user.getSkills(),p.getSkills());
                    stringBuilder.append("<tr>\n" +
                            "<td>"+p.getId()+"</td>\n" +
                            "<td>"+p.getTitle()+"</td>\n" +
                            "<td>"+p.getBudget()+"</td>\n" +
                            "</tr>\n");
                } catch (NotEnoughSkillsException e) {

                }
            }
        }catch (itemNotFoundException ie){
            System.out.println("user not found");
        }

        stringBuilder.append("</table>\n" +
                "</body>\n" +
                "</html>");

        writeOnOutPut(t,stringBuilder.toString());

//        t.getRequestHeaders().set("Content-Type","text/html;charset=utf-8");
//        byte[] bytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
//        t.sendResponseHeaders(200, bytes.length);
//
//        OutputStream os = t.getResponseBody();
//        os.write(bytes);
//        os.close();
    }
}
