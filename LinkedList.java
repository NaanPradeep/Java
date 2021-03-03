
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
import java.util.*;

public class LinkedList<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;
    
    public class Node<T> {
        private T data;
        private Node<T> next;
        
        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return data.toString();
        }
    }
    
    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            head.next = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void insert(T elem) {
        if(isEmpty()) {
            addAtFirst(elem);
        }
        else {
            addAtLast(elem);
        }
    }
    
    public void addAtFirst(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null);
        }
        else {
            Node<T> prev_head = head;
            head = new Node<T>(elem, prev_head);
        }
        size++;
    }
    
    public void addAtLast(T elem) {
        if(isEmpty()) {
            head = tail = new Node<T>(elem, null);
        }
        else {
            Node<T> new_tail = new Node<T>(elem, null);
            tail.next = new_tail;
            tail = new_tail;
        }
        size++;
    }

    /* Index starts from 0  */
    public void addAtIndex(int index, T elem) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<T> trav1 = head;
        Node<T> trav2 = trav1.next;
        for(int i = 0; i < index-1; i++){
            trav1 = trav1.next;
            trav2 = trav2.next;
        }
        trav1.next = new Node<T>(elem, trav2);
        size++;
    }
    
    public void removeFirst() {
        if(isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        Node<T> cur_head = head;
        Node<T> new_head = cur_head.next;
        cur_head.next = null;
        head = new_head;
        size--;
    }
    
    public void removeLast() {
        if(isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        Node<T> trav = head;
        for(int i = 0; i < size-1; i++) {
            trav = trav.next;
        }
        trav.next = null;
        tail = trav;
        size--;
    }
    
    public void removeAtIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<T> trav1 = head;
        Node<T> trav2 = trav1.next;
        for(int i=0; i<index-1; i++) {
            trav1 = trav1.next;
            trav2 = trav2.next;
        }
        trav1.next = trav2.next;
        trav2.next = null;
        size--;
    }
    
    public String display() {
        Node<T> trav = head;
        String str = "";
        for(int i = 0; i < size; i++){
            str = str + trav.toString() + '-';
            trav = trav.next;
        }
        return str;
    }
}