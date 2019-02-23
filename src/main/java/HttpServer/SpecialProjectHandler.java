package HttpServer;

import AuctionData.MyAuction;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class SpecialProjectHandler implements HttpHandler {
    private MyAuction myAuction;
    public SpecialProjectHandler(MyAuction myAuction){
        this.myAuction = myAuction;
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
