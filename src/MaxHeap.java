public class MaxHeap {
    public static void heapify(int array[]){
    for (var i = 0;i<array.length;i++)//now in each iteration we will heapify
        heapify(array,i);
    }
    private static void heapify(int array[],int index){
    var largerIndex = index;
        //we first assuming this(root is larger) index is okay and
        // the value of the index is greater or equals to its left & right child
        // but we have to validate this by using below steps.
        var leftIndex = index * 2 +1;
        //Now, if left child is greater than its parent
        // then we have to swap the reference of both the indexes
        if(leftIndex<array.length && array[leftIndex]>array[largerIndex])
            largerIndex = leftIndex;
        //Now, if right child is greater than its parent
        // then we have to swap the reference of both the indexes
        var rightIndex = index * 2 +2;
        if(rightIndex<array.length && array[rightIndex]>array[largerIndex])
            largerIndex = rightIndex;
        //if the item is greater or equal with both of its children
        //then we just have to return; that means we don't have to do anything
        if(index==largerIndex)//base case
            return;
        //Other than the cases we have to swap items
        swap(array,index,largerIndex);
        heapify(array,largerIndex);
        //for the branches we have to do it recursively
    }
    private static void swap(int[]array, int first, int second){
        var temp = array[first];
        array[first]=array[second];
        array[second]=temp;
    }
    public static void heapifyOptimized(int []array){
        //half of recursion will decreased
        var lastParentIndex = array.length / 2 - 1;
        for(int i=lastParentIndex;i>=0;i--)
            heapify(array,i);
    }
    public static int kthLargestItem(int [] array,int k){
        if(k<1 || k>array.length)
            throw new IllegalArgumentException();
        var heap = new Heap();
        for(var number:array)//inserting all the items in the array
            heap.insert(number);
        //now we have to delete the items till k-1 times
        // so that we got kth largest from root
        for(var i=0;i<k-1;i++){
            heap.remove();
        }
        return heap.max();
    }
}
