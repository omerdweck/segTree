
class main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int[] arr2 = {0, 0, 0, 0, 0, 0, 0};
        int[] arr3 = {1, 3, 2 ,4};
        MaximumSegmentTreeByTree maxTree = new MaximumSegmentTreeByTree(arr);;
        maxTree.update(0, 10);
        System.out.println(maxTree.queryRange(0, 5));
        MinimumSegmentTreeByTree minTree = new MinimumSegmentTreeByTree(arr);
        minTree.update(0, 10);
        System.out.println(minTree.queryRange(0, 5));
        SummationSegmentTreeByTree sumTree = new SummationSegmentTreeByTree(arr);
        sumTree.update(1, 10);
        System.out.println(sumTree.queryRange(0, 5));
        System.out.println(maxTree + "\n" + minTree + "\n" + sumTree);
        MinimumSegmentTreeByTree minTree2 = new MinimumSegmentTreeByTree(arr2);
        System.out.println(minTree2);
        System.out.println(maxTree.size() + "\n" + minTree.size() + "\n" + sumTree.size() + "\n" + minTree2.size());
        SummationSegmentTreeByArray maxTree3 = new SummationSegmentTreeByArray(arr);
        maxTree3.update(4, 10);
        System.out.println(maxTree3);
        MinimumSegmentTreeByArray minTree3 = new MinimumSegmentTreeByArray(arr2);
        System.out.println(minTree3.queryRange(5, 5));
        minTree3.update(0, 10);
        System.out.println(minTree3);
        SummationSegmentTreeByArray sumTree3 = new SummationSegmentTreeByArray(arr2);
        sumTree3.update(1, 10);
        System.out.println(sumTree3.queryRange(0, 5));
        System.out.println(sumTree3);
        MaximumSegmentTreeByArray maxTree4 = new MaximumSegmentTreeByArray(arr2);
        maxTree4.update(0, 10);
        System.out.println(maxTree4.queryRange(0, 5));
    }
}
