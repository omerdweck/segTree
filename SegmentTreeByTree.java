
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
        /**
         * If the input array is null or empty, return. else, call the buildHelper method to build the tree.
         * @param arr the array of integers to build the segment tree from.
         */
        if (arr == null || arr.length == 0) {
            return;
        }
        this.root = buildHelper(arr, 0, arr.length - 1);
    }

    private SegmentTreeNode buildHelper(int[] arr, int start, int end) {
        /**
         * @BuildHelper is the helper of build that builds the tree recursively. It takes in the array, start index
         * and end index as parameters. It returns the root of the tree. If the start index is equal to the end index,
         * then the node is a leaf node and the node's min, max, and sum are all equal to the value of the array at
         * that index. If the start index is not equal to the end index, then the node is not a leaf node and the
         * node's min, max, and sum are calculated by calling the min, max, and sum methods. The left child of the
         * node is the result of calling buildHelper on the left half of the array and the right child of the node is
         * the result of calling buildHelper on the right half of the array. The node is then returned.
         * @param arr the array of integers to build the segment tree from.
         * @param start the start index of the array.
         * @param end the end index of the array.
         * @return the root of the tree.
         */
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
        /**
         * @max is a helper method that returns the max of the left child, right child, and the value of the array
         * at the index of the node.
         * @param leftChild the left child of the node.
         * @param rightChild the right child of the node.
         * @param self the value of the array at the index of the node.
         * @return the max of the left child, right child, and the value of the array at the index of the node.
         */
        if (leftChild == null && rightChild == null) {
            return self;
        } else if (leftChild == null) {
            return rightChild.getMax();
        } else if (rightChild == null) {
            return leftChild.getMax();
        }
        return Math.max(leftChild.getMax(), rightChild.getMax());
    }
    private int sum(SegmentTreeNode leftChild, SegmentTreeNode rightChild, int self) {
        /**
         * @sum is a helper method that returns the sum of the left child, right child, and the value of the array
         * at the index of the node.
         * @param leftChild the left child of the node.
         * @param rightChild the right child of the node.
         * @param self the value of the array at the index of the node.
         * @return the sum of the left child, right child, and the value of the array at the index of the node.
         */
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
        /**
         * @min is a helper method that returns the min of the left child, right child, and the value of the array
         * at the index of the node.
         * @param leftChild the left child of the node.
         * @param rightChild the right child of the node.
         * @param self the value of the array at the index of the node.
         * @return the min of the left child, right child, and the value of the array at the index of the node.
         */
        if (leftChild == null && rightChild == null) {
            return self;
        } else if (leftChild == null) {
            return rightChild.getMin();
        } else if (rightChild == null) {
            return leftChild.getMin();
        }
        return Math.min(leftChild.getMin(), rightChild.getMin());
    }


    /**
     * Updates the element at the specified index in the original array and updates the segment tree accordingly.
     *
     * @param index the index of the element to update
     * @param value the new value of the element at the specified index
     */
    @Override
    public void update(int index, int value) {
        /**
         * @update is a method that updates the element at the specified index in the original array and updates the
         * segment tree accordingly. If the index is less than 0 or greater than or equal to the size of the array,
         * then an IllegalArgumentException is thrown. Otherwise, the updateHelper method is called with the root of
         * the tree, the index, and the value as parameters.
         * @param index the index of the element to update
         * @param value the new value of the element at the specified index
         */
        if (index < 0 || index >= (size())) {
            throw new IllegalArgumentException("Invalid index");
        }
        updateHelper(root, index, value);
    }

    public void updateHelper(SegmentTreeNode node, int index, int value) {
        /**
         * @updateHelper is a helper method that updates the element at the specified index in the original array and
         * updates the segment tree accordingly. If the node is null, then nothing happens. If the start index and end
         * index of the node are equal to the index, then the node's min, max, and sum are all equal to the value of
         * the array at that index. Otherwise, the updateHelper method is called with the left child of the node, the
         * index, and the value as parameters if the index is less than or equal to the middle index of the node. If
         * the index is greater than the middle index of the node, then the updateHelper method is called with the
         * right child of the node, the index, and the value as parameters. The left child and right child of the node
         * are then set to the left child and right child of the node. The node's min, max, and sum are then set to the
         * min, max, and sum of the left child and right child of the node.
         * @param node the node to update.
         * @param index the index of the element to update
         * @param value the new value of the element at the specified index
         */
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
            updateHelper((SegmentTreeNode) node.getLeft(), index, value);
        } else {
            updateHelper((SegmentTreeNode) node.getRight(), index, value);
        }

        SegmentTreeNode leftChild = (SegmentTreeNode) node.getLeft();
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
        /**
         * @queryRangeHelper is a helper method for querying the Segment Tree. If the node is null, then null is
         * returned. If the left index and right index are equal to the start index and end index of the node,
         * respectively, then the node is returned. Otherwise, the @queryRangeHelper method is called with the left
         * child of the node, the left index, and the right index as parameters if the right index is less than or
         * equal to the middle index of the node. If the left index is greater than the middle index of the node, then
         * the @queryRangeHelper method is called with the right child of the node, the left index, and the right index
         * as parameters. Otherwise, the @queryRangeHelper method is called with the left child of the node, the left
         * index, and the middle index of the node as parameters and the @queryRangeHelper method is called with the
         * right child of the node, the middle index of the node plus one, and the right index as parameters. The left
         * child and right child of the node are then set to the left child and right child of the node. The node's
         * min, max, and sum are then set to the min, max, and sum of the left child and right child of the node.
         * @param node the node to query.
         * @param left  Start index of the query range
         * @param right End index of the query range
         * @return A SegmentTreeNode that contains the minimum, maximum and sum values for the given range
         */
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

}