package cab.model.ALVTree;

public class AVLNode<E extends Comparable<E>> {
    E key;
    int height;
    AVLNode<E> left, right;

    public AVLNode(E key) {
        this.key = key;
        this.height = 1;
    }
}
