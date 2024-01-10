package util;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    private static final String FILL_UNIT = ".";

    public static StringBuilder resolving(TreeNode node) {
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> eleQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        eleQueue.offer(node);
        posQueue.offer(1);
        int size;
        int edgeUnitCount = 1 << getMaxDepth(node);
        int curLayerCapacity = 1;
        while ((size = eleQueue.size()) > 0) {
            StringBuilder nextLine = new StringBuilder();
            result.append(FILL_UNIT);
            nextLine.append(FILL_UNIT);
            edgeUnitCount >>= 1;
            int curPos = 1;
            while (size-- > 0) {
                TreeNode temp = eleQueue.poll();
                int desPos = posQueue.remove();
                //NULL值打印
                while (curPos++ != desPos)
                    printNULL(result, nextLine, edgeUnitCount - 1);
                //正常值打印
                int halfEdgeUnitCount = edgeUnitCount >> 1;
                //打印左半部分
                //填充
                for (int i = 0; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                //打印树枝
                if (temp.left == null)
                    for (int i = 1; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append(FILL_UNIT);
                    }
                else if (halfEdgeUnitCount > 2) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                    for (int i = 2; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append("_");
                    }
                } else
                    for (int i = 1; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append(FILL_UNIT);
                    }
                //打印中央部分
                boolean exUnit = false;
                if ((desPos & 1) == 0)//目标位置为偶数则是右子树
                    result.append("\\");
                else {
                    if (halfEdgeUnitCount > 0) {
                        result.append(FILL_UNIT);
                        exUnit = true;
                    }
                    result.append("/");
                }
                if (desPos > 9)
                    nextLine.append("@");
                else
                    nextLine.append(desPos);
                //打印右半部分
                //打印树枝
                if (temp.right == null)
                    for (int i = 1; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append(FILL_UNIT);
                    }
                else if (halfEdgeUnitCount > 2) {
                    for (int i = 2; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append("_");
                    }
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                } else
                    for (int i = 1; i < halfEdgeUnitCount; i++) {
                        result.append(FILL_UNIT);
                        nextLine.append(FILL_UNIT);
                    }
                //填充
                for (int i = 0; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                result.append(FILL_UNIT);
                nextLine.append(FILL_UNIT);
                if (exUnit)
                    result.append("\b");
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
                printNULL(result, nextLine, edgeUnitCount - 1);
            //打印下一行
            result.append("\n").append(nextLine).append("\n");
            curLayerCapacity <<= 1;
        }
        return result;
    }

    private static void printNULL(StringBuilder result, StringBuilder nextLine, int edgeUnitCount) {
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(FILL_UNIT);
            nextLine.append(FILL_UNIT);
        }
        result.append(FILL_UNIT);
        nextLine.append("+");//NULL值位置
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(FILL_UNIT);
            nextLine.append(FILL_UNIT);
        }
        result.append(FILL_UNIT);
        nextLine.append(FILL_UNIT);
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
