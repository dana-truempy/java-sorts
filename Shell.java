import java.util.Arrays;

/**
 * The shell class makes a new Shell object, which can then use the shellSort method.
 * It contains a simple insertion sort method as well as a method to make the subfiles.
 * 
 * @author danat
 *
 */
public class Shell {
	private int[] arr, seq;
	private int maxSeq;

	/**
	 * This is the class constructor for Shell. It calculates from the size of the array which of the values of k should be used.
	 * 
	 * @param arr The array of values to be sorted 
	 * @param seq The list of values for creating subfiles
	 */
	public Shell(int[] arr, int[] seq) {
		this.arr = arr;

		int i = 0;
		while(i < seq.length - 1 && seq[i] < arr.length) {
			i++;
		}
		this.maxSeq = i < 2 ? i : i - 2; //if the value used for the sort is position 2 or less in the seq array then the algorithm uses the farthest position reached
		this.seq = seq;
	}

	/**
	 * This is a method for a simple insertion sort.
	 * 
	 * @param arr The array to be sorted
	 * @return Returns the sorted subfile
	 */
	public int[] InsertionSort(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int j = i;
			while(j > 0 && arr[j] < arr[j-1]) {
				int current = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = current;
				j--;
			}
		}
		return arr;
	}

	/**
	 * This separates a file into subfiles and performs shellsort on them, then puts the sorted subfiles back in their original locations.
	 * 
	 * @return Returns the sorted array
	 */
	public int[] ShellSort() {
		for(int i = maxSeq; i > 0; i--) {
			int[][] subArrs = new int[seq[i]][(int) Math.ceil(1.0 * arr.length/seq[i])]; //rounds up when dividing up the subfiles
				//new 2D array of subfiles of length arr.length/sequence
			int subArrPos = 0;
			for(int j = 0; j < arr.length; j++) {
				if( j != 0 && j % subArrs.length == 0 ) { //adds elements to subfiles
					subArrPos++;
				}
				subArrs[j % subArrs.length][subArrPos] = arr[j];
			}
			for(int subArr = 0; subArr < subArrs.length; subArr++) { //insertion sort on each subfile
				subArrs[subArr] = Arrays.copyOfRange(subArrs[subArr], 0, subArrPos+1);
				subArrs[subArr] = this.InsertionSort(subArrs[subArr]);
			}
			subArrPos = 0; //puts subfiles back into larger array in order
			for(int j = 0; j < arr.length; j++) {
				if( j != 0 && j % subArrs.length == 0 ) {
					subArrPos++;
				}
				arr[j] = subArrs[j % subArrs.length][subArrPos];
			}
		}
		
		return this.InsertionSort(arr); //the last run should be a simple insertion sort on the file
	}
}
