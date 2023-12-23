package cab.model.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Places {
    public static List<List<String>> readData() {
        String path = "D:\\KH_JAVA\\Cab Booking System\\src\\cab\\data\\Places with Coordinates.csv";
        boolean isHeadRow = true;
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (!isHeadRow) records.add(Arrays.asList(values));
                isHeadRow = false;
            }
        } catch (IOException ex) {
            System.out.println("Replace path \"" + path + "\" with absolute path");
        }
        return records;
    }
    public static Country addData() {
        List<List<String>> records = readData();
        Country country = new Country();
        String currentCity = "";
        String currentDistrict = "";
        String currentWard = "";
        City cityObj = null;
        District districtObj = null;
        Stopover stopoverObj = null;
        for (List<String> record : records) {
            String city = record.get(2);
            String district = record.get(1);
            String ward = record.get(0);
            if (!city.equals(currentCity)) {
                currentCity = city;
                currentDistrict = district;
                currentWard = ward;
                cityObj = new City(currentCity);
                districtObj = new District(currentDistrict);
                stopoverObj = new Stopover(currentWard);
                stopoverObj.setLatitude(Double.parseDouble(record.get(4)));
                stopoverObj.setLongitude(Double.parseDouble(record.get(5)));
                stopoverObj.setDistrict(districtObj);
                stopoverObj.setCity(cityObj);
                districtObj.addStopover(stopoverObj);
                cityObj.addDistrict(districtObj);
                country.addCity(cityObj);
            } else if (!district.equals(currentDistrict)) {
                currentDistrict = district;
                currentWard = ward;
                districtObj = new District(currentDistrict);
                stopoverObj = new Stopover(currentWard);
                stopoverObj.setLatitude(Double.parseDouble(record.get(4)));
                stopoverObj.setLongitude(Double.parseDouble(record.get(5)));
                stopoverObj.setDistrict(districtObj);
                stopoverObj.setCity(cityObj);
                cityObj.addDistrict(districtObj);
                districtObj.addStopover(stopoverObj);

            } else {
                stopoverObj = new Stopover(ward);
                stopoverObj.setLatitude(Double.parseDouble(record.get(4)));
                stopoverObj.setLongitude(Double.parseDouble(record.get(5)));
                stopoverObj.setDistrict(districtObj);
                stopoverObj.setCity(cityObj);
                districtObj.addStopover(stopoverObj);
            }
        }
        return country;
    }

    public static void main(String[] args) {
        Country country = addData();
        List<String> list = new ArrayList<>();
        list.add("Thành phố Hà Nội");
        list.add("Quận Ba Đình");
        list.add("Phường Vĩnh Phúc");
        System.out.println(country.search(list).getLatitude());
        System.out.println(country.search(list).getLongitude());
    }
}
