import java.util.Iterator;

public class NumberAnalyzerByArrays extends NumberAnalyzer{

    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     *
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzerByArrays(Integer[] numbers) {
        super(numbers);
        Iterator<Integer> NAIterator = iterator();
    }

    @Override
    public Integer getMax(int left, int right) {
        /**
         * Returns the maximum value in the given range.
         * @param left The left endpoint of the range.
         * @param right The right endpoint of the range (inclusive).
         * @return The maximum value in the range.
         */
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new MaximumSegmentTreeByArray(temp_arr).queryRange(left, right);
    }

    @Override
    public Integer getMin(int left, int right) {
        /**
         * Returns the minimum value in the given range.
         * @param left The left endpoint of the range.
         * @param right The right endpoint of the range (inclusive).
         * @return The minimum value in the range.
         */
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new MinimumSegmentTreeByArray(temp_arr).queryRange(left, right);
    }

    @Override
    public Integer getSum(int left, int right) {
        /**
         * Returns the sum of the values in the given range.
         * @param left The left endpoint of the range.
         * @param right The right endpoint of the range (inclusive).
         * @return The sum of the values in the range.
         */
        int[] temp_arr = new int[right - left + 1];
        for (int i = 0; i < temp_arr.length; i++) {
            temp_arr[i] = this.numbers[left + i];
        }
        return new SummationSegmentTreeByArray(temp_arr).queryRange(left, right);
    }

    @Override
    public void update(int index, int value) {
        /**
         * Updates the value at the given index.
         * @param index The index of the value to be updated.
         * @param value The new value to be set at the given index.
         */
        this.numbers[index] = value;
    }

}
