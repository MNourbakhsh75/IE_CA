package JobOonja.Filters;


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;

import JobOonja.Entities.User;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebFilter(filterName = "JobOonja.Filters.TokenFilter", urlPatterns = {"/*"})
public class TokenFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/login", "/logout", "/register")));
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("kharoo");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token =  request.getHeader("token");
        String userName = null;
        Boolean ok = true;
        if(token != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary("joboonja"))
                        .parseClaimsJws(""+token+"").getBody();
                userName = claims.get("userName",String.class);
                ok= true;
            } catch (Exception exception) {
                System.out.println(exception);
                ok = false;
                response.setStatus(403);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("msg","احراز هویت موفق نبود");
                jsonObject.addProperty("success",false);
                PrintWriter printWriter = response.getWriter();
                printWriter.println(jsonObject.toString());
            }
        }else{
            System.out.println("null token");
            userName = null;
//            response.setStatus(401);
        }
        if(ok){
            request.setAttribute("userName",userName);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
