package HttpServer;

import AuctionData.MyAuction;
import AuctionData.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import itemException.itemNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import static Functions.Functions.*;

public class SpecialUserHandler implements HttpHandler {
    private MyAuction myAuction;
    public SpecialUserHandler(MyAuction myAuction){
        this.myAuction = myAuction;
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println("SpecialUserHandler");
            String response;
            StringBuilder stringBuilder = new StringBuilder();
            response = createSpecialUserResponse(stringBuilder,null,1);
            ArrayList<String> token = getTokenizUrl(httpExchange.getRequestURI().getPath());
            if(token.size() != 1){
                try {
                    User user = this.myAuction.getUserBaseOnId(token.get(1));
                    response = createSpecialUserResponse(stringBuilder,user,2);
                    response = createSpecialUserResponse(stringBuilder,null,3);
                    writeOnOutPut(httpExchange,response);
                }catch (itemNotFoundException e){
                    writeError(httpExchange,"there is no user with this id");
                }
            }else{
                String m = "incorrect url";
                writeError(httpExchange,m);
            }
    }
}
