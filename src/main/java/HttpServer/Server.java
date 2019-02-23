package HttpServer;

import AuctionData.MyAuction;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static com.sun.net.httpserver.HttpServer.create;

public class Server {
    private MyAuction myAuction;
    public Server(MyAuction myAuction){
        this.myAuction = myAuction;
    }
    public void startServer() throws Exception {
        HttpServer server = create(new InetSocketAddress(8080), 0);
        server.createContext("/project", new AllProjectHandler(this.myAuction));
//        server.createContext("/test2", new MyHandler(2));
        server.setExecutor(null);
        server.start();
    }

}
