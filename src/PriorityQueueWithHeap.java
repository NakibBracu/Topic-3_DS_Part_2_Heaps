public class PriorityQueueWithHeap {
    private Heap heap = new Heap();
    //this is simply a wrapper class of heap.
    public void enqueue(int item){//O(log n)
        heap.insert(item);
    }
    public int dequeue(){//O(log n)
        return heap.remove();
    }
    public boolean isEmpty(){
        return heap.isEmpty();
    }
}
