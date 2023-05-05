public class MinimumSegmentTreeByArray extends SegmentTreeByArray{
    /**
     * Constructor for initializing the segment tree with the given input array.
     *
     * @param arr the input array
     */
    public MinimumSegmentTreeByArray(int[] arr) {
        super(arr);
    }

    @Override
    public int queryRange(int left, int right) {
        return 0;
    }

    @Override
    protected int query(int node, int start, int end, int left, int right) {
        return 0;
    }

    @Override
    public void update(int index, int value) {

    }

    @Override
    public int size() {
        return 0;
    }
}
