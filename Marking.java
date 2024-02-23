import java.util.HashMap;
import java.util.Map;

public class Marking {
    private final Map<Place, Integer> tokenMap = new HashMap<>();

    public boolean setToken(Place place, Integer value) {
        return tokenMap.put(place, value) == null;
    }

    public int getTokens(Place place) {
        return tokenMap.getOrDefault(place, 0);
    }

    private void foo() {
        tokenMap.entrySet();
    }
}
