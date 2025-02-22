package tree;

import java.util.*;

/**
 * @author MiskuZero
 */
public class TreeUtils {

    private static final char FILL_UNIT = ' ';
    private static final char NULL_UNIT = '.';

    @SuppressWarnings("all")
    public static StringBuilder resolving(BinaryTree.Node node) {
        if (node == null)
            return new StringBuilder();
        StringBuilder result = new StringBuilder();
        Queue<BinaryTree.Node> eleQueue = new LinkedList<>();
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
                BinaryTree.Node temp = eleQueue.poll();
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
    public static StringBuilder resolvingAvlTree(BinaryTree.Node node) {
        if (node == null)
            return new StringBuilder();
        StringBuilder result = new StringBuilder();
        Queue<BinaryTree.Node> eleQueue = new LinkedList<>();
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
                BinaryTree.Node temp = eleQueue.poll();
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
                String keyStr = String.valueOf(temp.key) + "," + ((AvlTree.AvlNode) temp).height;
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

    public static int getMaxDepth(BinaryTree.Node node) {
        if (node == null)
            return 0;
        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    private static int getMaxDepth0(BinaryTree.Node node) {
        if (node == null)
            return 0;
        int left = getMaxDepth0(node.left);
        int right = left == -1 ? -1 : getMaxDepth0(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        else
            return Math.max(left, right) + 1;
    }

    public static boolean isAvlTree(BinaryTree.Node node) {
        return getMaxDepth0(node) >= 0;
    }

    /*
    遍历方法
     */

    public static List<Integer> preTraversal(BinaryTree.Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTree.Node cur = stack.pop();
            res.add(cur.key);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
        return res;
    }

    public static List<Integer> preTraversal2(BinaryTree.Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.Node> stack = new Stack<>();
        BinaryTree.Node tmp = node;
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

    public static void recursionPreTraversal(BinaryTree.Node node, List<Integer> res) {
        if (node != null) {
            res.add(node.key);
            recursionPreTraversal(node.left, res);
            recursionPreTraversal(node.right, res);
        }
    }

    public static List<Integer> midTraversal(BinaryTree.Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.Node> stack = new Stack<>();
        BinaryTree.Node tmp = node;
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

    public static void recursionMidTraversal(BinaryTree.Node node, List<Integer> res) {
        if (node != null) {
            recursionMidTraversal(node.left, res);
            res.add(node.key);
            recursionMidTraversal(node.right, res);
        }
    }

    public static List<Integer> postTraversal(BinaryTree.Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<BinaryTree.Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTree.Node cur = stack.pop();
            res.add(cur.key);
            if (cur.left != null)
                stack.push(cur.left);
            if (cur.right != null)
                stack.push(cur.right);
        }
        for (int i = 0; i < res.size() >> 1; i++) {
            int tmp = res.get(i);
            res.set(i, res.get(res.size() - i - 1));
            res.set(res.size() - i - 1, tmp);
        }
        return res;
    }

    public static void recursionPostTraversal(BinaryTree.Node node, List<Integer> res) {
        if (node != null) {
            recursionPostTraversal(node.left, res);
            recursionPostTraversal(node.right, res);
            res.add(node.key);
        }
    }

    public static List<Integer> layerTraversal(BinaryTree.Node node) {
        ArrayList<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<BinaryTree.Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BinaryTree.Node cur = queue.poll();
            res.add(cur.key);
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
        return res;
    }

    public static void main(String[] args) {

        /*
        随机添加测试用例：
        AvlTree avlTree = new AvlTree();
        final int addCount = 30;
        ArrayList<Integer> srcList = new ArrayList<>();
        do {
            for (int i = 0; i < addCount; i++) {
                int num = ((int) (Math.random() * addCount) - (addCount >> 1)) >> 1;
                if (avlTree.add(num)) {
                    System.out.print(TreeUtils.resolving(avlTree.root));
                    srcList.add(num);
                    System.out.println("------(" + srcList.size() + ").[" + num + "]-----------------------------------------------------------------------");
                }
            }
        } while (TreeUtils.getMaxDepth(avlTree.root) > 7);
        System.out.println("Tree Depth: " + TreeUtils.getMaxDepth(avlTree.root));
        System.out.println("Is Avl-Tree: " + TreeUtils.isAvlTree(avlTree.root));
        System.out.println(srcList);
        System.out.println(TreeUtils.midTraversal(avlTree.root));
         */

        /*
        AVL树添加节点方法测试用例：
        修改前失败：
        [-8, 6, 0, -5, -7, 5, 2, -6, -4, 4, 1, -3, 3]
        [-4, -3, 4, -2, 6, -1, 1, -5, 5, 3, 0, -6, 2, -8, 7]
        [0, 1, -8, -4, 4, 3, -3, 2, 6, -6, -2, 7, -1, -7, -5]
        [-6, -3, -4, 2, -2, 5, 1, -5, -8, 6, 0, 3, 4, -7]
        [4, -2, -8, 5, 3, -7, 6, 2, -3, -1, 0, -6, -5]
        修改前成功：
        [4, 3, -4, 1, -7, -5, 0, -2, 2, 5, -3, -6, 7]
        [-7, 1, 6, -5, -1, -4, 7, -2, 4, 0, 5, -3, -6]
        有序：
        [-7, -6, -5, -4, -3, -2, 0, 1, 2, 3, 4, 5, 7]
        [-7, -6, -5, -4, -3, -2, -1, 0, 1, 4, 5, 6, 7]
         */
        int[][] samples = {
                {-8, 6, 0, -5, -7, 5, 2, -6, -4, 4, 1, -3, 3},
                {-4, -3, 4, -2, 6, -1, 1, -5, 5, 3, 0, -6, 2, -8, 7},
                {0, 1, -8, -4, 4, 3, -3, 2, 6, -6, -2, 7, -1, -7, -5},
                {-6, -3, -4, 2, -2, 5, 1, -5, -8, 6, 0, 3, 4, -7},
                {4, -2, -8, 5, 3, -7, 6, 2, -3, -1, 0, -6, -5},
                {4, 3, -4, 1, -7, -5, 0, -2, 2, 5, -3, -6, 7},
                {-7, 1, 6, -5, -1, -4, 7, -2, 4, 0, 5, -3, -6},
                {-7, -6, -5, -4, -3, -2, 0, 1, 2, 3, 4, 5, 7},
                {-7, -6, -5, -4, -3, -2, -1, 0, 1, 4, 5, 6, 7}
        };
        AvlTree avlTree;
        for (int[] sample : samples) {
            avlTree = new AvlTree();
            for (int i : sample) {
                avlTree.add(i);
            }
            System.out.println("Is Avl-Tree: " + TreeUtils.isAvlTree(avlTree.root));
            System.out.println(TreeUtils.resolving(avlTree.root));
        }

        avlTree = new AvlTree();
        for (int i = 0; i < samples[8].length; i++) {
            avlTree.add(samples[8][i]);
            System.out.print(TreeUtils.resolving(avlTree.root));
            System.out.println("------(" + avlTree.size() + ").[" + samples[8][i] + "]-----------------------------------------------------------------------");
        }
        System.out.println("Is Avl-Tree: " + TreeUtils.isAvlTree(avlTree.root));

    }

}
