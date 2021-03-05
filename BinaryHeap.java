import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
    private int heapSize = 0;
    private int heapCapacity = 0;
    private List<T> heap = null;
    
    public BinaryHeap() {
        this(1);
    }
    
    public BinaryHeap(int capacity) {
        heap = new ArrayList<T>(capacity);
    }
    
    public BinaryHeap(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<T>(heapCapacity);
        
        for(int i=0; i<heapSize; i++) {
            heap.add(elems[i]);
        }
        
        for(int i = (heapSize/2) - 1; i >=0; i--){
            sink(i);
        }
    }
    
    public int size() {
        return heapSize;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void add(T elem) {
        if(elem == null) throw new IllegalArgumentException();
        if(heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        }
        else {
            heap.add(elem);
            heapCapacity++;
        }
        swim(heapSize);
        heapSize++;
    }
    
    public T poll() {
        return removeAt(0);
    }
    
    public boolean remove(T elem) {
        if(elem == null) return false;
        
        for(int i = 0; i < heapSize; i++) {
            if(heap.get(i).equals(elem)){
                removeAt(i);
                return true;
            }
        }
        return false;
    }
    
    public T removeAt(int index) {
        if(isEmpty()) return null;
        heapSize--;
        T removed_data = heap.get(index);
        swap(index, heapSize);
        heap.set(heapSize, null);
        if(index == heapSize) return removed_data;
        T elem = heap.get(index);
        
        sink(index);
        if(heap.get(index).equals(elem)) swim(index);
        return removed_data;
    }
    
    public boolean node1_less_than_or_equals_node2(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }
    
    public void sink(int elem_index) {
        while(true) {
            int left_node = 2 * elem_index + 1;
            int right_node = 2 * elem_index + 2;
            int smallest = left_node;
            
            if(right_node < heapSize && node1_less_than_or_equals_node2(right_node, left_node)) smallest = right_node;
            
            if(left_node >= heapSize || node1_less_than_or_equals_node2(elem_index, smallest)) break;
            
            swap(smallest, elem_index);
            elem_index = smallest;
        }
    }
    
    public void swim(int elem_index) {
        int parent_index = (elem_index - 1) / 2;
        
        while(elem_index > 0 && node1_less_than_or_equals_node2(elem_index, parent_index)){
            swap(parent_index, elem_index);
            elem_index = parent_index;
            parent_index = (elem_index - 1) / 2;
            
        }
    }
    
    public void swap(int i, int j) {
        T temp_1 = heap.get(i);
        T temp_2 = heap.get(j);
        
        heap.set(i, temp_2);
        heap.set(j, temp_1);
    }
    
    @Override
  public String toString() {
    return heap.toString();
  }
    
}