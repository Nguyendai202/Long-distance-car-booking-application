package cab.model.Map;

import java.util.*;
public class CustomMap {

    private ArrayList<Stopover> stopovers;
    private ArrayList<Path> paths;

    public CustomMap() {
        this.stopovers = new ArrayList<>();
        this.paths = new ArrayList<>();
    }

    public void addStopover(Stopover stopover) {
        stopovers.add(stopover);
    }

    public void addPath(Stopover stopover1, Stopover stopover2, double distance) {
        if (!stopovers.contains(stopover1) || !stopovers.contains(stopover2)) {
            return;
        }
        paths.add(new Path(stopover1, stopover2, distance));
    }

    public List<Stopover> findShortestPath(Stopover source, Stopover destination) {
        double[] shortestPaths = new double[stopovers.size()];
        int[] previousStopovers = new int[stopovers.size()];
        Arrays.fill(shortestPaths, Double.MAX_VALUE);
        Arrays.fill(previousStopovers, -1);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));
        Set<Stopover> visited = new HashSet<>();
        int sourceIndex = stopovers.indexOf(source);
        shortestPaths[sourceIndex] = 0;
        priorityQueue.add(new Node(sourceIndex, 0));
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            if (!visited.contains(stopovers.get(currentNode.index))) {
                visited.add(stopovers.get(currentNode.index));
                for (Path path : paths) {
                    if (stopovers.indexOf(path.getStopover1()) == currentNode.index) {
                        int neighborIndex = stopovers.indexOf(path.getStopover2());
                        double newDistance = shortestPaths[currentNode.index] + path.getDistance();
                        if (newDistance < shortestPaths[neighborIndex]) {
                            shortestPaths[neighborIndex] = newDistance;
                            previousStopovers[neighborIndex] = currentNode.index;
                            priorityQueue.add(new Node(neighborIndex, newDistance));
                        }
                    }
                }
            }
        }
        return reconstructPath(stopovers.indexOf(destination), previousStopovers);
    }

    private List<Stopover> reconstructPath(int destinationIndex, int[] previousStopovers) {
        List<Stopover> path = new ArrayList<>();
        for (int at = destinationIndex; at != -1; at = previousStopovers[at]) {
            path.add(stopovers.get(at));
        }
        List<Stopover> result = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--){
            result.add(path.get(i));
        }
        return result;
    }

    public List<List<Stopover>> findShortestPath(Stopover source, District destinations){
        List<List<Stopover>> shortestPaths = new ArrayList<>();
        for (Stopover destination : destinations.getStopovers()){
            shortestPaths.add(findShortestPath(source, destination));
        }
        return shortestPaths;
    }
    public List<List<List<Stopover>>> findShortestPath(Stopover source, City destinations){
        List<List<List<Stopover>>> shortestPaths = new ArrayList<>();
        for (District destination : destinations.getDistricts()){
            shortestPaths.add(findShortestPath(source, destination));
        }
        return shortestPaths;
    }
    private record Node(int index, double distance) {
    }
}
