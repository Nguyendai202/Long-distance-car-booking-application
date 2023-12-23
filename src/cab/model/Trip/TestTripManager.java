package cab.model.Trip;


import cab.model.Map.Country;
import cab.model.Map.Places;
import cab.model.Map.Stopover;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestTripManager {
    public static Country country = Places.addData();
    public static TripManager tripManager = new TripManager(country);
    public static void main(String[] args) {
//        String source = "Thành phố Hà Nội,Quận Ba Đình,Phường Vĩnh Phúc";
//        String destination = "Tỉnh Hà Tĩnh,Huyện Can Lộc,Xã Xuân Lộc";
//        String priorityMode = "Giá rẻ";
//        String[][] table = book(source, destination, priorityMode);
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table[0].length; j++) {
//                System.out.print(table[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
    private static String generatePhoneNumber() {
        Random random = new Random();
        String[] phoneNumberHead = {"070", "079", "077", "076", "078"};
        String head = phoneNumberHead[random.nextInt(0, 5)];
        for (int i = 0; i < 7; i++) {
            head += Integer.toString(random.nextInt(0, 10));
        }
        return head;
    }
    public static String[][] book(String source, String destination, String priorityMode) {
        String[] sources = source.split(",");
        String[] destinations = destination.split(",");
        Stopover start = country.search(Arrays.stream(sources).toList());
        Stopover end = country.search(Arrays.stream(destinations).toList());
        List<Trip> trips = tripManager.tripsRecommendation(start, end, priorityMode);
        if (trips == null || trips.size() == 0) {
            return null;
        } else {
            String[][] table = new String[5][6];
            for (int i = 0; i < 5; i++) {
                Trip trip = trips.get(i);
                table[i][0] = trip.getStart();
                table[i][1] = trip.getLastCity();
                table[i][2] = trip.getLicensePlate();
                table[i][3] = Integer.toString(trip.getPrice());
                table[i][5] = readRoute(trip.getRoute());
                table[i][4] = generatePhoneNumber();
            }
            return table;
        }

    }
    private static String readRoute(List<Stopover> route) {
        StringBuilder builder = new StringBuilder();
        for (Stopover s : route) builder.append("-->").append(s);
        builder.append("\n");
        return builder.toString();
    }
}