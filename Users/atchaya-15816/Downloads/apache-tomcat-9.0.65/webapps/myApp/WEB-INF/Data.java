

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Data{
	public static JSONArray getData(){
		JSONArray data_obj = null;
		try{
			URL url = new URL("https://my-json-server.typicode.com/typicode/demo/db");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.connect();
		    if(conn.getResponseCode() == 200) {
		    	Scanner scan = new Scanner(url.openStream());
		        String temp = "";
		        while(scan.hasNext()) {
		    	    temp += scan.nextLine();
	            }
	            JSONParser parse = new JSONParser();
                data_obj = (JSONArray) parse.parse(temp);
            }
		}
		catch(Exception e){}
		return data_obj;
    }
}