package struct;

import java.util.*;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public boolean add(int val) {
        TreeNode node = new TreeNode(val);
        final int value = node.val;
        TreeNode tmp = this;
        while (true)
            if (value < tmp.val)
                if (tmp.left != null)
                    tmp = tmp.left;
                else {
                    tmp.left = node;
                    return true;
                }
            else if (value > tmp.val)
                if (tmp.right != null)
                    tmp = tmp.right;
                else {
                    tmp.right = node;
                    return true;
                }
            else
                return false;
    }

    public static List<Integer> preTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            res.add(treeNode.val);
            if (treeNode.right != null)
                stack.push(treeNode.right);
            if (treeNode.left != null)
                stack.push(treeNode.left);
        }
        return res;
    }

    public static void recursionPreTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            recursionPreTraversal(root.left, res);
            recursionPreTraversal(root.right, res);
        }
    }

    public static List<Integer> midTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = root;
        while (true) {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            while (tmp == null) {
                if (stack.empty())
                    return res;
                tmp = stack.pop();
                res.add(tmp.val);
            }
            tmp = tmp.right;
        }
    }

    public static void recursionMidTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            recursionMidTraversal(root.left, res);
            res.add(root.val);
            recursionMidTraversal(root.right, res);
        }
    }

    public static List<Integer> postTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            res.add(treeNode.val);
            if (treeNode.left != null)
                stack.push(treeNode.left);
            if (treeNode.right != null)
                stack.push(treeNode.right);
        }
        for (int i = 0; i < res.size() / 2; i++) {
            int tmp = res.get(i);
            res.set(i, res.get(res.size() - i - 1));
            res.set(res.size() - i - 1, tmp);
        }
        return res;
    }

    public static void recursionPostTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            recursionPostTraversal(root.left, res);
            recursionPostTraversal(root.right, res);
            res.add(root.val);
        }
    }

    public static List<Integer> layerTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            res.add(treeNode.val);
            if (treeNode.left != null)
                queue.offer(treeNode.left);
            if (treeNode.right != null)
                queue.offer(treeNode.right);
        }
        return res;
    }

}
