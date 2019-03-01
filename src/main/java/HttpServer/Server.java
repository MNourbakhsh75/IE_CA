package HttpServer;

import AuctionData.MyAuction;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

import static com.sun.net.httpserver.HttpServer.create;

public class Server {
    private MyAuction myAuction;
    public Server(MyAuction myAuction){
        this.myAuction = myAuction;
    }
    public void startServer() throws Exception {
        HttpServer server = create(new InetSocketAddress(8082), 0);
        server.createContext("/project", new AllProjectHandler(this.myAuction));
        server.createContext("/project/", new SpecialProjectHandler(this.myAuction));
        server.createContext("/user/",new SpecialUserHandler(this.myAuction));
        server.setExecutor(null);
        server.start();
    }

}
