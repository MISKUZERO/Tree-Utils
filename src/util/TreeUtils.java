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
        int edgeUnitCount = 1 << (getMaxDepth(node) - 1);
        int curLayerCapacity = 1;
        String emptyUnit = " ";
        String branchUnit = "_";
        while ((size = eleQueue.size()) > 0) {
            StringBuilder nextLine = new StringBuilder();
            result.append(emptyUnit);
            nextLine.append(emptyUnit);
            edgeUnitCount >>= 1;
            int curPos = 1;
            while (size-- > 0) {
                TreeNode temp = eleQueue.poll();
                int desPos = posQueue.remove();
                //NULL值打印
                while (curPos++ != desPos)
                    printNULL(result, (edgeUnitCount << 1) - 1, nextLine);
                //正常值打印
                for (int i = 0; i < edgeUnitCount; i++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }
                if (temp.left == null)
                    for (int i = 1; i < edgeUnitCount; i++) {
                        result.append(emptyUnit);
                        nextLine.append(emptyUnit);
                    }
                else
                    for (int i = 1; i < edgeUnitCount; i++) {
                        result.append(emptyUnit);
                        nextLine.append(branchUnit);
                    }
                if (desPos > 9)
                    nextLine.append("@");
                else
                    nextLine.append(desPos);
                result.append("|");
                if (temp.right == null)
                    for (int i = 1; i < edgeUnitCount; i++) {
                        result.append(emptyUnit);
                        nextLine.append(emptyUnit);
                    }
                else
                    for (int i = 1; i < edgeUnitCount; i++) {
                        result.append(emptyUnit);
                        nextLine.append(branchUnit);
                    }
                for (int i = 0; i < edgeUnitCount; i++) {
                    result.append(emptyUnit);
                    nextLine.append(emptyUnit);
                }
                result.append(emptyUnit);
                nextLine.append(emptyUnit);
                //添加下一层节点
                if (temp.left != null) {
                    eleQueue.offer(temp.left);
                    posQueue.offer((desPos << 1) - 1);
                }
                if (temp.right != null) {
                    eleQueue.offer(temp.right);
                    posQueue.offer(desPos << 1);
                }
            }
            //NULL值打印（补充）
            while (curPos++ <= curLayerCapacity)
                printNULL(result, (edgeUnitCount << 1) - 1, nextLine);
            //打印下一行
            result.append("\n").append(nextLine).append("\n");
            curLayerCapacity <<= 1;
        }
        return result;
    }

    private static void printNULL(StringBuilder result, int edgeUnitCount, StringBuilder nextLine) {
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(" ");
            nextLine.append(" ");
        }
        result.append(" ");
        nextLine.append(".");//NULL值位置
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(" ");
            nextLine.append(" ");
        }
        result.append(" ");
        nextLine.append(" ");
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
