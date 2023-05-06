//public class NumberAnalyzerByTrees extends NumberAnalyzer{
//
//
//    /**
//     * Constructs a new NumberAnalyzer object with the given array of integers.
//     *
//     * @param numbers The array of integers to be analyzed.
//     */
//    public NumberAnalyzerByTrees(Integer[] numbers) {
//        super(numbers);
//    }
//
//    @Override
//    public Integer getMax(int left, int right) {
//        return null;
//    }
//
//    @Override
//    public Integer getMax(int left, int right, Iterator<Integer> it) {
//        int max = Integer.MIN_VALUE;
//        int index = 0;
//        while (it.hasNext() && index <= right) {
//            int num = it.next();
//            if (index >= left && num > max) {
//                max = num;
//            }
//            index++;
//        }
//        return max;
//    }
//
//
//
//    @Override
//    public Integer getMin(int left, int right) {
//        return null;
//    }
//
//    @Override
//    public Integer getSum(int left, int right) {
//        return null;
//    }
//
//    @Override
//    public void update(int index, int value) {
//
//
//    }
//
//    @Override
//    public int compare(Integer o1, Integer o2) {
//        return 0;
//    }
//}
