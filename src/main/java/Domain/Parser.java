
package Domain;

import com.google.gson.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Parser {

    public ArrayList<Revisor> parse(String topic) throws UnsupportedEncodingException, MalformedURLException {

        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic, ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");

        try {
            URL url1 = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic, ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
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
        JsonObject rootObject = rootElement.getAsJsonObject();

        RevisonParser parser = new RevisonParser();
        ArrayList<Revisor> revisions = parser.parse(rootElement);
        return revisions;
    }



}