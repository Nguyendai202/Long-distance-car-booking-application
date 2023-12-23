package cab.model.Coach;

public class Coach implements Comparable<Coach>{
    private int availableSeats;

    private final String licencePlate;

    public Coach(String licencePlate) {
        this.availableSeats = 40;
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat(int seatBooked){
        availableSeats -=  seatBooked;
    }

    public void reachedStation(){
        this.availableSeats = 40;
    }

    @Override
    public int compareTo(Coach other) {
        return licencePlate.compareTo(other.licencePlate);
    }

}
