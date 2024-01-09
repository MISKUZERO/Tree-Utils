package util;

import com.sun.istack.internal.NotNull;
import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class TreeUtils {

    @NotNull
    @SuppressWarnings("all")
    public static Object[] resolving(TreeNode node) {
        ArrayDeque<TreeNode> eleDeque = new ArrayDeque<>();
        eleDeque.add(node);
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
                posBuilder.append("\n");
                continue;
            }
            if (ele == empty)
                posBuilder.append(0);
            else {
                posBuilder.append(1);
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

    public static int getMaxDepth(TreeNode node) {
        if (node == null)
            return 0;
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    @NotNull
    public static boolean isAVLTree(TreeNode node) {
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(node);
        TreeNode temp;
        while ((temp = deque.poll()) != null) {
            TreeNode lNode = temp.left;
            TreeNode rNode = temp.right;
            if (Math.abs(TreeUtils.getMaxDepth(lNode) - TreeUtils.getMaxDepth(rNode)) > 1)
                return false;
            if (lNode != null)
                deque.add(lNode);
            if (rNode != null)
                deque.add(rNode);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode();
        System.out.print("[0, ");
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 10) - 5;
            if (t.add(num))
                System.out.print(num + ", ");
        }
        System.out.println("\b\b]");

        Object[] res = TreeUtils.resolving(t);
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(TreeUtils.getMaxDepth(t));
        System.out.println(TreeUtils.isAVLTree(t));

    }
}
