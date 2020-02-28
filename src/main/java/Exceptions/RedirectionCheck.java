package Exceptions;

import com.google.gson.JsonObject;


public class RedirectionCheck {

    public static boolean redirectionFinder(JsonObject mainObject) {
        try{
            mainObject.getAsJsonObject("query").getAsJsonArray("redirects").get(0);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
