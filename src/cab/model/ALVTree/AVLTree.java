package cab.model.ALVTree;
import java.util.ArrayList;
public class AVLTree<E extends Comparable<E>> {
    private AVLNode<E> root;

    private int height(AVLNode<E> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLNode<E> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode<E> node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private AVLNode<E> rightRotate(AVLNode<E> y) {
        AVLNode<E> x = y.left;
        AVLNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode<E> leftRotate(AVLNode<E> x) {
        AVLNode<E> y = x.right;
        AVLNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void insert(E key) {
        root = insert(root, key);
    }

    private AVLNode<E> insert(AVLNode<E> node, E key) {
        if (node == null) {
            return new AVLNode<>(key);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = insert(node.left, key);
        } else if (compareResult > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    public E search(E key) {
        AVLNode<E> resultNode = search(root, key);
        return (resultNode != null) ? resultNode.key : null;
    }

    private AVLNode<E> search(AVLNode<E> node, E key) {
        if (node == null || key == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return search(node.left, key);
        } else if (compareResult > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }
    public void delete(E key) {
        root = delete(root, key);
    }

    private AVLNode<E> delete(AVLNode<E> node, E key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = delete(node.left, key);
        } else if (compareResult > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.key = minValue(node.right);
            node.right = delete(node.right, node.key);
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private E minValue(AVLNode<E> node) {
        E minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }
    public ArrayList<E> getAllKeys() {
        ArrayList<E> keysList = new ArrayList<>();
        inOrderTraversal(root, keysList);
        return keysList;
    }
    private void inOrderTraversal(AVLNode<E> node, ArrayList<E> keysList) {
        if (node != null) {
            inOrderTraversal(node.left, keysList);
            keysList.add(node.key);
            inOrderTraversal(node.right, keysList);
        }
    }
}
