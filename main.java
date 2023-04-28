class main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        MaximumSegmentTreeByTree maxTree = new MaximumSegmentTreeByTree(arr);
        maxTree.update(1, 10);
    }
}
