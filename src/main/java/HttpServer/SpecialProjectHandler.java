package HttpServer;

import AuctionData.MyAuction;
import AuctionData.Project;
import AuctionData.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import itemException.NotEnoughSkillsException;
import itemException.itemNotFoundException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import static Functions.Functions.*;

public class SpecialProjectHandler implements HttpHandler {
    private MyAuction myAuction;
    public SpecialProjectHandler(MyAuction myAuction){
        this.myAuction = myAuction;
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("SpecialProjectHandler");
        StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        String context = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        String id = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Project</title>\n" +
                "</head>\n" +
                "<body>");
        System.out.println("id : " + id);
        if (id != null){
            try {
                User user = this.myAuction.getUserBaseOnId("1");
                Project project = this.myAuction.getProjectBaseOnId(id);
                try {
                    checkForEnoughSkills(user.getSkills(),project.getSkills());
                    stringBuilder.append("<ul>\n" +
                            "        <li>id: " + project.getId() + "</li>\n" +
                            "        <li>title: " + project.getTitle() + "</li>\n" +
                            "        <li>description: " + project.getDescription() + "</li>\n" +
                            "        <li>imageUrl: <img src=\"" + project.getImageUrl() + "\" style=\"width: 50px; height: 50px;\"></li>\n" +
                            "        <li>budget: " + project.getBudget() + "</li>\n" +
                            "    </ul>");
                }catch (NotEnoughSkillsException e){
                    String m = "not enough skills!";
                    writeError(httpExchange,m);
                }
            }catch (itemNotFoundException ie){
                String ms = "there is no project with this id";
                writeError(httpExchange,ms);
            }
        }else{
            String ms = "incorrect url";
            writeError(httpExchange,ms);
            return;
        }
        stringBuilder.append("</body>\n" +
                "</html>");
        writeOnOutPut(httpExchange,stringBuilder.toString());

    }
}
