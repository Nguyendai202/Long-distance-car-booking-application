package cab.model.Map;


public class Stopover implements Comparable<Stopover>{
    private double latitude;
    private double longitude;
    private String address;
    private String name;
    private int id;
    private District district;
    private City city;

    public Stopover(String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Stopover(String name, String address, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
    }
    public Stopover(String name, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setDistrict(District district) {
        this.district = district;
        this.city = district.getCity();
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Stopover(String name){
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" (").append(district.getName()).append(", ").append(city.getName());
        builder.append(")");
        return builder.toString();
    }
    @Override
    public int compareTo(Stopover other) {
        return this.name.compareTo(other.name);
    }

    public int getId() {
        return id;
    }
}
