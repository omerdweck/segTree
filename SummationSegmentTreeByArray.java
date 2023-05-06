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
        int res = 0;
        for (int i = start; i <= end; i++) {
            res += arr[i];
        }
        return res;
    }

    @Override
    protected void fixArrayAfterUpdate(int fromIndex) {
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
        int[] arr = new int[right - left + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.tree[findTreeIndex(left + i)];
        }
        return sum(arr, 0, arr.length - 1);
    }


}
