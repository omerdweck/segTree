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
        return new Iterator<Integer>() {
            private int index = 0;

            public boolean hasNext() {
                return index < numbers.length;
            }

            public Integer next() {
                return numbers[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    public Comparator<Integer> comparator() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (a % 2 == 1 && b % 2 == 0) {
                    // a is odd and b is even, so b is bigger than a
                    return 1;
                } else if (a % 2 == 0 && b % 2 == 1) {
                    // a is even and b is odd, so a is bigger than b
                    return -1;
                } else {
                    // both a and b are odd or both are even, so compare them normally
                    return a.compareTo(b); // returns 0
                }
            }
        };
        return comparator;
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