package practice2;
import java.util.*;


public class Practice {
	
	public static int seggregate(int[] array) {
		int i = 0;
		for(int j = 0; j < array.length; j++) {
			if(array[j] <= 0) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
			}
		}
		return i;
	}
	
	public static int findMissingNumber(int[] array) {
		for(int j = 0; j < array.length; j++) {
			if((Math.abs(array[j]) - 1) < array.length  && (Math.abs(array[j]) -1) >= 0) {
				array[Math.abs(array[j]) - 1] = - array[Math.abs(array[j])];
			}
		}
		for(int num: array) {
			System.out.println(num);
		}
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] > 0) {
				return i + 1;
			}
		}
		return array.length + 1;
	}
	
	public static int findMisiingPosNumber(int[] array) {
		int positiveNumBeginAt = seggregate(array);
		int[] slicedArray = Arrays.copyOfRange(array, positiveNumBeginAt, array.length);
		return findMissingNumber(slicedArray);
	}
	
	public static <T> void main(String[] args) {
		int[] input = {3, 6, -2, -1,-3, 0, 4, 1};
		System.out.println(findMisiingPosNumber(input));
	}
}
