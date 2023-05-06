/**
 * This abstract class represents a segment tree implementation using an array
 * and provides methods to build, update, and query the tree.
 */
public abstract class SegmentTreeByArray implements SegmentTree {

    protected int[] tree;
    protected int size;

    /**
     * Constructor for initializing the segment tree with the given input array.
     *
     * @param arr the input array
     */
    public SegmentTreeByArray(int[] arr) {
        build(arr);
    }

    /**
     * Builds the segment tree from the input array.
     *
     * @param arr the input array
     */
    @Override
    public void build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int arrayLength = arr.length - 1;
        this.size = arr.length;
        size = 2 * (int) Math.pow(2, Math.ceil(Math.log(arrayLength) / Math.log(2))) - 1;
        tree = new int[size];
        defaultArray(this.tree);
        buildHelper(arr, 0, arrayLength, 0);
    }

    public void buildHelper(int[] arr, int start, int end, int index) {
        int curr;
        if (start == end) {
            this.tree[index] = arr[start];
            return;
        }
        curr = calcValOfNode(arr, start, end);
        int mid = (start + end) / 2;
        buildHelper(arr, start, mid, 2 * index + 1);
        buildHelper(arr, mid + 1, end, 2 * index + 2);
        this.tree[index] = curr;
    }


    public void defaultArray(int[] tree) {
        int i = 0;
        for (int spot_iter : tree) {
            tree[i] = Integer.MIN_VALUE;
            i++;
        }
    }

    public abstract int calcValOfNode(int[] arr, int start, int end);



    // You might want to add a helping functions for this.
    // You can create the construction in a "generic" way by using functions that will give the correct value,
    //and complete these functions in subclasses


    //IMPLEMENT THE FUNCTION
    // You might want to add a helping functions for this.
    // You can create the construction in a "generic" way by using functions that will give the correct value,
    //and complete these functions in subclasses


    /**
     * Updates the value at the specified index and updates the segment tree accordingly.
     *
     * @param index the index of the element to update in the array
     * @param value the new value to replace the existing value
     */
    @Override
    public void update(int index, int value) {
        this.updateHelper(0, size - 1, findTreeIndex(index), 0, index, value);
    }

    private void updateHelper(int start, int end, int treeIndex, int treeLevel, int index, int value) {
        if (start == end) {
            this.tree[treeIndex] = value;
            fixArrayAfterUpdate(treeIndex);
            return;
        }
        int mid = start + (end - start) / 2;
        if (treeIndex <= mid) {
            updateHelper(start, mid, treeIndex, treeLevel + 1, index, value);
        } else {
            updateHelper(mid + 1, end, treeIndex, treeLevel + 1, index, value);
        }
    }

    protected int findTreeIndex(int index) {
        int curr = 0;
        int counter = -1;
        boolean[] visitedLeft = new boolean[size()];
        boolean[] visitedRight = new boolean[size()];
        while (counter != index && curr <= size) {
            if (curr >= size - size / 2 - 1 && this.tree[curr] != Integer.MIN_VALUE || this.tree[2 * curr + 1] == Integer.MIN_VALUE && this.tree[2 * curr + 2] == Integer.MIN_VALUE) {
                counter++;
                visitedLeft[curr] = true;
                visitedRight[curr] = true;
                if (counter == index) {
                    return curr;
                }
            }
            if (curr <= size - size / 2 - 1 && !visitedLeft[curr]) {
                visitedLeft[curr] = true;
                curr = 2 * curr + 1;
            } else if (visitedLeft[curr] && !visitedRight[curr]) {
                visitedRight[curr] = true;
                curr = 2 * curr + 2;
            } else {
                if (curr % 2 == 0) {
                    curr = curr / 2 - 1;
                } else {
                    curr = curr / 2;
                }
            }
        }
        return curr; // won't get here because of the assigment guidelines.
    }

    protected abstract void fixArrayAfterUpdate(int fromIndex);

    /**
     * Queries the segment tree for a range of elements.
     *
     * @param left  the left index of the range
     * @param right the right index of the range
     * @return the result of the query operation
     */
    @Override
    public int queryRange(int left, int right) {
        return query(0, 0, size - 1, left, right);
    }

    /**
     * Abstract method for query operation, to be implemented by subclasses.
     *
     * @param node  the current node
     * @param start the start index
     * @param end   the end index
     * @param left  the left index
     * @param right the right index
     * @return the result of the query operation
     */
    protected abstract int query(int node, int start, int end, int left, int right);

    /**
     * The members inside the array representing the segment tree are printed according to their indexes in the array.
     * When the members are surrounded by "[ ]" and exactly one space between each number and between the brackets.
     * For example, for the tree [10,4,6,1,3,2,4] " [ 10 4 6 1 3 2 4 ] " will be returned
     */
    @Override
    public String toString() {
        //IMPLEMENT THE FUNCTION
        StringBuilder sb = new StringBuilder();
        sb.append(" [ ");
        for (int nodeVal : this.tree) {
            if (nodeVal == Integer.MIN_VALUE) {
                sb.append("-").append(" ");
                continue;
            }
            sb.append(nodeVal);
            sb.append(" ");
        }
        sb.append("] ");
        return sb.toString();
    }

    /**
     * Returns the number of elements in the original array that the segment tree was built from.
     *
     * @return the size of the original array
     */
    @Override
    public int size() {
        //IMPLEMENT THE FUNCTION
        return this.size;
    }
}
