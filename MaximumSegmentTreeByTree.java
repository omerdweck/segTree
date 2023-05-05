import java.util.Arrays;

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
        // we use pre-order traversal to print the tree --> [ root, left, right ]
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        preOrderSearch(root, sb); // what would be the parameter to split by numbers only?
        sb.append(']');
        return sb.toString();
    }
    private void preOrderSearch(SegmentTreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        stringBuilder.append(root.getMax() + " ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}
