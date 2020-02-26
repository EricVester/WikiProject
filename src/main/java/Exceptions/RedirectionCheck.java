package Exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class RedirectionCheck {
    public void redirectionFinder(String topic) throws IOException{
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(topic,("utf-8")) + "&rvprop=timestamp|user&rvlimit=24&redirects");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        System.out.println(bufferedReader);
        System.out.println(url);
        bufferedReader.close();
    }
}
