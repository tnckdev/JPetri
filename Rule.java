import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Rule {
    private final Set<Place> preSet;
    private final Set<Place> postSet;
    private final Map<Place, Integer> preSetRule = new HashMap<>();
    private final Map<Place, Integer> postSetRule = new HashMap<>();

    public Rule(Set<Place> preSet, Set<Place> postSet) {
        this.preSet = preSet;
        this.postSet = postSet;
    }

    public boolean addPreSetRule(Place place, int value) {
        if (!preSet.contains(place)) {
            throw new IllegalArgumentException("Given place is not part of the pre-set!");
        }
        return preSetRule.put(place, value) == null;
    }

    public boolean addPostSetRule(Place place, Integer value) {
        if (!postSet.contains(place)) {
            throw new IllegalArgumentException("Given place is not part of the post-set!");
        }
        return postSetRule.put(place, value) == null;
    }


    public int getPreSetTokens(Place place) {
        return preSetRule.getOrDefault(place, 0);
    }

    public int getPostSetTokens(Place place) {
        return postSetRule.getOrDefault(place, 0);
    }
}
