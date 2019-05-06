package JobOonja.Functions;

import JobOonja.itemException.NotEnoughSkillsException;
import JobOonja.Entities.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Functions {

//    public static HashMap<String,ArrayList<String>> endorsedSkill = new HashMap<>();

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
            throw new NotEnoughSkillsException("NotEnoughSkillsException");
    }


    public static void writeError(HttpExchange httpExchange,String ms) throws IOException{
        String response = "<html>" + "<body>"+ms+"</body>" + "</html>";
        httpExchange.sendResponseHeaders(403,response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static ArrayList<String> getTokenizeUrl(String url){
        ArrayList<String> token = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(url, "/");
        while (tokenizer.hasMoreTokens()){
            token.add(tokenizer.nextToken());
        }
        return token;
    }

    public static JsonObject createJsonResponse(String msg,Integer code,Boolean success){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msg",msg);
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("success",success);
        return jsonObject;
    }

    public static JsonObject createJsonResponseForEndorse(String msg,Integer code,Boolean success,ArrayList<String> en){
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("msg",msg);
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("success",success);
        for (String s: en){
            jsonArray.add(s);
        }
        jsonObject.add("endorsedSkill",jsonArray);
        return jsonObject;
    }


    public static String addStaticUser1(){ //!!!!!

        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        JsonObject jsonObject4 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id","1");
        jsonObject.addProperty("firstName","ژان");
        jsonObject.addProperty("lastName","ژاک روسو");
        jsonObject.addProperty("jobTitle","برنامه نویس وب");
        jsonObject.addProperty("profilePictureURL","https://upload.wikimedia.org/wikipedia/en/4/4c/Maurice_Quentin_de_La_Tour_-_Portrait_of_Jean-Jacques_Rousseau_-_adjusted.jpg");
        jsonObject.addProperty("bio","آقا ژان ژاک روسو می\u200Cگه عید بسیار زیباست😊😊");
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
    public static String addStaticUser2(){ //!!!!!

        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        JsonObject jsonObject4 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id","2");
        jsonObject.addProperty("firstName","نقی");
        jsonObject.addProperty("lastName","معمولی");
        jsonObject.addProperty("jobTitle","برنامه نویس معمولی");
        jsonObject.addProperty("profilePictureURL","https://kandoonews.com/images/news/630/thumbs/630.jpg");
        jsonObject.addProperty("bio","سیکل");
        jsonObject1.addProperty("name","Node.js");
        jsonObject1.addProperty("point",5);
        jsonArray.add(jsonObject1);
        jsonObject2.addProperty("name","PHP");
        jsonObject2.addProperty("point",4);
        jsonArray.add(jsonObject2);
        jsonObject3.addProperty("name","MySQL");
        jsonObject3.addProperty("point",2);
        jsonArray.add(jsonObject3);
        jsonObject.add("skills",jsonArray);
        return jsonObject.toString();
    }
    public static String addStaticUser3(){ //!!!!!

        JsonObject jsonObject1 = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        JsonObject jsonObject4 = new JsonObject();
        JsonObject jsonObject5 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id","3");
        jsonObject.addProperty("firstName","فامیل");
        jsonObject.addProperty("lastName","دور");
        jsonObject.addProperty("jobTitle","برنامه نویس دوری");
        jsonObject.addProperty("profilePictureURL","https://images.chesscomfiles.com/uploads/v1/user/20102042.6349e34c.161x161o.72c98194bbfc.jpeg");
        jsonObject.addProperty("bio","که با این در اگر در بند در مانند درمانند");
        jsonObject1.addProperty("name","React");
        jsonObject1.addProperty("point",7);
        jsonArray.add(jsonObject1);
        jsonObject2.addProperty("name","Javascript");
        jsonObject2.addProperty("point",6);
        jsonArray.add(jsonObject2);
        jsonObject3.addProperty("name","CSS");
        jsonObject3.addProperty("point",3);
        jsonArray.add(jsonObject3);
        jsonObject4.addProperty("name","SQL");
        jsonObject4.addProperty("point",4);
        jsonArray.add(jsonObject4);
        jsonObject5.addProperty("name","Node.js");
        jsonObject5.addProperty("point",5);
        jsonArray.add(jsonObject5);
        jsonObject.add("skills",jsonArray);
        return jsonObject.toString();
    }
}
