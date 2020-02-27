package Exceptions;

import java.net.URL;
import java.net.URLConnection;

public class ConnectionCheck {
     public boolean checkURL(){
        try{
            URL url = new URL("https://www.wikipedia.org");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;

        }
        catch (Exception e){
            return false;
        }
    }


}
