import java.util.*;

public class Transition implements Comparable<Transition> {
    private final int id;
    private final String name;
    private final Set<Place> preSet = new HashSet<>();
    private final Set<Place> postSet = new HashSet<>();
    private final Rule rule = new Rule(preSet, postSet);

    public Transition(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean addPreSet(Place place) {
        return preSet.add(place);
    }

    public boolean addPostSet(Place place) {
        return postSet.add(place);
    }


    public boolean isActive(Marking preMarking) {
        for (Place place : preSet) {
            int currentTokens = preMarking.getTokens(place);
            int requiredTokens = rule.getPreSetTokens(place);
            if (requiredTokens > currentTokens) {
                return false;
            }
        }
        return true;
    }

    public void addPreSetRule(Place place, int value) {
        rule.addPreSetRule(place, value);
    }

    public void addPostSetRule(Place place, int value) {
        rule.addPostSetRule(place, value);
    }

    public void print() {
        List<Place> preList = new ArrayList<>(preSet);
        List<Place> postList = new ArrayList<>(postSet);
        Collections.sort(preList);
        Collections.sort(postList);
        for (Place place : preList) {
            System.out.println(place + " --- " + rule.getPreSetTokens(place) + " ---> ");
        }
        System.out.println(toString());
        for (Place place : postList) {
            System.out.println(" --- " + rule.getPostSetTokens(place) + " ---> " + place);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Transition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Marking fire(Marking preMarking) {
        if (!isActive(preMarking)) {
            throw new IllegalArgumentException("Transition cannot fire with the given marking!");
        }
        Marking postMarking = new Marking();
        for (Place place : preSet) {
            int currentTokens = preMarking.getTokens(place);
            int requiredTokens = rule.getPreSetTokens(place);
            postMarking.setToken(place, currentTokens - requiredTokens);
        }
        for (Place place : postSet) {
            int currentTokens = preMarking.getTokens(place);
            int additionalTokens = rule.getPostSetTokens(place);
            postMarking.setToken(place, currentTokens + additionalTokens);
        }
        return postMarking;
    }

    @Override
    public int compareTo(Transition that) {
        return Integer.compare(this.id, that.id);
    }
}
