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
        /**
         * Returns the result of the range query, which is the summation value of the given range.
         * @param left the starting index of the range of the query.
         * @param right the ending index of the range of the query.
         * @return the result of the range query, which is the summation value of the given range.
         */
        SegmentTreeNode result = queryRangeHelper(root, left, right);
        return result.getSum();
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
         * toString helper function, pre-order traversal. Each step appends the summation value of the current node
         * with its children to the stringBuilder. If the current node is null, the function returns, skipping
         * the current node.
         * @param root the current node
         * @param stringBuilder the stringBuilder to append the minimum value of the current node to
         */
        if (root == null) {
            return;
        }
        stringBuilder.append(root.getSum()).append(" ");
        preOrderSearch((SegmentTreeNode) root.getLeft(), stringBuilder);
        preOrderSearch((SegmentTreeNode) root.getRight(), stringBuilder);
    }
}
