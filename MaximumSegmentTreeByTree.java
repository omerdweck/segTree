public class MaximumSegmentTreeByTree extends SegmentTreeByTree{
    /**
     * Constructor for creating a Segment Tree from an input array
     *
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public MaximumSegmentTreeByTree(int[] arr) {
        super(arr);
    }

    @Override
    public int queryRange(int left, int right) {
        SegmentTreeNode result = queryRangeHelper(root, left, right);
        return result.getMax();
    }

    public String toString() {
       return "";
        }
}
