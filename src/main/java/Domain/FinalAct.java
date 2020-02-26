package Domain;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.text.html.ListView;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public  class FinalAct{


    private String topic;
    URL url = null;

    public FinalAct(URL url, String topic) throws IOException {
        this.url = url;
        this.topic = topic;
    }

    public void finalTest() {

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
        List<Revisor> revisions = parser.parse(rootElement);


        for (Revisor r: revisions) {
            System.out.println(r);
        }

    }

}
