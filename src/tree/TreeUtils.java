package tree;

import java.util.*;

/**
 * @author MiskuZero
 */
public class TreeUtils {

    private static final char FILL_UNIT = ' ';
    private static final char NULL_UNIT = '.';

    @SuppressWarnings("all")
    public static StringBuilder resolving(BinaryTree.TreeNode node) {
        if (node == null)
            return new StringBuilder();
        StringBuilder result = new StringBuilder();
        Queue<BinaryTree.TreeNode> eleQueue = new LinkedList<>();
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
                BinaryTree.TreeNode temp = eleQueue.poll();
                int desPos = posQueue.poll();
                //NULL值打印
                while (curPos++ != desPos)
                    printNull(result, nextLine, edgeUnitCount - 1);
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
                boolean exUnit = false, reqUnit = false;
                if ((desPos & 1) == 0) {//目标位置为偶数则是右子树
                    if (halfEdgeUnitCount > 0) {
                        result.append("\b");
                        reqUnit = true;
                    }
                    result.append("\\");
                } else {
                    if (halfEdgeUnitCount > 0) {
                        result.append(FILL_UNIT);
                        exUnit = true;
                    }
                    result.append("/");
                }
                //打印数值
                String keyStr = String.valueOf(temp.key);
                int textLen = keyStr.length();
                int strLen = nextLine.length();
                int scale = edgeUnitCount << 1;
                int maxLen = scale;
                char preChar;
                while ((strLen - maxLen > 0) && (preChar = nextLine.charAt(strLen - maxLen)) == NULL_UNIT)
                    maxLen += scale;
                if (textLen < maxLen) {
                    for (int i = 1; i < textLen; i++) nextLine.append("\b");
                    nextLine.append(keyStr);
                } else if (textLen == maxLen) {
                    if (temp.key < 0 || desPos == 1) {
                        for (int i = 1; i < textLen; i++) nextLine.append("\b");
                        nextLine.append(keyStr);
                    } else
                        nextLine.append("*");
                } else
                    nextLine.append("*");
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
                //修正
                if (reqUnit)
                    result.append(FILL_UNIT);
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
                printNull(result, nextLine, edgeUnitCount - 1);
            //打印下一行
            result.append("\n").append(nextLine).append("\n");
            curLayerCapacity <<= 1;
        }
        return result;
    }


    @SuppressWarnings("all")
    public static StringBuilder resolving2(BinaryTree.TreeNode node) {
        if (node == null)
            return new StringBuilder();
        StringBuilder result = new StringBuilder();
        Queue<BinaryTree.TreeNode> eleQueue = new LinkedList<>();
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
                BinaryTree.TreeNode temp = eleQueue.poll();
                int desPos = posQueue.poll();
                //NULL值打印
                while (curPos++ != desPos)
                    printNull(result, nextLine, edgeUnitCount - 1);
                //正常值打印
                int halfEdgeUnitCount = edgeUnitCount >> 1;
                //打印左半部分
                for (int i = 0; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                for (int i = 1; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                //打印中央部分
                int exUnit = 0, reqUnit = 0;
                if ((desPos & 1) == 0) {//目标位置为偶数则是右子树
                    while (reqUnit < halfEdgeUnitCount) {
                        result.append("\b");
                        reqUnit++;
                    }
                    result.append("\\");
                } else {
                    while (exUnit < halfEdgeUnitCount) {
                        result.append(FILL_UNIT);
                        exUnit++;
                    }
                    result.append("/");
                }
                //打印数值
                String keyStr = String.valueOf(temp.key);
                int textLen = keyStr.length();
                int strLen = nextLine.length();
                int scale = edgeUnitCount << 1;
                int maxLen = scale;
                char preChar;
                while ((strLen - maxLen > 0) && (preChar = nextLine.charAt(strLen - maxLen)) == NULL_UNIT)
                    maxLen += scale;
                if (textLen < maxLen) {
                    for (int i = 1; i < textLen; i++) nextLine.append("\b");
                    nextLine.append(keyStr);
                } else if (textLen == maxLen) {
                    if (temp.key < 0 || desPos == 1) {
                        for (int i = 1; i < textLen; i++) nextLine.append("\b");
                        nextLine.append(keyStr);
                    } else
                        nextLine.append("*");
                } else
                    nextLine.append("*");
                //打印右半部分
                for (int i = 1; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                for (int i = 0; i < halfEdgeUnitCount; i++) {
                    result.append(FILL_UNIT);
                    nextLine.append(FILL_UNIT);
                }
                result.append(FILL_UNIT);
                nextLine.append(FILL_UNIT);
                //修正
                while (reqUnit-- > 0)
                    result.append(FILL_UNIT);
                while (exUnit-- > 0)
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
                printNull(result, nextLine, edgeUnitCount - 1);
            //换行调整树枝斜率
            if (edgeUnitCount > 4) {
                int count = edgeUnitCount >> 3;
                if (curLayerCapacity != 1)
                    for (int i = 0; i < count && i != 3; i++) {
                        result.append("\n");
                    }
                count >>= 1;
                if (edgeUnitCount >> 1 > 4)
                    for (int i = 0; i < count && i != 3; i++) {
                        nextLine.append("\n");
                    }
            }
            //删除起始行
            if (curLayerCapacity == 1)
                result = new StringBuilder();
            else
                result.append("\n");
            //打印下一行
            result.append(nextLine).append("\n");
            curLayerCapacity <<= 1;
        }
        return result;
    }

    private static void printNull(StringBuilder result, StringBuilder nextLine, int edgeUnitCount) {
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(FILL_UNIT);
            nextLine.append(FILL_UNIT);
        }
        result.append(FILL_UNIT);
        //NULL值位置
        nextLine.append(NULL_UNIT);
        for (int i = 0; i < edgeUnitCount; i++) {
            result.append(FILL_UNIT);
            nextLine.append(FILL_UNIT);
        }
        result.append(FILL_UNIT);
        nextLine.append(FILL_UNIT);
    }

    public static int getMaxDepth(BinaryTree.TreeNode node) {
        if (node == null)
            return 0;
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    private static int getMaxDepth0(BinaryTree.TreeNode node) {
        if (node == null)
            return 0;
        int left = getMaxDepth0(node.left);
        int right = left == -1 ? -1 : getMaxDepth0(node.right);//剪枝
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        else
            return Math.max(left, right) + 1;
    }

    public static boolean isAvlTree(BinaryTree.TreeNode node) {
        return getMaxDepth0(node) >= 0;
    }

    /*
    遍历方法
     */

    public static List<Integer> preTraversal(BinaryTree.TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTree.TreeNode treeNode = stack.pop();
            res.add(treeNode.key);
            if (treeNode.right != null)
                stack.push(treeNode.right);
            if (treeNode.left != null)
                stack.push(treeNode.left);
        }
        return res;
    }

    public static List<Integer> preTraversal2(BinaryTree.TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        BinaryTree.TreeNode tmp = node;
        while (true) {
            while (tmp != null) {
                stack.push(tmp);
                res.add(tmp.key);
                tmp = tmp.left;
            }
            if (stack.isEmpty())
                return res;
            tmp = stack.pop();
            tmp = tmp.right;
        }
    }

    public static void recursionPreTraversal(BinaryTree.TreeNode node, List<Integer> res) {
        if (node != null) {
            res.add(node.key);
            recursionPreTraversal(node.left, res);
            recursionPreTraversal(node.right, res);
        }
    }

    public static List<Integer> midTraversal(BinaryTree.TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        BinaryTree.TreeNode tmp = node;
        while (true) {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            if (stack.isEmpty())
                return res;
            tmp = stack.pop();
            res.add(tmp.key);
            tmp = tmp.right;
        }
    }

    public static void recursionMidTraversal(BinaryTree.TreeNode node, List<Integer> res) {
        if (node != null) {
            recursionMidTraversal(node.left, res);
            res.add(node.key);
            recursionMidTraversal(node.right, res);
        }
    }

    public static List<Integer> postTraversal(BinaryTree.TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTree.TreeNode treeNode = stack.pop();
            res.add(treeNode.key);
            if (treeNode.left != null)
                stack.push(treeNode.left);
            if (treeNode.right != null)
                stack.push(treeNode.right);
        }
        for (int i = 0; i < res.size() >> 1; i++) {
            int tmp = res.get(i);
            res.set(i, res.get(res.size() - i - 1));
            res.set(res.size() - i - 1, tmp);
        }
        return res;
    }

    public static void recursionPostTraversal(BinaryTree.TreeNode node, List<Integer> res) {
        if (node != null) {
            recursionPostTraversal(node.left, res);
            recursionPostTraversal(node.right, res);
            res.add(node.key);
        }
    }

    public static List<Integer> layerTraversal(BinaryTree.TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<BinaryTree.TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BinaryTree.TreeNode treeNode = queue.poll();
            res.add(treeNode.key);
            if (treeNode.left != null)
                queue.offer(treeNode.left);
            if (treeNode.right != null)
                queue.offer(treeNode.right);
        }
        return res;
    }

    public static void main(String[] args) {

        final int addCount = 50;
        BinarySearchTree bst;
        ArrayList<Integer> srcList;
        do {
            bst = new BinarySearchTree();
            srcList = new ArrayList<>();
            for (int i = 0; i < addCount; i++) {
                int num = ((int) (Math.random() * addCount) - (addCount >> 1)) >> 1;
                if (bst.add(num))
                    srcList.add(num);
            }
        } while (TreeUtils.getMaxDepth(bst.root) > 7);
        System.out.println("Tree Depth: " + TreeUtils.getMaxDepth(bst.root));
        System.out.println("Is Avl-Tree: " + TreeUtils.isAvlTree(bst.root));
        System.out.println(srcList);
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("-4: " + bst.del(-4));
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("0: " + bst.del(0));
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("-1: " + bst.del(-1));
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("3: " + bst.del(3));
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("2: " + bst.del(2));
        System.out.print(TreeUtils.resolving(bst.root));
        System.out.println("OK!");


    }

}
