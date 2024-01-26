package tree;

/**
 * @author MiskuZero
 */
public class AvlTree extends BinarySearchTree {

    @Override
    public boolean add(int key) {
        return addNode(root, null, key);
    }

    private boolean addNode(TreeNode node, TreeNode father, int key) {
        if (node == null) {
            if (father == null) {
                root = new TreeNode(key);
                size++;
                return true;
            }
            if (key < father.key)
                father.left = new TreeNode(key);
            else
                father.right = new TreeNode(key);
            size++;
            return true;
        } else {
            if (key < node.key)
                return addNode(node.left, node, key);
            else if (key > node.key)
                return addNode(node.right, node, key);
            else
                return false;
        }
    }

    @Override
    public boolean del(int key) {
        return super.del(key);
    }


}
