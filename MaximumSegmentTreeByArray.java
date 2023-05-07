public class MaximumSegmentTreeByArray extends SegmentTreeByArray {
    /**
     * Constructor for initializing the segment tree with the given input array.
     *
     * @param arr the input array
     */
    public MaximumSegmentTreeByArray(int[] arr) {
        super(arr);
    }

    @Override
    public int calcValOfNode(int[] arr, int start, int end) {
        return max(arr, start, end);
    }

    public int max(int[] arr, int start, int end) {
        /**
         * @max is the helper method of calcValOfNode in this class. Using polymorphism, returns the maximum value
         * in the given array.
         * @param arr the array to find the maximum value in
         * @param start the starting index of the range to find the maximum value in
         * @param end the ending index of the range to find the maximum value in
         * @return the maximum value in the given array
         */
        int res = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    @Override
    protected void fixArrayAfterUpdate(int fromIndex) {
        /**
         * After update, the value of the node is changed, so we need to update the value of its parent node
         * until the root node. The value of the parent node is the maximum value of its two children, so we
         * compare the value of the two children and update the value of the parent node up to the root node.
         * @param fromIndex the index of the node whose value is changed
         */
        while (fromIndex > 0) {
            fromIndex = (fromIndex - 1) / 2;
            this.tree[fromIndex] = Math.max(this.tree[2 * fromIndex + 1], this.tree[2 * fromIndex + 2]);
        }
    }

    @Override
    public int queryRange(int left, int right) {
        return this.query(0, 0, this.tree.length - 1, left, right);
    }

    @Override
    protected int query(int node, int start, int end, int left, int right) {
        /**
         * Returns the result of the range query, which is the maximum value in the given range.
         * @param node the current node
         * @param start the starting index of the range of the current node
         * @param end the ending index of the range of the current node
         * @param left the starting index of the range of the query
         * @param right the ending index of the range of the query
         * @return the result of the range query, which is the maximum value in the given range
         */
        int[] arr = new int[right - left + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.tree[findTreeIndex(left + i)];
        }
        return max(arr, 0, arr.length - 1);
    }

}
