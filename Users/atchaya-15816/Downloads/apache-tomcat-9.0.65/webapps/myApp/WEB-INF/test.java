
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
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Post extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream stream = null;
        try{
            URL myint = new URL("https://my-json-server.typicode.com/typicode/demo/posts");
            HttpURLConnection myCon = (HttpURLConnection)myint.openConnection();
            myCon.setRequestMethod("GET");
            stream = myCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            JSONObject obj = new JSONObject();
            while ((line = reader.readLine()) != null) {
                // resp.getWriter().print(line);
                obj.put(line);
            }
            ß
            // String output[] = inputLine.split("[,\\[\\{\\]\\}]");
            // JSONParser parser = new JSONParser();
            // JSONArray arr = (JSONArray)parser.parse(output);
            // for(String e : output){
            //     resp.getWriter().println(e);
            // }
            resp.setContentType("text/html");
            resp.getWriter().println(" <html><head><title>Create Posts</title> <style type=\"text/css\">.postId{padding-top: 190px; padding-left: 90px;}.but{cursor: pointer;border-radius: 10px;width: 110px; height: 25px;}</style></head><body><form class=\"postId\" method=\"POST\" action=\"/Post\"><input type=\"submit\" value=\"Create Post\" class = \"but\"> </form> </body></html>");
        }
        catch(Exception e){}
        finally{
            stream.close();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream stream = null;
        try{
            resp.setContentType("text/plain");
            resp.getWriter().println("Now you are in Post Method");
            URL url = new URL("https://my-json-server.typicode.com/typicode/demo/posts");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            String value = "title : Post 4";
            out.write(value);
            out.close();
            stream = httpCon.getInputStream();
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