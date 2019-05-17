package JobOonja.Controller;


import JobOonja.Entities.User;
import JobOonja.Services.AddUser;
import JobOonja.Services.GetAllUsers;
import JobOonja.Services.Login;
import JobOonja.Services.SearchUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import java.security.Key;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static JobOonja.Functions.Functions.createJsonResponse;

@Controller
public class LoginCtl {

    @RequestMapping(value = "/login",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserHandler(HttpServletRequest request){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(request.getParameter("data"),JsonObject.class);
        Login login = new Login();
        String userName = login.login(jsonObject);
        if(userName != null) {
            try {
                System.out.println("login");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timestamp.getTime());
                cal.add(Calendar.SECOND, 30);
                Timestamp newtimestamp = new Timestamp(cal.getTime().getTime());
//                Algorithm algorithmHS = Algorithm.HMAC256("joboonja");
//                String token = JWT.create()
//                        .withIssuer("IEJoboonja")
//                        .withIssuedAt(timestamp)
//                        .withExpiresAt(newtimestamp)
//                        .withClaim("userName",userName)
//                        .sign(algorithmHS);
                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
                byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("joboonja");
                Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
                String jws = Jwts.builder()
                        .setIssuer("IEJoboonja")
                        .setIssuedAt(timestamp)
                        .setExpiration(newtimestamp)
                        .claim("userName",userName)
                        .signWith(signatureAlgorithm,signingKey)
                        .compact();
                JsonObject respObject = new JsonObject();
                respObject.addProperty("msg","عملیات موفقیت آمیز بود");
                respObject.addProperty("code",200);
                respObject.addProperty("success",true);
                respObject.addProperty("token",jws);
                return respObject.toString();

            }catch (Exception e){
                System.out.println(e);
                return createJsonResponse("خطا در ورود به سیستم", 403, true).toString();
            }
        }
        else
            return createJsonResponse("نام کاربری یا کلمه عبور اشتباه است",403,false).toString();
    }
}
