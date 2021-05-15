public class MinHeap {
    private class Node{
        private int key;
        private String value;
        public Node(int key,String value){
            this.key=key;
            this.value=value;
        }
    }
    private Node[] nodes = new Node[10];
    private int size;
    public void insert(int key,String value){
        if(isFull())
            throw new IllegalStateException();
        nodes[size++]=new Node(key,value);
        bubbleup();
    }
    private void bubbleup(){
        var index=size-1;
        while (index>0 && nodes[index].key<nodes[parent(index)].key){
            swap(index,parent(index));
            index=parent(index);
        }
    }
    private int parent(int index){
        return (index-1)/2;
    }
    private void swap(int first,int second){
        var temp = nodes[first];
        nodes[first] = nodes[second];
        nodes[second] = temp;
    }
    public boolean isFull(){
        return size== nodes.length;
    }
    public String remove(){
        if(isEmpty())
            throw new IllegalStateException();
        var root = nodes[0].value;
        nodes[0]=nodes[--size];

        bubbleDown();

        return root;
    }

    private void bubbleDown(){
        var index=0;
        while(index<=size && !isValidParent(index)){
            var largerChildIndex=smallerChildIndex(index);
            swap(index,largerChildIndex);
            index = largerChildIndex;
        }
    }

    private int smallerChildIndex(int index){
        //in max heap the largest value will be on the root but here we work with min heap
        //that means lowest value should be at the root
        //For this reason, we find the smallest child here and swap between smaller child and larger child
        //largerchild will go down ,for that we use bubble down method
        if(!hasLeftChild(index))
            return index;
        if(!hasRightChild(index))
            return leftChildIndex(index);
        return (leftChild(index).key<rightChild(index).key) ? leftChildIndex(index):rightChildIndex(index);
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= size;
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        var isValid = nodes[index].key <= leftChild(index).key;

        if (hasRightChild(index))
            isValid &= nodes[index].key <= rightChild(index).key;

        return isValid;
    }

    private Node rightChild(int index) {
        return nodes[rightChildIndex(index)];
    }

    private Node leftChild(int index) {
        return nodes[leftChildIndex(index)];
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    public boolean isEmpty(){
        return size==0;
    }
}
