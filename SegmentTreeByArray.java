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
        int temp = arr.length - 1;
        int number2 = arr.length - 1;
        int res = 1;
        while (temp > 1) {
            temp = temp / 2;
            res++;
        }
        size = 2 * (int) Math.pow(2, res) - 1;
        tree = new int[size];
        defaultArray(this.tree);
        buildHelper(arr, 0, number2, 0);
    }

    public void buildHelper(int[] arr, int start, int end, int index) {
        int curr;
        if (start == end) {
            this.tree[index] = arr[start];
            return;
        }
        if (this instanceof SummationSegmentTreeByArray) {
            curr = sum(arr, start, end);
        } else if (this instanceof MaximumSegmentTreeByArray) {
            curr = max(arr, start, end);
        } else {
            curr = min(arr, start, end);
        }

        int mid = (start + end) / 2;
        buildHelper(arr, start, mid, 2 * index + 1);
        buildHelper(arr, mid + 1, end, 2 * index + 2);
        this.tree[index] = curr;
    }

    public int sum(int[] arr, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res += arr[i];
        }
        return res;
    }

    public int max(int[] arr, int start, int end) {
        int res = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    public int min(int[] arr, int start, int end) {
        int res = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.min(res, arr[i]);
        }
        return res;
    }

    public void defaultArray(int[] tree) {
        int i = 0;
        for (int spot : tree) {
            tree[i] = Integer.MIN_VALUE;
            i++;
        }
    }



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
        //IMPLEMENT THE FUNCTION
        // You might want to add a helping functions for this.
    }


    /**
     * Queries the segment tree for a range of elements.
     *
     * @param left  the left index of the range
     * @param right the right index of the range
     * @return the result of the query operation
     */
    @Override
    public int queryRange(int left, int right) {
        //IMPLEMENT THE FUNCTION
        // use the query function for the implementation.
        return 0;
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
        for (int i = 0; i < this.tree.length; i++) {
            if (this.tree[i] == Integer.MIN_VALUE) {
                sb.append("-").append(" ");
                continue;
            }
            sb.append(this.tree[i]);
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
