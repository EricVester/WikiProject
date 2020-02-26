package JsonTest;

import Domain.RevisonParser;
import Domain.Revisor;
import Domain.WikiPageCheck;
import Exceptions.ConnectionCheck;
import Exceptions.RedirectionCheck;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class LearnTest {
    @Test
    public void testcountRevison() throws MalformedURLException {
        JsonArray  array = null;
        JsonParser parser = new JsonParser();
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=\" + \"Obama\" + \"&rvprop=timestamp|user&rvlimit=30&redirects");
        InputStream inputStream= getClass().getClassLoader().getResourceAsStream("sample.json");
        assert  inputStream !=null;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        for(Map.Entry<String,JsonElement> entry :pages.entrySet() ){
            JsonObject entryObject=  entry.getValue().getAsJsonObject();
            array= entryObject.getAsJsonArray("revisions");

        }
        System.out.println(array);
        System.out.println(url);
    }

    @Test
    public void ConnectionTester(){
        ConnectionCheck connectioncheck = new ConnectionCheck();
        connectioncheck.checkURL();

    }

    @Test
    public void RedirectTest() throws IOException {
        RedirectionCheck redirectionCheck = new RedirectionCheck();
        redirectionCheck.redirectionFinder("dog");

    }

    @Test
    public void PageCheck() throws IOException {
      URL  url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode("dog", ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
        URLConnection urlConnection = null;
        urlConnection = url.openConnection();
        InputStream jsonFile = null;
        jsonFile = urlConnection.getInputStream();
        Reader reader = new InputStreamReader(jsonFile);
        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement = jsonParser.parse(reader);
        RevisonParser parser = new RevisonParser();
        List<Revisor> revisions = parser.parse(rootElement);
        WikiPageCheck checkPage = new WikiPageCheck();

        URL  url2 = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode("jhdaK", ("utf-8")) + "&rvprop=timestamp|user&rvlimit=30&redirects");
        URLConnection urlConnection2 = null;
        urlConnection = url2.openConnection();
        InputStream jsonFile2 = null;
        jsonFile2 = urlConnection.getInputStream();
        Reader reader2 = new InputStreamReader(jsonFile2);
        JsonParser jsonParser2 = new JsonParser();
        JsonElement rootElement2 = jsonParser2.parse(reader2);
        RevisonParser parser2 = new RevisonParser();
        List<Revisor> revisions2 = parser2.parse(rootElement2);
        WikiPageCheck checkPage2 = new WikiPageCheck();

        Assertions.assertTrue(checkPage.checkExistence(revisions));
        Assertions.assertFalse(checkPage2.checkExistence(revisions2));

    }


    }



