package cab.model.Trip;

import  cab.model.Map.City;
import  cab.model.Map.Country;
import cab.model.Map.Stopover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripManager {
    TreeSet<Trip> trips = new TreeSet<>();

    public TripManager(Country country) {
        this.addTripFromDataFile("D:\\KH_JAVA\\Cab Booking System\\src\\cab\\data\\Trips-infor.csv", country);
    }

    public void addTrip(Trip trip){
        trips.add(trip);
    }
    public void addTripFromDataFile(String filePath, Country country){
        boolean isHeadRow = true;
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (!isHeadRow) records.add(Arrays.asList(values));
                isHeadRow = false;
            }
        } catch (IOException ex) {
            System.out.println("Replace path \"" + filePath + "\" with absolute path");
        }
        for (List<String> record : records) {
            trips.add(readTripInfo(record, country));
        }
    }
    public void removeTrip(Trip trip){
        trips.remove(trip);
    }
    public List<Trip> tripsRecommendation(Stopover start, Stopover destination, String base){
        if (base.equals("Giá rẻ")){
            return sortedTripsByPrices(start.getCity(), destination.getCity());
        } else if (base.equals("Khoảng cách gần")){
            return sortedTripsByMostConvenientDistance (start.getCity(), destination);
        }
        return null;
    }
    private List<Trip> sortedTripsByPrices (City startCity, City destination){
        List<Trip> mostAffordableTrips = new ArrayList<>();
        int count = 0;
        for (Trip trip : trips) {
            if (count > 5){
                break;
            }
            if (trip.getStart().equals(startCity.getName())){
                boolean isContainDestination = false;
                for (Stopover stopover : trip.getRoute()){
                    if (stopover.getCity().equals(destination)){
                        isContainDestination = true;
                        break;
                    }
                }
                if (isContainDestination) {
                    count ++;
                    trip.setLastCity(destination.getName());
                    mostAffordableTrips.add(trip);
                }
            }
        }
        return mostAffordableTrips;
    }
    private double calculateDistance(double latitude1, double longitude1,  double latitude2, double longitude2) {
        final double EARTH_RADIUS = 6371.0;
        double radianLatitude1 = Math.toRadians(latitude1);
        double radianLongitude1 = Math.toRadians(longitude1);
        double radianLatitude2 = Math.toRadians(latitude2);
        double radianLongitude2 = Math.toRadians(longitude2);
        double deltaLatitude = radianLatitude2 - radianLatitude1;
        double deltaLongitude = radianLongitude2 - radianLongitude1;
        double a = Math.pow(Math.sin(deltaLatitude / 2), 2) + Math.cos(radianLatitude1) * Math.cos(radianLatitude2) * Math.pow(Math.sin(deltaLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
    private List<Trip> sortedTripsByMostConvenientDistance (City start, Stopover finalDestination){
        PriorityQueue<Pair<Double, Trip>> minimumDistance = new PriorityQueue<>(Comparator.comparing(pair -> pair.first));
        for (Trip trip : trips){
            if (trip.getStart().equals(start.getName())) {
                Pair<Double, Trip> nearestDistance = new Pair<>(Double.MAX_VALUE, trip);
                for (Stopover stopover : trip.getRoute()) {
                    if (!stopover.getCity().equals(finalDestination.getCity())) {
                        continue;
                    }
                    if (stopover.getName().equals(finalDestination.getName())) {
                        nearestDistance.first = 0.0;
                        break;
                    }
                    double distance = calculateDistance(finalDestination.getLatitude(), finalDestination.getLongitude(), stopover.getLatitude(), stopover.getLongitude());
                    if (nearestDistance.first > distance) {
                        nearestDistance.first = distance;
                    }
                }
                if (nearestDistance.first != Double.MAX_VALUE) {
                    minimumDistance.add(nearestDistance);
                }
            }
        }
        List<Trip> sortedTripByPrice = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            if (minimumDistance.isEmpty()){
                break;
            }
            Trip trip = minimumDistance.poll().second;
            trip.setLastCity(finalDestination.getCity().getName());
            sortedTripByPrice.add(trip);
        }
        return sortedTripByPrice;
    }

    private class Pair<F, S>{
        F first;
        S second;
        public Pair (F first, S second){
            this.first = first;
            this.second = second;
        }
    }

    private Trip readTripInfo(List<String> line, Country country) {

        List<Stopover> route = new ArrayList<>();
        for (int i = 1; i  < line.size() - 2; i++){
            route.add(country.search(parseLocationString(line.get(i))));
        }
        int price = Integer.parseInt(line.get(line.size() - 2));
        String[] description = readTripsGeneralDescription(line.get(0));
        return new Trip(route, description[0], description[1], price);
    }
    public ArrayList<String> parseLocationString(String inputString) {
        ArrayList<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^\\(]*)\\(([^\\)]*)\\)");
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            String ward = matcher.group(1).trim();
            String districtAndCity = matcher.group(2).trim();
            String[] districtAndCityArray = districtAndCity.split("\\s*-\\s*");
            String district = districtAndCityArray[0].trim();
            String city = districtAndCityArray[1].trim();
            resultList.add(city);
            resultList.add(district);
            resultList.add(ward);
        }
        return resultList;
    }
    public static String[] readTripsGeneralDescription(String inputString) {
        String[] parts = inputString.split("[#:]");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
