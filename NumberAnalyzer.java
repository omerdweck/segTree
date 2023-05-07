import java.util.Comparator;
import java.util.Iterator;

/**
 The NumberAnalyzer class provides an abstract implementation for analyzing a collection of integers.
 It implements the Iterable interface to allow for iteration over the collection, and the Comparator interface
 to provide a default comparison method for integers.
 */
public abstract class NumberAnalyzer implements Iterable<Integer>, Comparator<Integer> {

    protected Integer[] numbers;

    /**
     * Constructs a new NumberAnalyzer object with the given array of integers.
     * @param numbers The array of integers to be analyzed.
     */
    public NumberAnalyzer(Integer[] numbers) {
        this.numbers = numbers;
    }

    public Iterator<Integer> iterator() {
        /**
         * Returns an iterator over the collection of integers.
         */
        return new Iterator<Integer>() {
            /**
             * an anonymous class that implements the Iterator interface.
             * It has a single field, index, which keeps track of the current index in the array.
             * The hasNext() method returns true if the index is less than the length of the array,
             * and false otherwise. The next() method returns the current number in the array and increments the index.
             * The remove() method throws an UnsupportedOperationException because its implementation is not required,
             * yet it is up to convention to throw this exception.
             */
            private int index = 0;

            public boolean hasNext() {
                return index < numbers.length;
            }

            public Integer next() {
                if (!hasNext()) throw new java.util.NoSuchElementException("No more numbers to iterate over!");
                int res = numbers[index];
                index++;
                return res;
            }

            public void remove() {
                throw new UnsupportedOperationException("Why are you trying to kill my numbers? They are innocent!");
            }
        };
    }
    protected int[] NAIterator(){
        /**
         * Returns an array of integers. This is a helper method for the subclasses.
         * It is used to iterate over the collection of integers. It creates a new array of integers, and then
         * iterates over the collection and adds each integer to the array.
         */
        int[] temp_arr = new int[numbers.length];
        int count = 0;
        Iterator<Integer> it = iterator();
        while (it.hasNext()) {
            temp_arr[count] = it.next();
            count++;
            }
        return temp_arr;
        }


    public int compare(Integer a, Integer b) {
        /**
         * Implements the compare method of the Comparator interface. It compares two integers and returns
         * 1, -1 or 0 according to the logic specified in the assignment guidelines.
         */
        if (a % 2 == 1 && b % 2 == 0) {
            // a is odd and b is even, so b is bigger than a
            return 1;
        } else if (a % 2 == 0 && b % 2 == 1) {
            // a is even and b is odd, so a is bigger than b
            return -1;
        } else {
            return a.compareTo(b);
        }
    }



    /**
     * Returns the maximum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The maximum value in the range.
     */
    public abstract Integer getMax(int left, int right);

    /**
     * Returns the minimum value in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The minimum value in the range.
     */
    public abstract Integer getMin(int left, int right);

    /**
     * Returns the sum of the values in the given range. This is an abstract function to be implemented by the subclasses
     * @param left The left endpoint of the range (inclusive).
     * @param right The right endpoint of the range (inclusive).
     * @return The sum of the values in the range.
     */
    public abstract Integer getSum(int left, int right);

    /**
     * Updates the value at the given index. This is an abstract function to be implemented by the subclasses
     * @param index The index of the value to be updated.
     * @param value The new value to be set at the given index.
     */
    public abstract void update(int index, int value);
}