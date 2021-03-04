import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<T>();
    
    public Stack(){}
    
    public Stack(T first_elem) {
        push(first_elem);
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void push(T elem) {
        list.addLast(elem);
    }
    
    public T pop() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        return list.removeLast();
    }
    
    public T peek() {
        if(isEmpty()) throw new java.util.EmptyStackException();
        return list.peekLast();
    }
    
    public String display(){
        if(isEmpty()) {
            return null;
        }
        String res = "";
        for(T elem: list) {
            res += elem.toString();
        }
        return res;
    }
    @Override public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}