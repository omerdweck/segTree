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
        stringBuilder.append(root.getMin() + " ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}