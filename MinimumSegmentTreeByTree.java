public class MinimumSegmentTreeByTree extends SegmentTreeByTree{
    public MinimumSegmentTreeByTree(int[] arr) {
        super(arr);
    }
    @Override
    public int queryRange(int left, int right) {
        SegmentTreeNode result = queryRangeHelper(root, left, right);
        return result.getMin();
    }
    public String toString() {
        // we use pre-order traversal to print the tree --> [ root, left, right ]
        return "";
    }
}
