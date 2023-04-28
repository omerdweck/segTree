public class SummationSegmentTreeByTree extends SegmentTreeByTree{
    /**
     * Constructor for creating a Segment Tree from an input array
     *
     * @param arr Input array for which Segment Tree needs to be constructed
     */
    public SummationSegmentTreeByTree(int[] arr) {
        super(arr);
    }

    @Override
    public int queryRange(int left, int right) {
        SegmentTreeNode result = queryRangeHelper(root, left, right);
        return result.getSum();
    }

//    public String toString() {
//        // i need to to strind this by pre-order traversal.
//
//    }
}
