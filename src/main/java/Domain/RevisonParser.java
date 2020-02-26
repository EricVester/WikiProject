package Domain;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import javax.swing.text.html.ListView;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

  public class RevisonParser {

    public List<Revisor> parse (InputStream inputStream) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        List<Revisor> revisionList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }

        assert array != null;
        for (JsonElement element : array) {
            String username = element.getAsJsonObject().get("username").getAsString();
            Integer timestamp = element.getAsJsonObject().get("timestamp").getAsInt();

        }
        return revisionList;
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
