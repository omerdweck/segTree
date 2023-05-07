public class MinimumSegmentTreeByArray extends SegmentTreeByArray{
    /**
     * Constructor for initializing the segment tree with the given input array.
     *
     * @param arr the input array
     */
    public MinimumSegmentTreeByArray(int[] arr) {
        super(arr);
    }

    @Override
    public int calcValOfNode(int[] arr, int start, int end) {
        /**
         * A simple implementation of this method would be to return the minimum value in the given range.
         * Using polymorphism, we return the minimum value in the given range.
         * @param arr the input array
         * @param start the start index of the range
         * @param end the end index of the range
         * @return the minimum value in the given range
         */
        return min(arr, start, end);
    }

    public int min(int[] arr, int start, int end) {
        /**
         * Returns the minimum value in the given range. Helper method for calcValOfNode().
         */
        int res = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.min(res, arr[i]);
        }
        return res;
    }
    @Override
    protected void fixArrayAfterUpdate(int fromIndex) {
        /**
         * After update, the value of the node is changed, so we need to update the value of its parent node
         * until the root node. The value of the parent node is the minimum value of its two children, so we
         * compare the value of the two children and update the value of the parent node up to the root node.
         * @param fromIndex the index of the node whose value is changed
         */
        while (fromIndex > 0) {
            fromIndex = (fromIndex - 1) / 2;
            this.tree[fromIndex] = Math.min(this.tree[2 * fromIndex + 1], this.tree[2 * fromIndex + 2]);
        }
    }

    @Override
    public int queryRange(int left, int right) {
        /**
         * Returns the result of the range query, which is the minimum value in the given range.
         * @param left the starting index of the range of the query
         * @param right the ending index of the range of the query
         * @return the result of the range query, which is the minimum value in the given range
         */
        return this.query(0, 0, this.tree.length - 1, left, right);
    }

    @Override
    protected int query(int node, int start, int end, int left, int right) {
        /**
         * Returns the result of the range query, which is the minimum value in the given range. This is done by
         * creating an array of the leaves in the given range and then finding the minimum value in that array.
         * @param node the current node
         * @param start the starting index of the range of the current node
         * @param end the ending index of the range of the current node
         * @param left the starting index of the range of the query
         * @param right the ending index of the range of the query
         * @return the result of the range query, which is the minimum value in the given range
         */
        int[] arr = new int[right - left + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.tree[findTreeIndex(left + i)];
        }
        return min(arr, 0, arr.length - 1);
    }

}
