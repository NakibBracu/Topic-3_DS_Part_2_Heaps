import java.util.Arrays;
public class main {
    public static void main(String[] args) {
    var heap = new Heap();
    var heap2 = new Heap();
    int []numbers = {5,3,10,1,4,2};
    heap.insert(10);
    heap.insert(5);
    heap.insert(17);
    heap.insert(4);
    heap.insert(22);
    for(int i=0;i< numbers.length;i++){//inserting items in heap2 object which is also a max heap
        heap2.insert(numbers[i]);
    }
    /*while(!heap2.isEmpty())
        System.out.println(heap.remove());
        // as we build our heap as max heap so if we remove items from heap
        //all items will remove in descending order.
     */
    //if we want our items to be removed in ascending order
    // we can use for loop and backward iteration
    for(int i= numbers.length-1;i>= 0;i--){
        numbers[i]= heap2.remove();
    }
           System.out.println(Arrays.toString(numbers));
    //queue task
        var queue = new PriorityQueue();
        queue.add(30);
        queue.add(20);
        queue.add(10);
        queue.add(5);
        System.out.println("The queue is "+queue);
    //heapify test
        int a[] = {5,3,8,4,1,2};
        MaxHeap.heapify(a);//as this method is static , so we don't have to create object for max heap
        System.out.println(Arrays.toString(a));
    //heapify Test2 for optimizedOne
        int b[] = {15,13,18,14,11,12};
        MaxHeap.heapifyOptimized(b);
        System.out.println(Arrays.toString(b));
        System.out.println(MaxHeap.kthLargestItem(b,3));
    //ex1 test-> is max heap or not?
        System.out.println(new Heap().isMaxHeap(new int[]{15,13,18,14,11,12}));
        System.out.println(new Heap().isMaxHeap(new int[]{5,4,3,2,1}));
    //ex2 test-> min heap operation
        MinHeap minh = new MinHeap();
        minh.insert(10,"p");
        minh.insert(9,"q");
        minh.insert(8,"r");
        minh.insert(7,"s");
        minh.insert(6,"u");
        minh.insert(5,"v");
        minh.insert(4,"w");
        while(!minh.isEmpty())
            System.out.println(minh.remove());
        //ex-3==> Test
        MinPriorityQueue m1 = new MinPriorityQueue();
        m1.add("a",2);
        m1.add("b",1);
        m1.add("c",3);
        m1.add("d",4);
        while(!m1.isEmpty())
            System.out.println(m1.remove());
        //lowest priority will remove first
        //cause this example its base on the min heap
        System.out.println(m1.isEmpty());
    }
}
