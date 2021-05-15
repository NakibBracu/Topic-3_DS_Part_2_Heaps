import java.util.Arrays;

public class PriorityQueue {
    private int[] items =  new int[5];
    private int count;

    public void add(int item){
        //0(n)-> cause in worst cases we have to shift all items to its rights.
        if(isFull())
            throw new IllegalStateException();

        var i = shiftItemsToInsert(item);
        items[i] = item;
        count++;
    }
    public boolean isFull(){
        return count== items.length;
    }
    public int shiftItemsToInsert(int item){
        int i;
        for(i=count-1;i>=0;i--){
            if(items[i]>item)
                items[i+1]=items[i];//shifting items
            else
                break;
        }
        return i+1;
    }
    public boolean isEmpty(){
        return count==0;
    }
    public int remove(){
        // considering highest number get the higher priority
        if(isEmpty())
            throw new IllegalStateException();
        return items[--count];
        // it doesn't remove any items physically from the array
        // it just decrement the size of the array and return the item
        // that's why time complexity is O(1).
    }
    @Override
    public String toString(){
        return Arrays.toString(items);
    }
}
