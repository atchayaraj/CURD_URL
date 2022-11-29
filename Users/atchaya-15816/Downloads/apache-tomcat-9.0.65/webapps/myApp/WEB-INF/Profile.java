import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Profile extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream stream=null;
        try{
            URL url = new URL("https://my-json-server.typicode.com/typicode/demo/profile");
            HttpURLConnection myCon = (HttpURLConnection)url.openConnection();
            myCon.setRequestMethod("GET");
            stream = myCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine().replaceAll("[,\\[\\{\\]\\}]","")) != null) {
                resp.getWriter().println(line);
            }
            // String output[] = inputLine.split("[,\\[\\{\\]\\}]");
            // for(String e : output){
            //     resp.getWriter().println(e);
            // }
        }
        catch(Exception e){}
        finally{
            stream.close();
        }   
    }
}