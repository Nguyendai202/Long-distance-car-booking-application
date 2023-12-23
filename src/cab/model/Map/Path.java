package cab.model.Map;


public class Path {
    private final Stopover stopover1;
    private final Stopover stopover2;
    private final double distance;

    public Path(Stopover stopover1, Stopover stopover2, double distance) {
        this.stopover1 = stopover1;
        this.stopover2 = stopover2;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Stopover getStopover1() {
        return stopover1;
    }

    public Stopover getStopover2() {
        return stopover2;
    }

    @Override
    public String toString() {
        return "Distance from " + stopover1 + " to " + stopover2 + ": " + distance + " km";
    }
}
