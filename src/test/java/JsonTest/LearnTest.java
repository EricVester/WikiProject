package JsonTest;

import Exceptions.ConnectionCheck;
import Exceptions.RedirectionCheck;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.net.URLConnection;

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




}
