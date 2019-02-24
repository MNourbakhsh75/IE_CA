package HttpServer;

import AuctionData.*;
//import com.sun.java.util.jar.pack.Package;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import itemException.*;
import com.sun.java.util.jar.pack.*;

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
        Integer counter = 0;
        Integer counter1 = 0;
        try {
            User user = this.myAuction.getUserBaseOnId("1");
            for(Project p : projects){
                try {
                    counter1++;
                    checkForEnoughSkills(user.getSkills(),p.getSkills());
                    response = createAllProjectResponse(stringBuilder,p,2);
                    counter++;
                } catch (NotEnoughSkillsException e) {
                    if((counter1 == projects.size()) && (counter==0)){
                        String m = "there is no project with your skills :(";
                        writeError(t,m);
                    }
                }
            }
        }catch (itemNotFoundException ie){
            System.out.println("oops...user not found !!");
        }
        response = createAllProjectResponse(stringBuilder,null,3);
        writeOnOutPut(t,response);
    }
}
