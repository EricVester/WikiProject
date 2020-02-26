package Domain;

public class WikiInfo {
    private String user;
    private Integer timestamp;

    WikiInfo(String user, Integer timestamp){
        this.user = user;
        this.timestamp = timestamp;

    }

    public String getUser() {
        return user;
    }

    public Integer getTimeStamp() {
        return timestamp;
    }
}
