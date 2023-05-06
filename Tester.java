/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* TODO - write a function for each class separately */
        // Each function here should test a different class. you should test here every class you created.


        //SegmentTrees
        testMaximumSegmentTreeByTree();
        testMinimumSegmentTreeByTree();
        testSummationSegmentTreeByTree();
        testMaximumSegmentTreeByArray();
        testMinimumSegmentTreeByArray();
        testSummationSegmentTreeByArray();
        //...
        //NumberAnalyzers
        testNumberAnalyzerByTrees();
        testNumberAnalyzerByArrays();

        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }


    /**
     * Checks the SegmentTreeByTree classes.
     */
    private static void testMaximumSegmentTreeByTree() {

        MaximumSegmentTreeByTree mstbt = new MaximumSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(mstbt.queryRange(0,4) == 60, "The max of {60,10,5,15,6} between indexes [0:4] should be 60");

        mstbt.update(1,80);
        test(mstbt.queryRange(0,4) == 80, "After update index 1 from {60,10,5,15,6} to 80, the max between indexes [0:4] should be 80");

        test(mstbt.toString().equals(" [ 80 80 80 60 80 5 15 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 80 80 80 60 80 5 15 15 6 ] ' got: '" + mstbt.toString()+ " '");

    }
    private static void testMinimumSegmentTreeByTree() {

        MinimumSegmentTreeByTree mistbt = new MinimumSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(mistbt.queryRange(0,4) == 5, "The min of {60,10,5,15,6} between indexes [0:4] should be 5");

        mistbt.update(1,80);
        test(mistbt.queryRange(0,4) == 5, "After update index 1 from {60,10,5,15,6} to 80, the min between indexes [0:4] should be 5");

        test(mistbt.toString().equals(" [ 5 5 60 60 80 5 6 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 5 5 60 60 80 5 6 15 6 ] ' got: '" + mistbt.toString()+ " '");

    }

    private static void testSummationSegmentTreeByTree() {

        SummationSegmentTreeByTree sstbt = new SummationSegmentTreeByTree(new int[]{60,10,5,15,6});

        test(sstbt.queryRange(0,4) == 96, "The sum of {60,10,5,15,6} between indexes [0:4] should be 96");

        sstbt.update(1,80);
        test(sstbt.queryRange(0,4) == 166, "After update index 1 from {60,10,5,15,6} to 80, the sum between indexes [0:4] should be 166");

        test(sstbt.toString().equals(" [ 166 145 140 60 80 5 21 15 6 ] "),"The toString of {60,80,5,15} should be ' [ 166 145 140 60 80 5 21 15 6 ] ' got: '" + sstbt.toString()+ " '");

    }

    /**
     * Checks the SegmentTreeByArray classes.
     */
    private static void testMaximumSegmentTreeByArray() {

        MaximumSegmentTreeByArray mstbt = new MaximumSegmentTreeByArray(new int[]{10,15,55,15,9,12});

        test(mstbt.toString().equals(" [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] "),"The toString of {10,15,55,15,9,12} should be ' [ 55 55 15 15 55 15 12 10 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");

        test(mstbt.queryRange(0,1) == 15, "The max of {10,15,55,15,9,12} between indexes [0:1] should be 15");

        mstbt.update(0,80);
        test(mstbt.queryRange(0,0) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the max between indexes [0:0] should be 80");

        test(mstbt.toString().equals(" [ 80 80 15 80 55 15 12 80 15 - - 15 9 - - ] "),"The toString of {80,15,55,15,9,12} should be ' [ 80 80 15 80 55 15 12 80 15 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");

        mstbt.update(1,90);

        test(mstbt.toString().equals(" [ 90 90 15 90 55 15 12 80 90 - - 15 9 - - ] "),"The toString of {80,90,55,15,9,12} should be ' [ 90 90 15 90 55 15 12 80 90 - - 15 9 - - ] ' got: '" + mstbt.toString()+ " '");

    }

    private static void testMinimumSegmentTreeByArray() {

        MinimumSegmentTreeByArray mstbt = new MinimumSegmentTreeByArray(new int[]{10, 15, 55, 15, 9, 12});

        test(mstbt.toString().equals(" [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] "), "The toString of {10,15,55,15,9,12} should be ' [ 9 10 9 10 55 9 12 10 15 - - 15 9 - - ] ' got: '" + mstbt.toString() + " '");

        test(mstbt.queryRange(0, 1) == 10, "The min of {10,15,55,15,9,12} between indexes [0:1] should be 10");

        mstbt.update(0, 80);
        test(mstbt.queryRange(0, 0) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the min between indexes [0:0] should be 80");

        test(mstbt.toString().equals(" [ 9 15 9 15 55 9 12 80 15 - - 15 9 - - ] "), "The toString of {80,15,55,15,9,12} should be ' [ 9 15 9 15 55 9 12 80 15 - - 15 9 - - ] ' got: '" + mstbt.toString() + " '");

        mstbt.update(1, 90);

        test(mstbt.toString().equals(" [ 9 55 9 80 55 9 12 80 90 - - 15 9 - - ] "), "The toString of {80,90,55,15,9,12} should be ' [ 9 55 9 80 55 9 12 80 90 - - 15 9 - - ] ' got: '" + mstbt.toString() + " '");

    }

    private static void testSummationSegmentTreeByArray() {

        SummationSegmentTreeByArray sstbt = new SummationSegmentTreeByArray(new int[]{10, 15, 55, 15, 9, 12});

        test(sstbt.toString().equals(" [ 116 80 36 25 55 24 12 10 15 - - 15 9 - - ] "), "The toString of {10,15,55,15,9,12} should be ' [ 116 80 36 25 55 24 12 10 15 - - 15 9 - - ] ' got: '" + sstbt.toString() + " '");

        test(sstbt.queryRange(0, 1) == 25, "The sum of {10,15,55,15,9,12} between indexes [0:1] should be 25");

        sstbt.update(0, 80);
        test(sstbt.queryRange(0, 0) == 80, "After update index 0 from {10,15,55,15,9,12} to 80, the sum between indexes [0:0] should be 80");

        test(sstbt.toString().equals(" [ 186 150 36 95 55 24 12 80 15 - - 15 9 - - ] "), "The toString of {80,15,55,15,9,12} should be ' [ 186 150 36 95 55 24 12 80 15 - - 15 9 - - ] ' got: '" + sstbt.toString() + " '");

        sstbt.update(1, 90);

        test(sstbt.toString().equals(" [ 261 225 36 170 55 24 12 80 90 - - 15 9 - - ] "), "The toString of {80,90,55,15,9,12} should be ' [ 261 225 36 170 55 24 12 80 90 - - 15 9 - - ] ' got: '" + sstbt.toString() + " '");
    }
    /**
     * Checks the NumberAnalyzer classes.
     */

    private static void testNumberAnalyzerByTrees() {

        Integer[] arr = {10,40,50};
        NumberAnalyzerByTrees nabt = new NumberAnalyzerByTrees(arr);

        test(nabt.getMax(0,1) == 40, "The max of {10,30,50} between indexes [0:1] should be 40");
        test(nabt.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(nabt.getSum(0,1) == 50, "The sum of {10,30,50} between indexes [0:1] should be 50");

        Integer[] arr2 = {10,30,50,20,40,60};
        NumberAnalyzerByTrees nabt2 = new NumberAnalyzerByTrees(arr2);

        test(nabt2.getMax(0,5) == 60, "The max of {10,30,50,20,40,60} between indexes [0:5] should be 60");
        test(nabt2.getMin(0,5) == 10, "The min of {10,30,50,20,40,60} between indexes [0:5] should be 10");
        test(nabt2.getSum(0,5) == 210, "The sum of {10,30,50,20,40,60} between indexes [0:5] should be 210");

        nabt2.update(0, 80);
        test(nabt2.getMax(0,5) == 80, "The max of {80,30,50,20,40,60} between indexes [0:5] should be 80");
        test(nabt2.getMin(0,5) == 20, "The min of {80,30,50,20,40,60} between indexes [0:5] should be 20");
        test(nabt2.getSum(0,5) == 280, "The sum of {80,30,50,20,40,60} between indexes [0:5] should be 280");

        nabt2.update(5, 10);
        test(nabt2.getMax(0,5) == 80, "The max of {80,30,50,20,40,10} between indexes [0:5] should be 80");
        test(nabt2.getMin(0,5) == 10, "The min of {80,30,50,20,40,10} between indexes [0:5] should be 10");
        test(nabt2.getSum(0,5) == 230, "The sum of {80,30,50,20,40,10} between indexes [0:5] should be 230");


        test(nabt2.compare(6, 6) == 0, "The compare of 6 and 6 should return 0");
        test(nabt2.compare(6, 5) == -1, "The compare of 6 and 5 should return -1");
        test(nabt2.compare(5, 6) == 1, "The compare of 5 and 6 should return 1");
        test(nabt2.compare(0, 5) == -1, "The compare of 0 and 5 should return -1");
        test(nabt2.compare(5, 0) == 1, "The compare of 5 and 0 should return 1");
        test(nabt2.compare(0, 0) == 0, "The compare of 0 and 0 should return 0");
    }
    private static void testNumberAnalyzerByArrays() {

        Integer[] arr = {10,30,50};
        NumberAnalyzerByArrays naba = new NumberAnalyzerByArrays(arr);

        test(naba.getMax(0,1) == 30, "The max of {10,30,50} between indexes [0:1] should be 30");
        test(naba.getMin(0,1) == 10, "The min of {10,30,50} between indexes [0:1] should be 10");
        test(naba.getSum(0,1) == 40, "The sum of {10,30,50} between indexes [0:1] should be 40");

        Integer[] arr2 = {10,30,50,20,40,60};
        NumberAnalyzerByArrays naba2 = new NumberAnalyzerByArrays(arr2);

        test(naba2.getMax(0,5) == 60, "The max of {10,30,50,20,40,60} between indexes [0:5] should be 60");
        test(naba2.getMin(0,5) == 10, "The min of {10,30,50,20,40,60} between indexes [0:5] should be 10");
        test(naba2.getSum(0,5) == 210, "The sum of {10,30,50,20,40,60} between indexes [0:5] should be 210");

        Integer[] arr3 = {10,30,50,20,40,60,70,80,90,100};
        NumberAnalyzerByArrays naba3 = new NumberAnalyzerByArrays(arr3);

        test(naba3.getMax(0,9) == 100, "The max of {10,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 100");
        test(naba3.getMin(0,9) == 10, "The min of {10,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 10");
        test(naba3.getSum(0,9) == 550, "The sum of {10,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 550");

        naba3.update(0, 6);

        test(naba3.getMax(0,9) == 100, "The max of {6,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 100");
        test(naba3.getMin(0,9) == 6, "The min of {6,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 6");
        test(naba3.getSum(0,9) == 546, "The sum of {6,30,50,20,40,60,70,80,90,100} between indexes [0:9] should be 546");

        naba3.update(9, 6);

        test(naba3.getMax(0,9) == 90, "The max of {6,30,50,20,40,60,70,80,90,6} between indexes [0:9] should be 90");
        test(naba3.getMin(0,9) == 6, "The min of {6,30,50,20,40,60,70,80,90,6} between indexes [0:9] should be 6");
        test(naba3.getSum(0,9) == 452, "The sum of {6,30,50,20,40,60,70,80,90,6} between indexes [0:9] should be 452");

        test(naba3.compare(6, 6) == 0, "The compare of 6 and 6 should return 0");
        test(naba3.compare(6, 7) == -1, "The compare of 6 and 7 should return -1");
        test(naba3.compare(7, 6) == 1, "The compare of 7 and 6 should return 1");
        test(naba3.compare(6, 8) == -1, "The compare of 6 and 8 should return -1");
        test(naba3.compare(8, 6) == 1, "The compare of 8 and 6 should return 1");
        test(naba3.compare(6, 9) == -1, "The compare of 6 and 9 should return -1");

    }
}
