import Domain.Parser;
import Domain.Revisor;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What topic would you like to see the recent revisions for in Wikipedia?");
       String topic =br.readLine();
        Scanner scanner = new Scanner(topic);
        URL url = null;
        try {
            url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic, ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        URLConnection connection = null;
        try{
            connection = url.openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }
        InputStream jsonFile = null;
        try {
            jsonFile = connection.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        Reader reader = new InputStreamReader(jsonFile);
        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement= jsonParser.parse(reader);



    }
}
