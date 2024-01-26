package tree;

/**
 * @author MiskuZero
 */
public abstract class BinaryTree {

    protected TreeNode root;

    protected int size;

    /**
     * 添加节点
     *
     * @param key 关键字
     * @return true - 如果添加成功，否则为false
     */
    protected abstract boolean add(int key);

    /**
     * 删除节点
     *
     * @param key 关键字
     * @return true - 如果删除成功，否则为false
     */
    protected abstract boolean del(int key);

    protected static class TreeNode {

        protected int key;
        protected TreeNode left;
        protected TreeNode right;

        protected TreeNode(int key) {
            this.key = key;
        }

    }


}
