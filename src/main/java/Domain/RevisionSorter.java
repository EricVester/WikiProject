package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RevisionSorter {
    public ArrayList<Revisor> sort(ArrayList<Revisor> unsortedRevisions){
        HashMap<String, ArrayList<Date>> namedRevisionsMap = new HashMap<>();
        for(Revisor r: unsortedRevisions){
            if(namedRevisionsMap.containsKey(r.userName))
                namedRevisionsMap.put(r.userName, new ArrayList<>());
            namedRevisionsMap.get(r.userName).add(r.date);
        }
        ArrayList<String> namesPartiallySorted = new ArrayList<>();
        ArrayList<String> namesUnsortedByFrequency = new ArrayList<>(namedRevisionsMap.keySet());
        while(namesUnsortedByFrequency.size() > 0){
            String mostFrequentName = null;
            int mostFrequentCount = 0;
            for(String name : namesUnsortedByFrequency){
                if(mostFrequentName == null || namedRevisionsMap.get(name).size() > mostFrequentCount){
                    mostFrequentName = name;
                    mostFrequentCount = namedRevisionsMap.get(name).size();
                }
            }
            namesPartiallySorted.add(mostFrequentName);
            namesUnsortedByFrequency.remove(mostFrequentName);
        }
        ArrayList<Revisor> revisionsSortedByFrequency = new ArrayList<>();
        for(String name: namesPartiallySorted)
            for(Date date: namedRevisionsMap.get(name))
                revisionsSortedByFrequency.add(new Revisor(name, date));
        return revisionsSortedByFrequency;
    }
}
