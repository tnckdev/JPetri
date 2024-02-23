import java.util.*;

public class PetriNet {
    private int highestPlaceID = 1;
    private int highestTransitionID = 1;

    private final Map<String, Place> placeMap = new HashMap<>();
    private final Map<String, Transition> transitionMap = new HashMap<>();

    private Marking initialMarking = new Marking();
    private Marking currentMarking = initialMarking;

    public PetriNet() {
    }

    public void setInitialMarking(String placeName, int value) {
        Place place = getPlace(placeName);
        initialMarking.setToken(place, value);
    }

    public boolean hasPlace(String name) {
        return placeMap.containsKey(name);
    }

    public void addPlace(String name) {
        if (hasPlace(name)) {
            throw new IllegalArgumentException("Place with the given name already exists!");
        }
        placeMap.put(name, new Place(highestPlaceID++, name));
    }

    public Place getPlace(String name) {
        if (!hasPlace(name)) {
            throw new IllegalArgumentException("Place with the given name doesn't exist!");
        }
        return placeMap.get(name);
    }


    public boolean hasTransition(String name) {
        return transitionMap.containsKey(name);
    }

    public void createTransition(String name) {
        if (hasTransition(name)) {
            throw new IllegalArgumentException("Transition with the given name already exists!");
        }
        transitionMap.put(name, new Transition(highestTransitionID++, name));
    }

    public Transition getTransition(String name) {
        if (!hasTransition(name)) {
            throw new IllegalArgumentException("Transition with the given name doesn't exist!");
        }
        return transitionMap.get(name);
    }

    public void addPreSet(String transitionName, String placeName) {
        addPreSet(transitionName, placeName, 1);
    }

    public void addPreSet(String transitionName, String placeName, int value) {
        Transition transition = getTransition(transitionName);
        Place place = getPlace(placeName);
        transition.addPreSet(place);
        transition.addPreSetRule(place, value);
    }

    public void addPostSet(String transitionName, String placeName) {
        addPostSet(transitionName, placeName, 1);
    }

    public void addPostSet(String transitionName, String placeName, int value) {
        Transition transition = getTransition(transitionName);
        Place place = getPlace(placeName);
        transition.addPostSet(place);
        transition.addPostSetRule(place, value);
    }

    public boolean transitionIsActive(String transitionName) {
        return transitionIsActive(transitionName, currentMarking);
    }

    public void printMarking() {
        printMarking(currentMarking);
    }

    public void printMarking(Marking marking) {
        List<Place> places = new ArrayList<>(placeMap.values());
        Collections.sort(places);
        int[] marks = new int[places.size()];
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            int tokens = marking.getTokens(place);
            marks[i] = tokens;
        }
        System.out.println(Arrays.toString(marks));
    }

    public boolean transitionIsActive(String transitionName, Marking preMarking) {
        Transition transition = getTransition(transitionName);
        return transition.isActive(preMarking);
    }

    public void fireTransition(String transitionName) {
        fireTransition(transitionName, currentMarking);
    }

    public void fireTransition(String transitionName, Marking preMarking) {
        Transition transition = getTransition(transitionName);
        currentMarking = transition.fire(preMarking);
    }


    public void print() {
        List<Place> places = new ArrayList<>(placeMap.values());
        Collections.sort(places);
        List<Transition> transitions = new ArrayList<>(transitionMap.values());
        Collections.sort(transitions);
        System.out.println("Places: ");
        for (Place place : places) {
            System.out.println(place);
        }
        System.out.println();
        System.out.println("Transitions: ");
        for (Transition transition : transitions) {
            transition.print();
        }
    }

}
