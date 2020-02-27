import Domain.*;
import Exceptions.ConnectionCheck;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.text.html.ListView;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.SortedMap;

public class Main {


    public static void main(String[] args) throws IOException {
        ConnectionCheck connectionCheck = new ConnectionCheck();
        if (connectionCheck.checkURL() == false) {
            System.out.println("No connection to Wikipedia try again later");
        } else {
            System.out.println("Connection Test passed you may continue");

            URL url = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What topic would you like to see the recent revisions for in Wikipedia?");
            String topic = br.readLine();
            Scanner scanner = new Scanner(topic);
            try {
                url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic, ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
            } catch (MalformedURLException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            URLConnection connection = null;
            try {
                connection = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream jsonFile = null;
            try {
                jsonFile = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Reader reader = new InputStreamReader(jsonFile);
            JsonParser jsonParser = new JsonParser();
            JsonElement rootElement = jsonParser.parse(reader);

            RevisonParser parser = new RevisonParser();
            ArrayList<Revisor> revisions = parser.parse(rootElement);
            WikiPageCheck checkPage = new WikiPageCheck();


            if (checkPage.checkExistence(revisions)) {
                for (Revisor r : revisions) {
                    System.out.println(r);
                }
            } else {
                System.out.println("No Wikipedia Page found for this Topic");
            }


        }
    }

    }


