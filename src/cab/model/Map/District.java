package cab.model.Map;

import cab.model.ALVTree.AVLTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class District implements Comparable<District>{
    private AVLTree<Stopover> stopovers;
    private String name;
    private City city;

    public District(String name, AVLTree<Stopover> stopovers) {
        this.name = name;
        this.stopovers = stopovers;
    }
    public District(String name){
        this.name = name;
        this.stopovers = new AVLTree<>();

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {this.city = city;}
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStopover(Stopover stopover){
        stopovers.insert(stopover);
    }
    public Stopover randomChoiceStopover() {
        List<Stopover> stopoverList = stopovers.getAllKeys();
        return stopoverList.get(new Random().nextInt(stopoverList.size() - 1));
    }
    @Override
    public String toString() {
        return "District: " + name;
    }

    @Override
    public int compareTo(District other) {
        return this.name.compareTo(other.name);
    }
    public Stopover search(String name){
        Stopover stopover = new Stopover(name);
        return stopovers.search(stopover);
    }
    public void delete(String name){
        Stopover stopover = new Stopover(name);
        stopovers.delete(stopover);
    }
    public List<Stopover> getStopovers(){
        return stopovers.getAllKeys();
    }
}
