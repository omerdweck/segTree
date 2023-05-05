<<<<<<< HEAD
class main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int[] arr2 = {0, 0, 0, 0, 0, 0};
        MaximumSegmentTreeByTree maxTree = new MaximumSegmentTreeByTree(arr);;
//        maxTree.update(0, 10);
        System.out.println(maxTree.queryRange(0, 5));
        MinimumSegmentTreeByTree minTree = new MinimumSegmentTreeByTree(arr);
        minTree.update(0, 10);
        System.out.println(minTree.queryRange(0, 5));
        SummationSegmentTreeByTree sumTree = new SummationSegmentTreeByTree(arr);
        sumTree.update(0, 10);
        System.out.println(sumTree.queryRange(0, 5));
        System.out.println(maxTree + "\n" + minTree + "\n" + sumTree);
        MinimumSegmentTreeByTree minTree2 = new MinimumSegmentTreeByTree(arr2);
        System.out.println(minTree2);
        System.out.println(maxTree.size() + "\n" + minTree.size() + "\n" + sumTree.size() + "\n" + minTree2.size());
        SummationSegmentTreeByArray maxTree3 = new SummationSegmentTreeByArray(arr);
         System.out.println(maxTree3);
    }
}
=======
>>>>>>> 0736e20a617d84fea4f0185c9db4b5cdd9de99c7
