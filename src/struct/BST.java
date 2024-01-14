package struct;

public class BST extends Tree {

    public BST() {
    }

    public TreeNode getRoot() {
        return root;
    }

    public boolean add(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return true;
        }
        TreeNode tmp = root;
        while (true)
            if (val < tmp.val)
                if (tmp.left != null)
                    tmp = tmp.left;
                else {
                    tmp.left = new TreeNode(val);
                    return true;
                }
            else if (val > tmp.val)
                if (tmp.right != null)
                    tmp = tmp.right;
                else {
                    tmp.right = new TreeNode(val);
                    return true;
                }
            else
                return false;
    }

    public boolean del(int val) {
        return true;
    }
}
