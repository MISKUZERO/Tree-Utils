package util;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class TreeUtils {

    public static Object[] treeAnalysis(TreeNode root) {
        ArrayDeque<TreeNode> eleDeque = new ArrayDeque<>();
        eleDeque.add(root);
        ArrayList<Integer> valList = new ArrayList<>();
        int count = 1;
        TreeNode empty = new TreeNode();
        TreeNode block = new TreeNode();
        eleDeque.add(block);
        StringBuilder posBuilder = new StringBuilder();
        TreeNode ele;
        while (count != 0) {
            if ((ele = eleDeque.poll()) == block) {
                eleDeque.add(block);
                continue;
            }
            if (ele == empty)
                posBuilder.append(0);
            else {
                posBuilder.append(1);
                assert ele != null;
                valList.add(ele.val);
                count--;
            }
            if (ele.left == null)
                eleDeque.add(empty);
            else {
                eleDeque.add(ele.left);
                count++;
            }
            if (ele.right == null)
                eleDeque.add(empty);
            else {
                eleDeque.add(ele.right);
                count++;
            }
        }
        while (eleDeque.poll() != block) posBuilder.append(0);
        return new Object[]{posBuilder, valList};
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
        t.add(-5);
        t.add(10);

        Object[] res = TreeUtils.treeAnalysis(t);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }
}
