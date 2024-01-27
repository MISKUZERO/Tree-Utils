package tree;

/**
 * @author MiskuZero
 */
public class AvlTree extends BinarySearchTree {

    protected static class AvlNode extends Node {

        protected int height;

        protected AvlNode(int key) {
            super(key);
            height = 1;
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
                    balance(node, father);
                    return true;
                } else
                    return false;
            } else if (key > node.key) {
                if (addNode((AvlNode) node.right, node, key)) {
                    balance(node, father);
                    return true;
                } else
                    return false;
            } else
                return false;
        }

    }

    private void balance(AvlNode node, AvlNode father) {
        //更新节点高度
        int lDepth = height((AvlNode) node.left);
        int rDepth = height((AvlNode) node.right);
        node.height = Math.max(lDepth, rDepth) + 1;
        //调整平衡
        if (lDepth - rDepth > 1) {
            Node grandfather = father;
            father = node;
            node = (AvlNode) node.left;
            if (height((AvlNode) node.left) < height((AvlNode) node.right)) {
                //LR
                Node n = node.right;
                node.right = n.left;
                n.left = node;
                node.height--;
                ((AvlNode) n).height++;
                rRotate(n, father, grandfather);
            } else {
                //LL
                rRotate(node, father, grandfather);
            }
        } else if (lDepth - rDepth < -1) {
            Node grandfather = father;
            father = node;
            node = (AvlNode) node.right;
            if (height((AvlNode) node.left) > height((AvlNode) node.right)) {
                //RL
                Node n = node.left;
                node.left = n.right;
                n.right = node;
                node.height--;
                ((AvlNode) n).height++;
                lRotate(n, father, grandfather);
            } else {
                //RR
                lRotate(node, father, grandfather);
            }
        }
    }

    private void lRotate(Node node, AvlNode father, Node grandfather) {
        father.right = node.left;
        node.left = father;
        if (grandfather == null)
            root = node;
        else {
            if (grandfather.right == father)
                grandfather.right = node;
            else
                grandfather.left = node;
        }
        //更新节点高度
        father.height -= 2;
    }

    private void rRotate(Node node, AvlNode father, Node grandfather) {
        father.left = node.right;
        node.right = father;
        if (grandfather == null)
            root = node;
        else {
            if (grandfather.right == father)
                grandfather.right = node;
            else
                grandfather.left = node;
        }
        //更新节点高度
        father.height -= 2;
    }

    private static int height(AvlNode node) {
        return node == null ? 0 : node.height;
    }

    @Override
    public boolean del(int key) {
        return super.del(key);
    }


}
