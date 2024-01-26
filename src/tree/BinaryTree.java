package tree;

/**
 * @author MiskuZero
 */
public abstract class BinaryTree {

    protected Node root;

    protected int size;

    /**
     * 添加节点
     *
     * @param key 关键字
     * @return true - 如果添加成功，否则为false
     */
    public abstract boolean add(int key);

    /**
     * 删除节点
     *
     * @param key 关键字
     * @return true - 如果删除成功，否则为false
     */
    public abstract boolean del(int key);

    public int size() {
        return size;
    }

    protected static class Node {

        protected int key;
        protected Node left;
        protected Node right;

        protected Node(int key) {
            this.key = key;
        }

    }


}
