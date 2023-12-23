package cab.model.Trip;
import cab.model.Map.Stopover;

import java.sql.Time;
import java.util.List;

public class Trip implements Comparable<Trip>{
    private Time departTime;
    private Time arriveTime;
    private int price;
    private List<Stopover> route;
    private final String licensePlate;
    private String startCity;
    private String lastCity;
    private int availableSeat;


    public String getStart() {
        return startCity;
    }

    public String getLastCity() {
        return lastCity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public int getPrice() {
        return price;
    }

    public List<Stopover> getRoute () {
        return route;
    }


    public Trip(List<Stopover> route, String licensePlate, String startCity, int price) {
        this.price = price;
        this.route = route;
        this.licensePlate = licensePlate;
        this.startCity = startCity;
        this.availableSeat = 40;
    }
    public void bookTickets(int numberOfTickets){
        if (availableSeat < numberOfTickets){
            System.out.println("Not enough available tickets");
            return;
        }
        availableSeat -= numberOfTickets;
    }

    public int getAvailableSeat(){
        return availableSeat;
    }

    public boolean isAvailable(){
        return availableSeat > 0;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLastCity(String lastCity) {
        this.lastCity = lastCity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Price: ").append(price).append(" VND \n");
        builder.append("Available seat: ").append(availableSeat).append(" seats \n");
        builder.append("License Plate : ").append(licensePlate).append("\n");
        builder.append("Start at: ").append(startCity).append("\n");
        builder.append("Route:");
        for (Stopover s : route) builder.append("\n").append(s);
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public int compareTo(Trip other) {
        if (this.price > other.price){
            return 1;
        } else if (this.licensePlate.equals(other.licensePlate)){
            return 0;
        }
        return -1;
    }
}
