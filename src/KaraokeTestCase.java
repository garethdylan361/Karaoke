
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Gareth on 4/2/2017.
 */
public class KaraokeTestCase {
    private List<List<Integer>> groups;
    private int highestScore = 0;
    private List<List<Integer>> uniqueGroupsWithHighest;


    //initialises and validates a KaraokeTestCase. This will not add a combination group if they don't meet a<b<c or 0<s<10000
    public KaraokeTestCase(List<List<Integer>> groups){
        this.groups = groups.stream().filter(p -> p.get(0) < p.get(1) && p.get(1) < p.get(2)
                && p.get(3) > 0 && p.get(3) < 10000).collect(Collectors.toList());
        this.uniqueGroupsWithHighest = this.getUniqueGroups();
    }

    //returns the groups
    public List<List<Integer>> getGroups() {
        return this.groups;
    }

    //calculates the top 3 highest scores since they are all already the highest unique scores
    public int getHighestScore() {
       List<Integer> scores = this.uniqueGroupsWithHighest.stream().map((x) -> x.get(3))
               .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
       return (this.isValid()) ? scores.get(0) + scores.get(1) + scores.get(2) : -1;
    }

    //checks if there are more than three potential groups to calculate from
    public boolean isValid() {
        return (this.uniqueGroupsWithHighest.size() >= 3) ? true : false;
    }

    //builds a list of the leaders of each unique group for example: between 1 2 3 4 and 1 2 5 6 the second combination
    // would win out
    private List<List<Integer>> getUniqueGroups() {
        List<List<Integer>> results = new ArrayList<>();
        for(List<Integer> group : this.groups) results = validateAndAddResults(group, results);
        return results;
    }

    //validates each result and adds it if its score is higher than the last group that contain a similar member.
    private List<List<Integer>> validateAndAddResults(List<Integer> group,  List<List<Integer>> results) {
        int s1 = group.get(3);
        boolean add = true;
        for (Iterator<List<Integer>> iterator = results.iterator(); iterator.hasNext();) {
            List<Integer> result = iterator.next();
            int s2 = result.get(3);
            if(this.foundMatchingMembers(group, result)) {
                if(s1 > s2) iterator.remove();
                else add = false;
            }
        }
        if(add) results.add(group);
        return results;
    }

    //checks if there is a matching member in a group
    private boolean foundMatchingMembers(List<Integer> group, List<Integer> result){
        int a1 = group.get(0);
        int b1 = group.get(1);
        int c1 = group.get(2);
        int a2 = result.get(0);
        int b2 = result.get(1);
        int c2 = result.get(2);
        return a1 == a2 || a1 == b2 || a1 == c2 || b1 == a2 || b1 == b2 || b1 == c2 || c1 == a2 || c1 == b2 || c1 == c2;
    }


}
