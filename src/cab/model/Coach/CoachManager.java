package cab.model.Coach;


import cab.model.ALVTree.AVLTree;

public class CoachManager {
    private AVLTree<Coach> coaches = new AVLTree<>();
    public void addCoach (Coach coach){
        coaches.insert(coach);
    }
    public void deleteCoach(Coach coach){
        coaches.delete(coach);
    }
    public Coach searchCoach(String licensePlate){
        return coaches.search(new Coach(licensePlate));
    }
}
