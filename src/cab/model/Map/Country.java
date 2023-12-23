package cab.model.Map;
import cab.model.ALVTree.AVLTree;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private AVLTree<City> cities = new AVLTree<>();

    public void addCity(City city){
        cities.insert(city);
    }
    public City search(String name){
        City city = new City(name);
        return cities.search(city);
    }
    public ArrayList<City> getCities(){
        return cities.getAllKeys();
    }
    public Stopover search(List<String> destinations) {
        City city = this.search(destinations.get(0));
        District district = city.search(destinations.get(1));
        Stopover stopover = district.search(destinations.get(2));
        stopover.setDistrict(district);
        stopover.setCity(city);
        return stopover;
    }
}
