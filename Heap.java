/**
 * This is the Heap class, which builds a heap from a given array and returns a sorted array.
 * 
 * @author danat
 */
public class Heap {

	private int[] arr;
	private int heapBoundary;

	/**
	 * This is the class constructor for the Heap class. 
	 * It also sets a heapBoundary as the "magic partition" between the heap and the array.
	 * 
	 * @param arr The array to be sorted
	 */
	public Heap(int[] arr) {
		this.arr = arr;
		heapBoundary = arr.length;
	}
	
	/**
	 * This sort method calls insert to build up the heap and then removes each element from the heap in sorted order.
	 * 
	 * @return Returns the sorted array
	 */
	public int[] sort() {
		for(int i = 1; i < arr.length; i++) {
			insert(i);
		}

		for(int j = arr.length - 1; j >= 0; j--) {
			remove(j);
		}

		return arr;
	}

	/**
	 * This inserts elements from the array into the heap. 
	 * While the element is still larger than its parent, the element moves up the heap until it is in the right location.
	 * 
	 * @param i The element to be moved into the heap
	 */
	public void insert(int i) {
		while(largerThanParent(i)) {
			int parent = arr[(i-1)/2];
			arr[(i-1)/2] = arr[i];
			arr[i] = parent;
			i = (i-1)/2;
		}
	}

	/**
	 * This method calculates if an element is larger than its parent 
	 * 
	 * @param i The element in the array
	 * @return True if the element is larger than its parent
	 */
	private boolean largerThanParent(int i) {
		return arr[(i-1)/2] < arr[i];
	}

	/**
	 * This method removes an element from the heap and puts in into the array.
	 * This is obviously more difficult than inserting, and has several cases for which element is removed.
	 * 
	 * @param j The element in the heap to be removed.
	 */
	private void remove(int j) {
		int i = 0;
		int root = arr[i];
		arr[i] = arr[j];
		arr[j] = root;
		this.heapBoundary--;

		int caseValue = compareToChildren(i);

		while(caseValue != 0) { //case 0 is the element being larger than both of its children
			switch(caseValue) {
			case 1: { //left child is larger, switch element with this child
				int child = arr[2*i+1];
				arr[2*i+1] = arr[i];
				arr[i] = child;
				i = 2 * i + 1;
				break;
			}
			case 2: { //right child is larger, switch element with this child
				int child = arr[2*i+2];
				arr[2*i+2] = arr[i];
				arr[i] = child;
				i = 2 * i + 2;
				break;
			}
			case 3: { //both children are larger than element, left larger than right - switch with left child
				if((arr[2*i+1]) > (arr[2*i+2])) {
					int child = arr[2*i+1];
					arr[2*i+1] = arr[i];
					arr[i] = child;
					i = 2 * i + 1;
				}

				else { //both children are larger than element, right larger than left - switch with right child
					int child = arr[2*i+2];
					arr[2*i+2] = arr[i];
					arr[i] = child;
					i = 2 * i + 2;
				}
				break;
			}
			}
			caseValue = compareToChildren(i);
		}
	}

	/**
	 * This methods compares an element to its children in the heap to determine if it is in the right place.
	 * 
	 * @param i The element from the heap that is being compared
	 * @return caseValue which of the children (if any) is larger
	 */
	private int compareToChildren(int i) {
		int caseValue = 0;
		if((2*i+1 < this.heapBoundary) && arr[i] < arr[2*i+1]) { //left is larger (+1 to caseValue)
			caseValue += 1;
		}
		if((2*i+2 < this.heapBoundary) && arr[i] < arr[2*i+2]) { //right is larger (+2 to caseValue)
			caseValue += 2;
		}
		return caseValue;
	}
}

