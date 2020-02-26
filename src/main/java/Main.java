import Domain.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.text.html.ListView;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.SortedMap;

public class Main {



    public static void main(String[] args) throws IOException {
        URL url =null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What topic would you like to see the recent revisions for in Wikipedia?");
       String topic =br.readLine();
        Scanner scanner = new Scanner(topic);
         url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic,("utf-8")) + "&rvprop=timestamp|user&rvlimit=24&redirects");
        FinalAct finalAct = new FinalAct(url,topic);
        finalAct.finalTest();

    }
}
