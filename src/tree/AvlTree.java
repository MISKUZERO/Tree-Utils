package tree;

/**
 * @author MiskuZero
 */
public class AvlTree extends BinarySearchTree {

    protected static class AvlNode extends Node {

        protected int depth;

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
            if (key < node.key) {
                if (addNode((AvlNode) node.left, node, key)) {
                    node.depth = Math.max(depth((AvlNode) node.left), depth((AvlNode) node.right)) + 1;
                    return true;
                } else
                    return false;
            } else if (key > node.key) {
                if (addNode((AvlNode) node.right, node, key)) {
                    node.depth = Math.max(depth((AvlNode) node.left), depth((AvlNode) node.right)) + 1;
                    return true;
                } else
                    return false;
            } else
                return false;
        }
    }

    private int depth(AvlNode node) {
        return node == null ? 0 : node.depth;
    }

    @Override
    public boolean del(int key) {
        return super.del(key);
    }


}
