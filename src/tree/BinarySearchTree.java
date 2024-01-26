package tree;

/**
 * @author MiskuZero
 */
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree() {
    }

    @Override
    public boolean add(int key) {
        if (root == null) {
            root = new TreeNode(key);
            size++;
            return true;
        }
        TreeNode cur = root;
        while (true)
            if (key < cur.key)
                if (cur.left != null)
                    cur = cur.left;
                else {
                    cur.left = new TreeNode(key);
                    size++;
                    return true;
                }
            else if (key > cur.key)
                if (cur.right != null)
                    cur = cur.right;
                else {
                    cur.right = new TreeNode(key);
                    size++;
                    return true;
                }
            else
                return false;
    }

    @Override
    public boolean del(int key) {
        if (size == 1)
            if (key == root.key) {
                root = null;
                size--;
                return true;
            } else
                return false;
        TreeNode father = null;
        TreeNode cur = root;
        while (cur != null)
            if (key < cur.key) {
                father = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                father = cur;
                cur = cur.right;
            } else {
                delNode(cur, father);
                size--;
                return true;
            }
        return false;
    }

    private void delNode(TreeNode node, TreeNode father) {
        if (node.left != null) {
            //找中序前驱
            father = node;
            TreeNode cur = node.left;
            while (cur.right != null) {
                father = cur;
                cur = cur.right;
            }
            node.key = cur.key;
            delNode(cur, father);
        } else if (node.right != null) {
            //找中序后继
            father = node;
            TreeNode cur = node.right;
            while (cur.left != null) {
                father = cur;
                cur = cur.left;
            }
            node.key = cur.key;
            delNode(cur, father);
        } else {
            if (father.left == node)
                father.left = null;
            else
                father.right = null;
        }
    }

}
