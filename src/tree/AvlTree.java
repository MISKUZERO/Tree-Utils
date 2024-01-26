package tree;

/**
 * @author MiskuZero
 */
public class AvlTree extends BinarySearchTree {

    private static class AvlNode extends Node {

        private int depth;

        protected AvlNode(int key) {
            super(key);
            depth = 1;
        }
    }

    @Override
    public boolean add(int key) {
        return addNode((AvlNode) root, null, key);
    }

    private boolean addNode(AvlNode node, AvlNode father, int key) {
        if (node == null) {
            if (father == null) {
                root = new AvlNode(key);
                size++;
                return true;
            }
            if (key < father.key)
                father.left = new AvlNode(key);
            else
                father.right = new AvlNode(key);
            size++;
            return true;
        } else {
            if (key < node.key)
                return addNode((AvlNode) node.left, node, key);
            else if (key > node.key)
                return addNode((AvlNode) node.right, node, key);
            else
                return false;
        }
    }

    @Override
    public boolean del(int key) {
        return super.del(key);
    }


}
