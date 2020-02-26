package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {

    public List<Revisor> parse (InputStream inputStream){
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject page = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray jsonArray = null;
        List<Revisor> revisorList = new ArrayList<>();
        for(Map.Entry<String,JsonElement> entry : page.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");

        }

        assert jsonArray !=null;
        for(JsonElement jElement : jsonArray){

            String username = jElement.getAsJsonObject().get("username").getAsString();
            Integer timestamp = jElement.getAsJsonObject().get("timestamp").getAsInt();
            System.out.println("Username is: " + username);
            System.out.println("The timestamp is: "+ timestamp);
        }

        return revisorList;




    }




}
