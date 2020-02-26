package Exceptions;

import java.net.URL;
import java.net.URLConnection;

public class ConnectionCheck {
     public void checkURL(){
        try{
            URL url = new URL("https://www.wikipedia.org");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Connection Successful");

        }
        catch (Exception e){
            System.out.println("Internet Not Connected");
        }
    }


}
