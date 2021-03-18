package practice2;
import java.util.*;

class Entry<K, V> {
	int hash;
	K key;
	V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
		this.hash = key.hashCode();
	}
}

public class HashTableSeperateChaining<K, V> {
	private static final int DEFAULT_CAPACITY = 4;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	
	private int capacity, size, threshold = 0;
	private double maxLoadFactor;
	private LinkedList<Entry<K, V>>[] table;
	
	public HashTableSeperateChaining() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableSeperateChaining(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}
	
	@SuppressWarnings("unchecked")
	public HashTableSeperateChaining(int capacity, double maxLoadFactor) {
		if(capacity <= 0) throw new IllegalArgumentException("Illegal capacity");
		if(maxLoadFactor <= 0) throw new IllegalArgumentException("Illegal maxLoadFactor");
		
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		this.maxLoadFactor = maxLoadFactor;
		threshold = (int) (this.capacity * maxLoadFactor);
		table = new LinkedList[this.capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private int normalizeIndex(int keyHash) {
	    return (keyHash & 0x7FFFFFFF) % capacity;
	  }
	
	public V put(K key, V value) {
		return insert(key, value);
	}
	
	public V insert(K key, V value) {
		if(key == null) throw new IllegalArgumentException("Null key");
		
		Entry<K, V> newEntry = new Entry<>(key, value);
		int bucketIndex = normalizeIndex(key.hashCode());
		return bucketInsertEntry(bucketIndex, newEntry);
	}
	
	public V get(K key) {
		if(key == null) return null;
		int bucketIndex = normalizeIndex(key.hashCode());
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		if(entry != null) return entry.value;
		return null;
	}
	
	public V remove(K key) {
		if(key == null) return null;
		int bucketIndex = normalizeIndex(key.hashCode());
		return bucketRemoveEntry(bucketIndex, key);
	}
	
	private V bucketRemoveEntry(int bucketIndex, K key) {
		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		if(bucket == null) return null;
		
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		if(entry != null) {
			bucket.remove(entry);
			size--;
		}
		return entry.value;
	}
	
	private V bucketInsertEntry(int bucketIndex, Entry<K, V> newEntry) {
		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		if(bucket == null) table[bucketIndex] = bucket = new LinkedList<>();
		
		Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, newEntry.key);
		
		if(existentEntry == null) {
			bucket.add(newEntry);
			size++;
			if(size > threshold) resizeTable();
			return null;
		}
		else {
			V oldValue = existentEntry.value;
			existentEntry.value = newEntry.value;
			return oldValue;
		}
	}
	
	private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		if(bucket == null) return null;
		for(Entry<K, V> entry : bucket) {
			if(entry.key.equals(key)) return entry;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void resizeTable() {
		capacity *= 2;
		threshold = (int) (capacity * maxLoadFactor);
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
		
		for(int i = 0; i < table.length; i++) {
			LinkedList<Entry<K, V>> bucket = table[i];
			if(bucket != null) {
				for(Entry<K, V> entry : bucket) {
					int bucketIndex  = entry.hash;
					LinkedList<Entry<K, V>> newBucket = newTable[i];
					if(newBucket == null) newTable[bucketIndex] = newBucket = new LinkedList<>();
					newBucket.add(entry);
				}
				table[i].clear();
				table[i] = null;
			}
		}
		table = newTable;
	}
	
	public List<K> keys() {
		List<K> keys = new ArrayList<>();
		for(LinkedList<Entry<K, V>> bucket : table) {
			if(bucket != null) {
				for(Entry<K, V> entry : bucket) keys.add(entry.key);
			}
		}
		return keys;
	}
	
	public List<V> values() {
		List<V> values = new ArrayList<>();
		for(LinkedList<Entry<K, V>> bucket : table) {
			if(bucket != null) {
				for(Entry<K, V> entry : bucket) values.add(entry.value);
			}
		}
		return values;
	}
}
