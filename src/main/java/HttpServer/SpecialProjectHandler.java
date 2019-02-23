package HttpServer;

import AuctionData.MyAuction;
import AuctionData.Project;
import AuctionData.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import itemException.NotEnoughSkillsException;
import itemException.itemNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<String> token = getTokenizUrl(httpExchange.getRequestURI().getPath());
        StringBuilder stringBuilder = new StringBuilder();
        String response;
        response = createSpecialProjectResponse(stringBuilder,null,1);
        if (token.size() != 1){
            try {
                User user = this.myAuction.getUserBaseOnId("1");
                Project project = this.myAuction.getProjectBaseOnId(token.get(1));
                try {
                    checkForEnoughSkills(user.getSkills(),project.getSkills());
                    response = createSpecialProjectResponse(stringBuilder,project,2);
                    response = createSpecialProjectResponse(stringBuilder,null,3);
                    writeOnOutPut(httpExchange,response);
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
    }
}
