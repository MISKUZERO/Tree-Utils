package util;

import struct.TreeNode;

import java.util.ArrayDeque;

public class TreeUtils {

    public static String treeAnalysis(TreeNode root) {
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        TreeNode block = new TreeNode(0);
        deque.add(block);
        StringBuilder result = new StringBuilder();
        TreeNode temp;
        while ((temp = deque.poll()) != null) {
            if (temp == block) {
                if (deque.isEmpty())
                    break;
                result.append("| ");
                deque.add(block);
                continue;
            }
            result.append(temp.val).append(" ");
            if (temp.left != null)
                deque.add(temp.left);
            if (temp.right != null)
                deque.add(temp.right);

        }
        return new String(result);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(6);
        t.add(1);
        t.add(3);
        t.add(-3);
        t.add(5);
        t.add(0);
        t.add(2);
        t.add(4);
        t.add(0);

        String s = TreeUtils.treeAnalysis(t);
        System.out.println(s);

    }
}
