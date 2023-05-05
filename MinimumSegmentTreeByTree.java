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
        StringBuilder sb = new StringBuilder();
        sb.append(" [ ");
        preOrderSearch(root, sb);
        sb.append("] ");
        return sb.toString();
    }
    private void preOrderSearch(SegmentTreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        stringBuilder.append(root.getMin()).append(" ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}
