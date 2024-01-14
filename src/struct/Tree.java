package struct;

public abstract class Tree {

    protected TreeNode root;

    protected abstract boolean add(int val);

    protected abstract boolean del(int val);

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

    }


}
