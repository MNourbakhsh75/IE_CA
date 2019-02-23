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
        String response;
        response = createAllProjectResponse(stringBuilder,null,1);
        ArrayList<Project> projects = this.myAuction.getProjects();
        try {
            User user = this.myAuction.getUserBaseOnId("1");
            for(Project p : projects){
                try {
                    checkForEnoughSkills(user.getSkills(),p.getSkills());
                    response = createAllProjectResponse(stringBuilder,p,2);
                } catch (NotEnoughSkillsException e) {

                }
            }
        }catch (itemNotFoundException ie){
            System.out.println("user not found");
        }
        response = createAllProjectResponse(stringBuilder,null,3);
        writeOnOutPut(t,response);
    }
}
