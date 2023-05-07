public class SummationSegmentTreeByArray extends SegmentTreeByArray {
    /**
     * Constructor for initializing the segment tree with the given input array.
     *
     * @param arr the input array
     */
    public SummationSegmentTreeByArray(int[] arr) {
        super(arr);
    }

    @Override
    public int calcValOfNode(int[] arr, int start, int end) {
        return sum(arr, start, end);
    }

    public int sum(int[] arr, int start, int end) {
        /**
         * @sum is the helper method of calcValOfNode in this class. Using polymorphism, returns the sum of
         * the given array.
         * @param arr the array to find the sum of
         * @param start the starting index of the range to find the sum of
         * @param end the ending index of the range to find the sum of
         * @return the sum of the given array
         */
        int res = 0;
        for (int i = start; i <= end; i++) {
            res += arr[i];
        }
        return res;
    }

    @Override
    protected void fixArrayAfterUpdate(int fromIndex) {
        /**
         * After update, the value of the node is changed, so we need to update the value of its parent node
         * until the root node. The value of the parent node is the sum of its two children, so we
         * add the value of the two children and update the value of the parent node up to the root node.
         * @param fromIndex the index of the node whose value is changed
         */
        while (fromIndex > 0) {
            fromIndex = (fromIndex - 1) / 2;
            this.tree[fromIndex] = this.tree[2 * fromIndex + 1] + this.tree[2 * fromIndex + 2];
        }
    }

    @Override
    public int queryRange(int left, int right) {
        return this.query(0, 0, this.tree.length - 1, left, right);
    }

    @Override
    protected int query(int node, int start, int end, int left, int right) {
        /**
         * @query is the helper method of queryRange. Returns the result of the range query, which is the sum
         * of the given range.
         * @param node the current node
         * @param start the starting index of the range of the current node
         * @param end the ending index of the range of the current node
         * @param left the starting index of the range of the query
         * @param right the ending index of the range of the query
         * @return the result of the range query, which is the sum of the given range
         */
        int[] arr = new int[right - left + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.tree[findTreeIndex(left + i)];
        }
        return sum(arr, 0, arr.length - 1);
    }


}
