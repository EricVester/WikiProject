package Domain;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Revisor {
    @SerializedName("user")
    String userName;
    @SerializedName("timestamp")
    Date date;

    public Revisor(String userName, Date date) {
        this.userName = userName;
        this.date = date;
    }

    public Revisor(String userName, String date){
        this.userName = userName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try{
            this.date = simpleDateFormat.parse(date);
        }
        catch (ParseException p){
            p.printStackTrace();
        }


    }

    @Override
    public String toString() {
        return "Revisor{" +
                "userName='" + userName + '\'' +
                ", date=" + date +
                '}';
    }
}
