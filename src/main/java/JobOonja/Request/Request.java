package JobOonja.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public String getReq(URL url) throws IOException {
        String response = null;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        httpURLConnection.setRequestMethod("GET");
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            response = stringBuffer.toString();
            bufferedReader.close();
        } else {
            System.out.println("GET request not worked");
        }
        return response;
    }
}
