package tree;

/**
 * @author MiskuZero
 */
public class AvlTree extends BinarySearchTree {

    @Override
    public boolean add(int key) {
        if (root == null) {
            root = new TreeNode(key);
            size++;
            return true;
        }
        return addNode(root, null, key);
    }

    private boolean addNode(TreeNode node, TreeNode father, int key) {
        if (node == null) {
            father.left = new TreeNode(key);
            size++;
        } else {
            if (key < node.key) {
                addNode(node.left, node, key);
            } else if (key > node.key) {
                addNode(node.right, node, key);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean del(int key) {
        return super.del(key);
    }


}
