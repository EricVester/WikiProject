package Domain;

import java.util.List;

public class WikiPageCheck {


    public WikiPageCheck() {

    }

    public boolean checkExistence(List<Revisor> revisions){
        try {
            if (revisions.size() != 0) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }
}




