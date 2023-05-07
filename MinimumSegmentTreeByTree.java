public class MinimumSegmentTreeByTree extends SegmentTreeByTree{
    public MinimumSegmentTreeByTree(int[] arr) {
        super(arr);
    }
    @Override
    public int queryRange(int left, int right) {
        /**
         * Returns the result of the range query, which is the minimum value in the given range.
         * @param left the starting index of the range of the query.
         * @param right the ending index of the range of the query.
         * @return the result of the range query, which is the minimum value in the given range.
         */
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
        /**
         * toString helper function, pre-order traversal. Each step appends the minimum value of the current node to
         * the stringBuilder. If the current node is null, the function returns, skipping the current node.
         * @param root the current node
         * @param stringBuilder the stringBuilder to append the minimum value of the current node to
         */
        if (root == null) {
            return;
        }
        stringBuilder.append(root.getMin()).append(" ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}
