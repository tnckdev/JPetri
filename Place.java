public class Place implements Comparable<Place> {
    private final int id;
    private final String name;

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Place(int id, String name, int tokens) {
        this(id, name);
    }

    @Override
    public int compareTo(Place that) {
        return Integer.compare(this.id, that.id);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
