package tree;

/**
 * @author MiskuZero
 */
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree() {
    }

    @Override
    public boolean add(int val) {
        if (root == null) {
            root = new TreeNode(val);
            size++;
            return true;
        }
        TreeNode curNode = root;
        while (true)
            if (val < curNode.val)
                if (curNode.left != null)
                    curNode = curNode.left;
                else {
                    curNode.left = new TreeNode(val);
                    size++;
                    return true;
                }
            else if (val > curNode.val)
                if (curNode.right != null)
                    curNode = curNode.right;
                else {
                    curNode.right = new TreeNode(val);
                    size++;
                    return true;
                }
            else
                return false;
    }

    @Override
    public boolean del(int key) {
        if (size == 1)
            if (key == root.val) {
                root = null;
                size--;
                return true;
            } else
                return false;
        TreeNode preNode = null;
        TreeNode curNode = root;
        while (curNode != null)
            if (key < curNode.val) {
                preNode = curNode;
                curNode = curNode.left;
            } else if (key > curNode.val) {
                preNode = curNode;
                curNode = curNode.right;
            } else {
                delNode(curNode, preNode);
                size--;
                return true;
            }
        return false;
    }

    private void delNode(TreeNode desNode, TreeNode preNode) {
        if (desNode.left != null) {
            //找中序前驱
            preNode = desNode;
            TreeNode curNode = desNode.left;
            while (curNode.right != null) {
                preNode = curNode;
                curNode = curNode.right;
            }
            desNode.val = curNode.val;
            delNode(curNode, preNode);
        } else if (desNode.right != null) {
            //找中序后继
            preNode = desNode;
            TreeNode curNode = desNode.right;
            while (curNode.left != null) {
                preNode = curNode;
                curNode = curNode.left;
            }
            desNode.val = curNode.val;
            delNode(curNode, preNode);
        } else {
            if (preNode.left == desNode)
                preNode.left = null;
            else
                preNode.right = null;
        }
    }

}
