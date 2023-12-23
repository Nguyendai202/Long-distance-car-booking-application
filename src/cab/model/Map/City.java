package cab.model.Map;
import cab.model.ALVTree.AVLTree;
import java.util.ArrayList;

public class City implements Comparable<City>{
    private AVLTree<District> districts;
    private String name;

    public City(String name) {
        this.name = name;
        this.districts = new AVLTree<>();
    }

    public City(String name, AVLTree<District> districts) {
        this.name = name;
        this.districts = districts;
    }

    @Override
    public int compareTo(City other) {
        return name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDistrict(District district){
        districts.insert(district);
    }
    public void delete(String name){
        District district = new District(name);
        districts.delete(district);
    }
    public District search(String name){
        District district = new District(name);
        return districts.search(district);
    }
    public ArrayList<District> getDistricts(){
        return districts.getAllKeys();
    }
    @Override
    public String toString() {
        return "City: " + name;
    }
}
