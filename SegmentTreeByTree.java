import java.util.*;

/**
 * An abstract base class for a segment tree data structure implemented using a tree structure.
 * Subclasses must implement the {@code queryRange} method to provide specific range query functionality.
 */
public abstract class SegmentTreeByTree implements SegmentTree {

    protected SegmentTreeNode root;
    protected int size;

    /**
     * Constructor for creating a Segment Tree from an input array
     *
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public SegmentTreeByTree(int[] arr) {
        build(arr);
    }

    /**
     * Builds the segment tree from the given array of integers.
     *
     * @param arr the array of integers to build the segment tree from
     *
     */
    @Override
    public void build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        this.root = buildHelper(arr, 0, arr.length - 1);
    }

    private SegmentTreeNode buildHelper(int[] arr, int start, int end) {
        // what does this function do? - it builds the tree, it is a recursive function that builds the tree from the bottom up by calling itself on the left and right half of the array and then combining the results into a node
        //
        if (start == end) {
            return new SegmentTreeNode(start, end, arr[start], arr[start], arr[start], null, null);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode leftChild = buildHelper(arr, start, mid);
        SegmentTreeNode rightChild = buildHelper(arr, mid + 1, end);
        int sum = sum(leftChild, rightChild, arr[start]);
        int max = max(leftChild, rightChild, arr[start]);
        int min = min(leftChild, rightChild, arr[start]);
        SegmentTreeNode node = new SegmentTreeNode(start, end, min, max, sum, leftChild, rightChild);
        return node;
    }

    private int max(SegmentTreeNode leftChild, SegmentTreeNode rightChild, int self) {
        if (leftChild == null && rightChild == null) {
            return self;
        } else if (leftChild == null) {
            return rightChild.getMax();
        } else if (rightChild == null) {
            return leftChild.getMax();
        }
        if (leftChild.getMax() > rightChild.getMax()) {
            return leftChild.getMax();
        } else {
            return rightChild.getMax();
        }
    }
    private int sum(SegmentTreeNode leftChild, SegmentTreeNode rightChild, int self) {
        if (leftChild == null && rightChild == null) {
            return self;
        } else if (leftChild == null) {
            return rightChild.getSum();
        } else if (rightChild == null) {
            return leftChild.getSum();
        }
        return leftChild.getSum() + rightChild.getSum();
    }

    private int min(SegmentTreeNode leftChild, SegmentTreeNode rightChild, int self) {
        if (leftChild == null && rightChild == null) {
            return 0;
        } else if (leftChild == null) {
            return rightChild.getMin();
        } else if (rightChild == null) {
            return leftChild.getMin();
        }
        if (leftChild.getMin() < rightChild.getMin()) {
            return leftChild.getMin();
        } else {
            return rightChild.getMin();
        }
    }


    /**
     * Updates the element at the specified index in the original array and updates the segment tree accordingly.
     *
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    @Override
    public void update(int index, int value) {
        //TODO need to use size insted of the long () but doents work ----- SIZE FUNCTION DOESNT WORK
        if (index < 0 || index >= (size())) {
            throw new IllegalArgumentException("Invalid index");
        }
        updateHelper(root, index, value);
    }

    public void updateHelper(SegmentTreeNode node, int index, int value) {
        if (node == null) {
            return;
        }
        if (node.getStart() == node.getEnd() && node.getStart() == index) {
            node.setMin(value);
            node.setMax(value);
            node.setSum(value);
            return;
        }
        int mid = node.getStart() + (node.getEnd() - node.getStart()) / 2;
        if (index <= mid) {
            updateHelper((SegmentTreeNode) node.getLeft(), index, value); //Todo  also here i have to cast the node to SegmentTreeNode --> check
        } else {
            updateHelper((SegmentTreeNode) node.getRight(), index, value);
        }

        /*
        not sure about that need to check if there is a better way
         */
        SegmentTreeNode leftChild = (SegmentTreeNode) node.getLeft(); //TODO need to check
        SegmentTreeNode rightChild = (SegmentTreeNode) node.getRight();


        node.setMin(min(leftChild, rightChild, value));
        node.setMax(max(leftChild, rightChild, value));
        node.setSum(sum(leftChild, rightChild, value));
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
        /*
         this function returns the size of the original array - the getStart() function
         return the first index of the array and getEnd() function returns the last index of the array.
         */
        if (this.root == null) {
            return 0;
        }
        return this.size = (this.root.getEnd() - this.root.getStart()) + 1;

    }

    /**
     * Queries the Segment Tree for the minimum value in the given range. to be implemented by subclasses.
     *
     * @param left  Start index of the query range
     * @param right End index of the query range
     * @return Minimum value in the given range
     */
    @Override
    public abstract int queryRange(int left, int right);

    /**
     * Helper method for querying the Segment Tree
     *
     * @param node  Current node of the Segment Tree
     * @param left  Start index of the query range
     * @param right End index of the query range
     * @return A SegmentTreeNode that contains the minimum, maximum and sum values for the given range
     */
    protected SegmentTreeNode queryRangeHelper(SegmentTreeNode node, int left, int right) {
//        if (node == null || left > node.getEnd() || right < node.getStart()) {
//            return null;
//
//        }

        if (left <= node.getStart() && right >= node.getEnd()) {
            return node;
        }
        int mid = node.getStart() + (node.getEnd() - node.getStart()) / 2;

        if (right <= mid) {
            return queryRangeHelper((SegmentTreeNode) node.getLeft(), left, right);
        } else if (left > mid) {
            return queryRangeHelper((SegmentTreeNode) node.getRight(), left, right);
        } else {
            SegmentTreeNode leftResult = queryRangeHelper((SegmentTreeNode) node.getLeft(), left, mid);
            SegmentTreeNode rightResult = queryRangeHelper((SegmentTreeNode) node.getRight(), mid + 1, right);

            if (leftResult == null) {
                return rightResult;
            } else if (rightResult == null) {
                return leftResult;
            } else {
                int min = min(leftResult, rightResult, node.getMin());
                int max = max(leftResult, rightResult, node.getMax());
                int sum = leftResult.getSum() + rightResult.getSum();
                return new SegmentTreeNode(leftResult.getStart(), rightResult.getEnd(), min, max, sum, leftResult, rightResult);
            }
        }
    }
//    @Override
//    public String toString() {
//        if (root == null) {
//            return "Empty tree";
//        }
//
//        StringBuilder sb = new StringBuilder();
//        Queue<SegmentTreeNode> nodes = new LinkedList<>();
//        Queue<Integer> levels = new LinkedList<>();
//        int maxLevel = 0;
//        nodes.offer(root);
//        levels.offer(0);
//
//        while (!nodes.isEmpty()) {
//            SegmentTreeNode node = nodes.poll();
//            int level = levels.poll();
//
//            if (level > maxLevel) {
//                sb.append('\n');
//                maxLevel = level;
//            }
//
//            int leftPad = (int) Math.pow(2, maxLevel - level) - 1;
//            int rightPad = (int) Math.pow(2, maxLevel - level + 1) - 1;
//            sb.append(" ".repeat(leftPad));
//            sb.append(node.toString());
//            sb.append(" ".repeat(rightPad));
//
//            if (node.getLeft() != null) {
//                nodes.offer((SegmentTreeNode) node.getLeft());
//                levels.offer(level + 1);
//            }
//
//            if (node.getRight() != null) {
//                nodes.offer((SegmentTreeNode) node.getRight());
//                levels.offer(level + 1);
//            }
//        }
//
//        return sb.toString();
//    }
//@Override
//public String toString() {
//    if (root == null) {
//        return "Empty tree";
//    }
//    StringBuilder sb = new StringBuilder();
//    int height = getHeight(root);
//    int level = 1;
//    List<SegmentTreeNode> currLevel = new ArrayList<>();
//    currLevel.add(root);
//    while (!currLevel.isEmpty()) {
//        int numNodes = currLevel.size();
//        int numSpaces = (int) (Math.pow(2, height - level) - 1);
//        for (int i = 0; i < numNodes; i++) {
//            SegmentTreeNode node = currLevel.remove(0);
//            if (node == null) {
//                sb.append(" ".repeat(numSpaces + 1));
//            } else {
//                sb.append(" ".repeat(numSpaces));
//                sb.append(node.getMin()).append("-").append(node.getMax());
//                sb.append(" ".repeat(numSpaces));
//                currLevel.add((SegmentTreeNode) node.getLeft());
//                currLevel.add((SegmentTreeNode) node.getRight());
//            }
//            sb.append(" ".repeat(numSpaces + 1));
//        }
//        sb.append("\n");
//        level++;
//    }
//    return sb.toString();
//}
//
//    private int getHeight(SegmentTreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        int leftHeight = getHeight((SegmentTreeNode) node.getLeft());
//        int rightHeight = getHeight((SegmentTreeNode) node.getRight());
//        return 1 + Math.max(leftHeight, rightHeight);
//    }
//    @Override
//    public String toString() {
//        if (root == null) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        buildVerticalRepresentation(sb, "", root, false);
//        return sb.toString();
//    }
//
//    private void buildVerticalRepresentation(StringBuilder sb, String prefix, SegmentTreeNode node, boolean isLeftChild) {
//        if (node == null) {
//            return;
//        }
//        sb.append(prefix);
//        sb.append(isLeftChild ? "├──" : "└──");
//        sb.append("[").append(node.getStart()).append(",").append(node.getEnd()).append("] (min=").append(node.getMin()).append(", max=").append(node.getMax()).append(", sum=").append(node.getSum()).append(")").append("\n");
//        if (node.getLeft() != null || node.getRight() != null) {
//            buildVerticalRepresentation(sb, prefix + (isLeftChild ? "│   " : "    "), (SegmentTreeNode) node.getLeft(), true);
//            buildVerticalRepresentation(sb, prefix + (isLeftChild ? "│   " : "    "), (SegmentTreeNode) node.getRight(), false);
//        }
//    }
//}
//    @Override
//    public String toString() {
//        if (root == null) {
//            return "Empty Segment Tree";
//        }
//
//        StringBuilder sb = new StringBuilder();
//        Queue<SegmentTreeNode> q = new LinkedList<>();
//        q.offer(root);
//
//        while (!q.isEmpty()) {
//            int levelSize = q.size();
//            for (int i = 0; i < levelSize; i++) {
//                SegmentTreeNode node = q.poll();
//                sb.append(node.getStart()).append("-").append(node.getEnd()).append(" (").append(node.getMin()).append(",").append(node.getMax()).append(",").append(node.getSum()).append(") ");
//                if (node.getLeft() != null) {
//                    q.offer((SegmentTreeNode) node.getLeft());
//                }
//                if (node.getRight() != null) {
//                    q.offer((SegmentTreeNode) node.getRight());
//                }
//            }
//            sb.append("\n");
//        }
//
//        return sb.toString();
//    }
//}
}