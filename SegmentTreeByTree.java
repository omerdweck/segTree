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
     */
    @Override
    public void build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        this.root = buildHelper(arr, 0, arr.length - 1);
    }

    private SegmentTreeNode buildHelper(int[] arr, int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, arr[start], arr[start], start, null, null);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode leftChild = buildHelper(arr, start, mid);
        SegmentTreeNode rightChild = buildHelper(arr, mid + 1, end);
        int sum = leftChild.getSum() + rightChild.getSum();
        SegmentTreeNode node = new SegmentTreeNode(start, end, min(leftChild.getMin(), rightChild.getMin()), max(leftChild.getMax(), rightChild.getMax()), sum, leftChild, rightChild);
        return node;
    }

    private int max(int a, int b) {
        int result = a;
        if (b > a) {
            result = b;
        }
        return result;
    }

    private int min(int a, int b) {
        int result = a;
        if (b < a) {
            result = b;
        }
        return result;
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
        if (index < 0 || index >= ((this.root.getEnd() - this.root.getStart()) + 1 )) {
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


        node.setMin(min(leftChild.getMin(), rightChild.getMin()));
        node.setMax(max(leftChild.getMax(), rightChild.getMax()));
        node.setSum(leftChild.getSum() + rightChild.getSum());
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
        /*
         this function returns the size of the original array- the getStart() function
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
        if (node == null || left > node.getEnd() || right < node.getStart()) {
            return null;

        }

         if (left <= node.getStart() && right >= node.getEnd()) {
            return node;
        }
        int mid = left + (right - left) / 2;

        if(right <= mid) {
            return queryRangeHelper((SegmentTreeNode) node.getLeft(), left, right);
        }
        else if(left > mid) {
            return queryRangeHelper((SegmentTreeNode) node.getRight(), left, right);
        }

        else {
            SegmentTreeNode leftResult = queryRangeHelper((SegmentTreeNode) node.getLeft(), left, right);
            SegmentTreeNode rightResult = queryRangeHelper((SegmentTreeNode) node.getRight(), left, right);

            if (leftResult == null) {
                return rightResult;
            } else if (rightResult == null) {
                return leftResult;
            } else {
                int min = min(leftResult.getMin(), rightResult.getMin());
                int max = max(leftResult.getMax(), rightResult.getMax());
                int sum = leftResult.getSum() + rightResult.getSum();
                return new SegmentTreeNode(leftResult.getStart(), rightResult.getEnd(), min, max, sum, leftResult, rightResult);
            }
        }
    }

}
