import java.util.Iterator;

public class NumberAnalyzerByTrees extends NumberAnalyzer{


    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     *
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzerByTrees(Integer[] numbers) {
        super(numbers);
        Iterator<Integer> NAIterator = iterator();
    }

    @Override
    public Integer getMax(int left, int right) {
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new MaximumSegmentTreeByTree(temp_arr).queryRange(left, right);
    }



    @Override
    public Integer getMin(int left, int right) {
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new MinimumSegmentTreeByTree(temp_arr).queryRange(left, right);
    }

    @Override
    public Integer getSum(int left, int right) {
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new SummationSegmentTreeByTree(temp_arr).queryRange(left, right);
    }

    @Override
    public void update(int index, int value) {
        this.numbers[index] = value;
    }
}
