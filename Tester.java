///**
// * This is a testing framework. Use it extensively to verify that your code is working
// * properly.
// */
//public class Tester {
//
//    private static boolean testPassed = true;
//    private static int testNum = 0;
//
//    /**
//     * This entry function will test all classes created in this assignment.
//     * @param args command line arguments
//     */
//    public static void main(String[] args) {
//
//        /* TODO - write a function for each class separately */
//        // Each function here should test a different class. you should test here every class you created.
//
//
//        //SegmentTrees
//        testMaximumSegmentTreeByTree();
//        testMaximumSegmentTreeByArray();
//        //...
//        //NumberAnalyzers
//        testNumberAnalyzerByTrees();
//        // etc....
//
//        // Notifying the user that the code have passed all tests.
//        if (testPassed) {
//            System.out.println("All " + testNum + " tests passed!");
//        }
//    }
//
//    /**
//     * This utility function will count the number of times it was invoked.
//     * In addition, if a test fails the function will print the error message.
//     * @param exp The actual test condition
//     * @param msg An error message, will be printed to the screen in case the test fails.
//     */
//    private static void test(boolean exp, String msg) {
//        testNum++;
//
//        if (!exp) {
//            testPassed = false;
//            System.out.println("Test " + testNum + " failed: "  + msg);
//        }
//    }
//
//
//    /**
//     * Checks the MaximumSegmentTreeByTree class.
//     */
//    private static void testMaximumSegmentTreeByTree() {
//
//        MaximumSegmentTreeByTree mstbt = new MaximumSegmentTreeByTree(new int[]{60,10,5,15,6});
//
//        test(mstbt.queryRange(0,4) == 60, "The max of {60,10,5,15,6} between indexes [0:3] should be 60");
//
//        mstbt.update(1,80);
//        test(mstbt.queryRange(0,4) == 80, "After update index 1 from {60,10,5,15,6} to 80, the max between indexes [0:3] should be 80");
//
//        test(mstbt.toString().equals(" [ 80 80 80 60 80 5 15 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 80 80 80 60 80 5 15 15 6 ] ' got: '" + mstbt.toString()+ " '");
//    }
//
//    /**
//     * Checks the MaximumSegmentTreeByArray class.
//     */
//    private static void testMaximumSegmentTreeByArray() {
//
//        MaximumSegmentTreeByArray mstbt = new MaximumSegmentTreeByArray(new int[]{10,15,55,15,9,12});
//
//        test(mstbt.toString().equals(" [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");
//
//        test(mstbt.queryRange(0,1) == 15, "The max of {10,15,55,15,9,12} between indexes [0:1] should be 15");
//
//        mstbt.update(0,80);
//        test(mstbt.queryRange(0,0) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the max between indexes [0:0] should be 80");
//    }
//    /**
//     * Checks the NumberAnalyzerByTrees class.
//     */
//    private static void testNumberAnalyzerByTrees() {
//
//        Integer[] arr = {10,30,50};
//        NumberAnalyzerByTrees nabt = new NumberAnalyzerByTrees(arr);
//
//        test(nabt.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
//        test(nabt.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
//        test(nabt.getSum(0,1) == 40, "The sum of {10,30,50} between indexes [0:1] should be 40");
//    }
//
//}
