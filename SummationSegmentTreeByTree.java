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

    public String toString() {
        // we use pre-order traversal to print the tree --> [ root, left, right ]
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        preOrderSearch(root, sb);
        sb.append("]");
        return sb.toString();
    }

    private void preOrderSearch(SegmentTreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        stringBuilder.append(root.getSum() + " ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}
