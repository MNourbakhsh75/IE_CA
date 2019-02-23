package Functions;

import AuctionData.Skills;
import com.sun.net.httpserver.HttpExchange;
import itemException.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Functions {

    public static void checkForEnoughSkills(ArrayList<Skills> uSkills, ArrayList<Skills> pSkills) throws NotEnoughSkillsException {
        ArrayList<Skills> u = uSkills;
        ArrayList<Skills> p = pSkills;
        ArrayList<Skills> t = new ArrayList<Skills>();
        for (Skills pkey : p) {
            for(Skills ukey : u){
                if (pkey.getName().equals(ukey.getName())) {
                    if(pkey.getPoint() <= ukey.getPoint()){
                        t.add(ukey);
                    }
                }
            }
        }
        if(t.size() != p.size())
            throw new NotEnoughSkillsException();
    }

    public static void writeOnOutPut(HttpExchange httpExchange, String data) throws IOException {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200,bytes.length);
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.close();
    }

    public static void writeError(HttpExchange httpExchange,String ms) throws IOException{
        String response = "<html>" + "<body>"+ms+"</body>" + "</html>";
        httpExchange.sendResponseHeaders(403,response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
