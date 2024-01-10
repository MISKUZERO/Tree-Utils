package util;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static StringBuilder resolving(TreeNode node) {
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> eleQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        eleQueue.offer(node);
        posQueue.offer(1);
        int size;
        int emptyCount = 1 << getMaxDepth(node);
        int curLayerCapacity = 1;
        String emptyUnit = " ";
        String branchUnit = "-";
        while ((size = eleQueue.size()) > 0) {
            StringBuilder nextLine = new StringBuilder();
            result.append("!");
            nextLine.append("!");
            emptyCount >>= 1;
            int curUnit = 1;
            while (size-- > 0) {
                TreeNode temp = eleQueue.poll();
                int pos = posQueue.remove();
                //NULL值打印
                while (curUnit++ != pos) {
                    for (int j = 1; j < emptyCount; j++) {
                        result.append(emptyUnit);
                        nextLine.append(emptyUnit);
                    }
                    result.append("x");
                    nextLine.append(emptyUnit);
                    for (int j = 1; j < emptyCount + 1; j++) {
                        result.append(emptyUnit);
                        nextLine.append(emptyUnit);
                    }
                }
                //正常打印
                for (int i = 1; i < emptyCount; i++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }
                if (pos > 9)
                    nextLine.append("@");
                else
                    nextLine.append(pos);
                result.append("|");
                for (int i = 1; i < emptyCount + 1; i++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }

                if (temp.left != null) {
                    eleQueue.offer(temp.left);
                    posQueue.offer((pos << 1) - 1);
                }
                if (temp.right != null) {
                    eleQueue.offer(temp.right);
                    posQueue.offer(pos << 1);
                }
            }
            //NULL值打印（补充）
            while (curUnit++ <= curLayerCapacity) {
                for (int j = 1; j < emptyCount; j++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }
                result.append("x");
                nextLine.append(emptyUnit);
                for (int j = 1; j < emptyCount + 1; j++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }
            }
            result.append("\n");
            //第二行打印
            result.append(nextLine).append("\n");
            curLayerCapacity <<= 1;
        }
        return result;
    }

    public static int getMaxDepth(TreeNode node) {
        if (node == null)
            return 0;
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    private static int getMaxDepth0(TreeNode node) {
        if (node == null)
            return 0;
        int left = getMaxDepth0(node.left);
        int right = left == -1 ? -1 : getMaxDepth0(node.right);//剪枝
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        else
            return Math.max(left, right) + 1;
    }

    public static boolean isAVLTree(TreeNode root) {
        return getMaxDepth0(root) >= 0;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode();
        System.out.print("[0, ");
        for (int i = 0; i < 20; i++) {
            int num = (int) (Math.random() * 20) - 10;
            if (t.add(num))
                System.out.print(num + ", ");
        }
        System.out.println("\b\b]");
        System.out.println("========================================================================================================================================");
        System.out.print(TreeUtils.resolving(t));
        System.out.println("========================================================================================================================================");
        System.out.println(TreeUtils.getMaxDepth(t));
        System.out.println(TreeUtils.isAVLTree(t));

    }
}
