package cab.model.Map;

import java.util.*;

public class KNearestNeighbors {

    private List<Stopover> stopovers;

    public KNearestNeighbors(List<Stopover> stopovers) {
        this.stopovers = stopovers;
    }
    public void removeLast(){
        stopovers.remove(stopovers.size() - 1);
    }
    public int size(){
        return stopovers.size();
    }
    public ArrayList<Pair<Double, Stopover>> findKNN(Stopover target, int k) {
        PriorityQueue<Pair<Double, Stopover>> distances = new PriorityQueue<>(Comparator.comparing(Pair::first));
        for (Stopover point : stopovers) {
            if (!point.equals(target)) {
                double distance = haversineDistance(target, point);
                distances.offer(new Pair<>(distance, point));
            }
        }
        ArrayList<Pair<Double, Stopover>> result = new ArrayList<>();
        for (int i = 0; i < k && !distances.isEmpty(); i++) {
            Pair<Double, Stopover> item = distances.peek();
            result.add(distances.poll());
        }
        return result;
    }


    public record Pair<F, S>(F first, S second) {
    }

    public static double haversineDistance(Stopover point1, Stopover point2) {
        double R = 6371;
        double lat1 = Math.toRadians(point1.getLatitude());
        double lon1 = Math.toRadians(point1.getLongitude());
        double lat2 = Math.toRadians(point2.getLatitude());
        double lon2 = Math.toRadians(point2.getLongitude());
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}


