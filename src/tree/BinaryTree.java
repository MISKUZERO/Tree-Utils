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
     * @param val 关键字
     * @return true - 如果添加成功，否则为false
     */
    protected abstract boolean add(int val);

    /**
     * 删除节点
     *
     * @param val 关键字
     * @return true - 如果删除成功，否则为false
     */
    protected abstract boolean del(int val);

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }


}
