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

public class Comment extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream stream = null;
        try{
            URL url = new URL("https://my-json-server.typicode.com/typicode/demo/comments");
            HttpURLConnection myCon = (HttpURLConnection)url.openConnection();
            myCon.setRequestMethod("GET");
            // myCon.setDoOutput(true);
            stream = myCon.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = in.readLine()) != null) {
                resp.getWriter().print(line);
            }
        // String output[] = inputLine.split("[,\\[\\{\\]\\}]");
        // for(String e : output){
        //     resp.getWriter().println(e);
        // }
        // resp.getWriter().println("hrllo");
            resp.setContentType("text/html");
            resp.getWriter().println("<html><head><title>Comment</title><style type=\"text/css\">.space{width: 200px;height: 40px;}.des{padding-top: 150px;padding-left: 250px;}</style></head><body><form class=\"des\" action=\"/Comment\" method=\"POST\" enctype=\"/POST\">Enter Comment : <br/><br/><input type=\"text/plain\" name=\"value\" class=\"space\"><br/><br/><input type=\"submit\" name=\"Submit\"></form></body></html>");
        }
        catch(Exception e){}
        finally{
            stream.close();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream stream=null;
        try{
            URL myint = new URL("https://my-json-server.typicode.com/typicode/demo/comments");
            HttpURLConnection myCon = (HttpURLConnection)myint.openConnection();
            myCon.setRequestMethod("POST");
            myCon.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(myCon.getOutputStream());
            String value = req.getParameter("value");
            out.write(value);
            out.close();
            stream = myCon.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
              resp.getWriter().println(line);
            }
        }
        catch(Exception e){}
        finally{
            stream.close();
        }
    }
}