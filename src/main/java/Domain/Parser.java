package Domain;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {

    public List<Revisor> parse (InputStream inputStream) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject page = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray jsonArray = null;
        List<Revisor> revisorList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : page.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");

        }

        assert jsonArray != null;
        for (JsonElement jElement : jsonArray) {

            String username = jElement.getAsJsonObject().get("username").getAsString();
            Integer timestamp = jElement.getAsJsonObject().get("timestamp").getAsInt();

        }

        return revisorList;
    }
       public ArrayList<Revisor> parse(JsonElement rootElement){
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
            JsonArray revisions = null;
            for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
                JsonObject entryObject = entry.getValue().getAsJsonObject();
                revisions = entryObject.getAsJsonArray("revisions");
            }
            Type revisionListType = new TypeToken<ArrayList<Revisor>>() {
            }.getType();
            return gson.fromJson(revisions, revisionListType);
        }


    }

