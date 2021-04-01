package practice2;
import java.util.*;


public class Practice {
	
	public static String addStar(String str, int n) {
		for(int i = str.length(); i < n; i++) {
			str += "*";
		}
		return str;
	}
	
	public static List<String> preHexList(String str, int n, int startPos) {
		String newString = addStar(str, n);
		List<String> list = new ArrayList<>();
		for(int i = startPos - 1; i < newString.length(); i++) {
			list.add(newString.substring(i, i+1));
		}
		for(int j = 0; j < startPos - 1; j++) {
			list.add(newString.substring(j, j+1));
		}
		List<String> newList = pad(list);
		return newList;
	}
	
	public static List<String> padMiddle(List<String> list) {
		int midStart = list.size() / 6; // index at which middle section starts on left side.
		int midEnd = 2 * midStart; // index at which middle section ends on left side.
		String midSpace = "  ";
		String frontSpace = " ";
		
		int m = (midStart * 5);// index at which middle section starts on right side.
		int n = list.size(); 
		
		for(int i = 0; i <= midStart; i++) { // we move from up to middle section start.
			if(i >= midStart && i <= midEnd) { // middle section range
				list.set(i, list.get(i) + frontSpace + "  "); // constant space in the middle section.
			}
			else {
				list.set(i, list.get(i) + midSpace); // creating constant space after the element on the left.
				if(n > m && n < list.size()) {
					list.set(n, frontSpace + list.get(n));// creating space before the element on the right.
					frontSpace += "    "; // increment the space if its not in the middle section(right side).
				}
			}
			n--;
		}
		
		midSpace = "  ";
		frontSpace = " ";
		int r = (midEnd * 2); // index at which middle section ends on right side.
		int s = (list.size()/2);
		for(int j = list.size() / 2; j > midStart; j--) { // we move from below to below middle section start.
			if(j >= midStart && j <= midEnd) {
				list.set(j, list.get(j) + frontSpace + "  "); // constant space for the middle section.
			}
			else {
				list.set(j, list.get(j) + midSpace); // constant space after elements in the left.
				if(s < r && s > list.size()/2) {
					list.set(s, frontSpace + list.get(s)); // creating space before the element on the right.
					frontSpace += "    "; // incrementing space if its not in the middle section.
				}
			}
			s++;
		}
		return list;
	}
	
	public static List<String> pad(List<String> list) {
		int midStart = (list.size() / 6);
		int midEnd = 2 * midStart;
		String frontSpace = "";
		String constSpace = "";
		
		for(int i = midStart; i >= 0; i--) {
			if(i >= midStart && i <= midEnd) {
				list.set(i, constSpace + list.get(i));	
			}
			else {
				frontSpace += "  ";
				list.set(i, frontSpace + list.get(i));
				
			}
		}
		
		frontSpace = "";
		for(int j = midStart + 1; j < (list.size() / 2) + 1; j++) {
			if(j >= midStart && j <= midEnd) {
				list.set(j, constSpace + list.get(j));
			}
			else {
				frontSpace += "  ";
				list.set(j, frontSpace + list.get(j));
				
			}
		}
		List<String> paddedList = padMiddle(list);
		return paddedList;
	}
	
	public static List<String> hexList(List<String> lst) {
		int i = 0;
		int j = lst.size() - 1;
		List<String> hexLst = new ArrayList<>();
		while(true) {
			if(i == 0) {
				hexLst.add(lst.get(i));
				j++;
			}
			else if(j == lst.size()/2) {
				hexLst.add(lst.get(j));
				break;
			}
			else {
				hexLst.add(lst.get(i) + lst.get(j));
			}
			i++;
			j--;
		}
		return hexLst;
	}
	
	
	public static void printHexagon(String str, int n, int startPos) {
		if(n < 1 || n > 60) {
			throw new IllegalArgumentException("n should be btwn [1,60]");
		}
		if(n % 6 != 0) {
			throw new IllegalArgumentException("n should be multiple of 6");
		}
		List<String> preHexLst = preHexList(str, n, startPos);
		List<String> finalHexList = hexList(preHexLst);
		for(String strng: finalHexList) {
			System.out.println(strng);
		}
	}
	
	public static void main(String[] args) {
		String str = "abcdefghijklmnopqrstuvwxyz";
		int n = 60;
		int startPos = 4;
		printHexagon(str, n, startPos);
	}
}
