package Functions;

import AuctionData.Project;
import AuctionData.Skills;
import AuctionData.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import itemException.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
    public static String createAllProjectResponse(StringBuilder stringBuilder, Project p, Integer flag){
        if(flag == 1) {
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
        }
        if(flag == 2){
            stringBuilder.append("<tr>\n" +
                    "<td>"+p.getId()+"</td>\n" +
                    "<td>"+p.getTitle()+"</td>\n" +
                    "<td>"+p.getBudget()+"</td>\n" +
                    "</tr>\n");
        }
        if(flag == 3){
            stringBuilder.append("</table>\n" +
                    "</body>\n" +
                    "</html>");
        }
        return stringBuilder.toString();
    }
    public static String createSpecialProjectResponse(StringBuilder stringBuilder,Project project,Integer flag){
        if(flag == 1){
            stringBuilder.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Project</title>\n" +
                    "</head>\n" +
                    "<body>");
        }
        if(flag == 2){
            stringBuilder.append("<ul>\n" +
                    "        <li>id: " + project.getId() + "</li>\n" +
                    "        <li>title: " + project.getTitle() + "</li>\n" +
                    "        <li>description: " + project.getDescription() + "</li>\n" +
                    "        <li>imageUrl: <img src=\"" + project.getImageUrl() + "\" style=\"width: 50px; height: 50px;\"></li>\n" +
                    "        <li>budget: " + project.getBudget() + "</li>\n" +
                    "    </ul>");
        }
        if(flag == 3){
            stringBuilder.append("</body>\n" +
                    "</html>");
        }
        return stringBuilder.toString();
    }
    public static ArrayList<String> getTokenizeUrl(String url){
        ArrayList<String> token = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(url, "/");
        while (tokenizer.hasMoreTokens()){
            token.add(tokenizer.nextToken());
        }
        return token;
    }
    public static String createSpecialUserResponse(StringBuilder stringBuilder,User u,Integer flag){
        if(flag == 1) {
            stringBuilder.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>User</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <ul>");
        }
        if(flag == 2){
            stringBuilder.append("<li>id: 1</li>\n" +
                    "        <li>first name: "+u.getFirstName()+"</li>\n" +
                    "        <li>last name: "+u.getLastName()+"</li>\n" +
                    "        <li>jobTitle: "+u.getJobTitle()+"</li>\n" +
                    "        <li>bio: "+u.getBio()+"</li>");
        }
        if(flag == 3){
            stringBuilder.append("</ul>\n" +
                    "</body>\n" +
                    "</html>");
        }
        return stringBuilder.toString();
    }

    public static String addStaticUser(){ //!!!!!

        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        JsonObject jsonObject4 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id","1");
        jsonObject.addProperty("firstName","علی");
        jsonObject.addProperty("lastName","شریف زاده");
        jsonObject.addProperty("jobTitle","برنامه نویس وب");
        jsonObject.addProperty("profilePictureURL","");
        jsonObject.addProperty("bio","روی سنگ قبرم بنویسید : خدا بیامرز میخواست خیلی کارا بکنه ولی پول نداشت");
        jsonObject1.addProperty("name","HTML");
        jsonObject1.addProperty("point",5);
        jsonArray.add(jsonObject1);
        jsonObject2.addProperty("name","Javascript");
        jsonObject2.addProperty("point",4);
        jsonArray.add(jsonObject2);
        jsonObject3.addProperty("name","C++");
        jsonObject3.addProperty("point",2);
        jsonArray.add(jsonObject3);
        jsonObject4.addProperty("name","Java");
        jsonObject4.addProperty("point",3);
        jsonArray.add(jsonObject4);
        jsonObject.add("skills",jsonArray);
        return jsonObject.toString();
    }
}
