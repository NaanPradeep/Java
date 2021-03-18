package practice2;
import java.util.*;

public class Trie {
	
	private final char rootChar = '\0';
	private TrieNode root = new TrieNode(rootChar);
	private static class TrieNode {
		char ch;
		int count = 0;
		boolean isWordEnding = false;
		Map<Character, TrieNode> children = new HashMap<>();
		
		public TrieNode(char ch) {
			this.ch = ch;
		}
		
		public void addChild(char c, TrieNode node) {
			children.put(c, node);
		}
	}
	
	private boolean insert(String key, int noOfInserts) {
		if(key == null) 
			throw new IllegalArgumentException("Null not permitted in trie");
		
		TrieNode node = root;
		boolean isPrefix = false;
		
		for(int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			TrieNode childNode = node.children.get(ch);
			if(childNode == null) {
				childNode = new TrieNode(ch);
				node.addChild(ch, childNode);
			} else {
				if(childNode.isWordEnding) isPrefix = true;
			}
			node = childNode;
			node.count += noOfInserts;
		}
		if(node != root) node.isWordEnding = true;
		return isPrefix;
	}
	
	public boolean insert(String key) {
		return insert(key, 1);
	}
	
	private boolean delete(String key, int noOfDeletion) {
		if(!contains(key)) return false;
		
		TrieNode curNode = root;
		
		for(int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			TrieNode nextNode = curNode.children.get(ch);
			nextNode.count -= noOfDeletion;
			
			if(nextNode.count == 0) {
				nextNode.children.remove(ch);
				nextNode.children = null;
				nextNode = null;
				return true;
			}
			curNode = nextNode;
		}
		return true;
	}
	
	public boolean delete(String key) {
		return delete(key, 1);
	}
	
	public int _contain(String key) {
		TrieNode node = root;
		
		for(int i = 0; i < key.length(); i++) {
			char ch = key.charAt(i);
			if(node == null) return 0;
			node = node.children.get(ch);
		}
		
		if(node != null) return node.count;
		return 0;
	}
	
	public boolean contains(String key) {
		return _contain(key) != 0;
	}
}
