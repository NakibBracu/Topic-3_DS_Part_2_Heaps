public class Heap {
    private int items[] = new int[10];
    private int size;
    public void insert(int value){
        //time complexity -> O(log n)
        // inserting item in the leaf
        //then if it is not in right position
        //we must have to bubble up for fixing it
        //in that case in worst case item has to travel the
        //height of a tree which is O(log n).
        // if we have too many insertion we can use heap to insert cause it is faster.
        if(isFull()) //array size full ==> give exception
            throw new IllegalStateException();
        items[size++] = value;
        bubbleUp();
    }
    private boolean isFull(){
        return size==items.length;
    }
    private void bubbleUp(){
        var index = size-1;
        while(index>0 && items[index]>items[parent(index)]){
            //bubble up
            swap(index,parent(index));
            //    10  =====>>>   10      =====>>> 12 (i)
            //   5 ========>>> 12 (i) ======>> 10
            // 12 i =======> 5   ==========>> 5
            // as everytime index will be changed so, index will become parent index
            index = parent(index);
        }
    }
    private int parent(int index){
        return (index-1)/2;
    }
    private void swap(int first,int second){
        var temp = items[first];
        items[first]=items[second];
        items[second]=temp;
    }
    public int remove(){
        //slower delete
        //O(log n) cause in worst cases it has to traverse the height of the tree which is O(log n)
        //first check if the heap is empty or not
        if(isEmpty())
            throw new IllegalStateException();
        //for removing we remove the last item and replace it with root ,
        // then set it root and bubble down if needed for heap property
        var root = items[0];
        items[0]=items[--size];//first decrement the size to get the last item and insert it in root
        //now its time for bubble down if (root<leftchild or root>rightchild)
        bubbleDown();
        return root;
    }
    private void bubbleDown(){
        var index = 0;
        while(index<=size/* at some point index may be -ve then we stopped*/ && !isValidParent(index)){
            //find which one is larger (left one or right one)
            var largerChildIndex = largerChildIndex(index);
            swap(index,largerChildIndex);
            index = largerChildIndex;//index can be changed multiple times before going to its right position
        }
    }
    private boolean hasLeftChild(int index){
        return leftChildIndex(index)<=size;
    }
    private boolean hasRightChild(int index){
        return rightChildIndex(index)<=size;
    }
    private int largerChildIndex(int index){
        //Now first we check if a node has a left child or not
        //if we don't have any leftchild that means this node doesn't have any children
        //because we fill nodes from left to right
        if(!hasLeftChild(index))
            return index;//leftChild na thakle oi index ei return korbo
        //now we see if the node has any rightChild or not if not then
        // we should return the leftChild Index cause it has only leftChild left
        if(!hasRightChild(index))
            return leftChildIndex(index);
        //now the 3rd case is the node both having its left and rightchild
        //in that case we have to compare between them and find the index with
        //larger value
        return (leftChild(index)>rightChild(index))?leftChildIndex(index):rightChildIndex(index);
    }
    private boolean isValidParent(int index){
        //if a node doesn't have any leftChild that means it is valid
        if(!hasLeftChild(index))
            return true;
        //now if the condition shown above is false that means we do have leftchild
        // then we compare the leftChild value with its parents
        var isValid = items[index]>=leftChild(index);
        //now if we also have right child then we have to think of both of the children of that node
        if(hasRightChild(index))
            isValid &= items[index]>=rightChild(index);
        //now 3rd case we check for both children with its parent that
        //parent must be greater than both of the children
        return isValid;
    }
    private int rightChild(int index){
        return items[rightChildIndex(index)];
    }
    private int leftChild(int index){
        return items[leftChildIndex(index)];
    }
    private int leftChildIndex(int index){
        return (index*2)+1;
    }
    private int rightChildIndex(int index){
        return (index*2)+2;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int max(){
        if(isEmpty())
            throw new IllegalStateException();
        return items[0];
    }
    public static boolean isMaxHeap(int[] array) {
        return isMaxHeap(array, 0);
    }

    private static boolean isMaxHeap(int[] array, int index) {
        // All leaf nodes are valid
        var lastParentIndex = (array.length - 2) / 2;
        //if we look at till last parent then it would be okay as per mosh
        if (index > lastParentIndex)
            return true;
        //find left and right child of every nodes till last parent
        // then compare them with the max heap property parent>=child
        var leftChildIndex = index * 2 + 1;
        var rightChildIndex = index * 2 + 2;

        var isValidParent =
                array[index] >= array[leftChildIndex] &&
                        array[index] >= array[rightChildIndex];

        return isValidParent &&
                isMaxHeap(array, leftChildIndex) &&
                isMaxHeap(array, rightChildIndex);
    }
}
