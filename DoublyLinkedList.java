package practice2;

public class DoublyLinkedList<T> {
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	private static class Node<T> {
		private T data;
		private Node<T> prev;
		private Node<T> next;
		
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void add(T data) {
		addLast(data);
	}
	
	public void addFirst(T data) {
		if(isEmpty()) {
			head = new Node<T>(data, null, null);
			tail = head;
		}
		else {
			Node<T> oldHead = head;
			head = new Node<T>(data, null, oldHead);
			oldHead.prev = head;
		}
		size++;
	}
	
	public void addLast(T data) {
		if(isEmpty()) {
			tail = new Node<T>(data, null, null);
			head = tail;
		}
		else {
			Node<T> oldTail = tail;
			tail = new Node<T>(data, oldTail, null);
			oldTail.next = tail;
		}
		size++;
	}
	
	
	public void addAt(int index, T data) {
		if(index <= size / 2) {
			Node<T> curNode = head;
			for(int i = 0; i < index-1; i++) {
				curNode = curNode.next;
			}
			Node<T> newNode = new Node<T>(data, curNode, curNode.next);
			curNode.next.prev = newNode;
			curNode.next = newNode;
		}
		else {
			Node<T> curNode = tail;
			for(int i = 0; i < size() - (index+1); i++) {
				curNode = curNode.prev;
			}
			Node<T> newNode = new Node<T>(data, curNode.prev, curNode);
			curNode.prev.next = newNode;
			curNode.prev = newNode;
		}
		size++;
	}
	
	public T removeFirst() {
		if(isEmpty()) throw new NullPointerException("List is empty");
		T data;
		data = head.data;
		if(head.next != null) {
			head = head.next;
			head.prev = null;
		}
		else {
			head = tail = null;
		}
		size--;
		return data;
	}
	
	public T removeLast() {
		if(isEmpty()) throw new NullPointerException("List is empty");
		T data;
		data = tail.data;
		if(tail.prev != null) {
			tail = tail.prev;
			tail.next = null;
		}
		else {
			head = tail = null;
		}
		size--;
		return data;
	}
	
	public T removeAt(int index) {
		
		if(index == 0) return removeFirst();
		if(index == size-1) return removeLast();
		
		T data;
		if(index <= size / 2) {
			Node<T> curNode = head;
			for(int i = 0; i < index; i++) {
				curNode = curNode.next;
			}
			data = curNode.data;
			curNode.next.prev = curNode.prev;
			curNode.prev.next = curNode.next;
		}
		else {
			Node<T> curNode = tail;
			for(int i = 0; i < size() - (index); i++) {
				curNode = curNode.prev;
			}
			data = curNode.data;
			curNode.next.prev = curNode.prev;
			curNode.prev.next = curNode.next;
		}
		size--;
		return data;
	}
	
	public boolean remove(T data) {
		Node<T> trav = head;
		int index = 0;
		while(!data.equals(trav.data) && trav != null) {
			index++;
			trav = trav.next;
		}
		
		if(trav != null) {
			removeAt(index);
			return true;
		}
		return false;
	}
	
	
	public String printList() {
		Node<T> trav = head;
		String result = "";
		
		while(trav != null) {
			result += trav.data.toString() + " - ";
			trav = trav.next;
 		}
		return result;
	}
	
	public String printReverseList() {
		Node<T> trav = tail;
		String result = "";
		
		while(trav != null) {
			result += trav.data.toString() + " - ";
			trav = trav.prev;
		}
		return result;
	}
}
