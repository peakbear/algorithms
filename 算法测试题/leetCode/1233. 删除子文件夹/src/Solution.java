import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public List<String> run(String[] folder) {
        Arrays.sort(folder);
        Set<String> set = new TreeSet<>((o1, o2) -> {
            if (o1.length() > o2.length() && o1.substring(0, o2.length() + 1).equals(o2 + "/")) {
                return 0;
            }
            return o1.compareTo(o2);
        });
        Collections.addAll(set, folder);
        return new ArrayList<>(set);

    }
}

