import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class controls the main method and executes I/O. 
 * 
 * @author danat
 */
public class Lab4 {
	/**
	 * This is the main method.
	 * The method reads through the file and makes an array from the integers listed.
	 * It then writes the output of each of the six sorts and their run time to the output file.
	 * 
	 * @param args The command line arguments are the paths to the input and output files.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String fileIn = args[0];
		File f = new File( fileIn );

		if( !f.exists() ){ //Incorrect path or nonexistent file will exit program.
			System.out.println( "File does not exist. Terminating program." );
			System.exit( 1 );
		}

		else {
			System.out.println( "Reading file." );
		}	

		String fileOut = args[1];
		File out = new File(fileOut);
		FileWriter wout = new FileWriter(out);

		Path inputFile = Paths.get(args[0]); //This makes a path object for the input file
		int totalLines = (int) Files.lines(inputFile).count(); //This goes through the input file and counts the lines to declare the size of the array.

		Scanner input = new Scanner( f );

		int i = 0;
		int[] arr = new int[totalLines]; //initialize the array with the number of lines in the input file

		try {
			while(input.hasNextLine()) {
				String line = input.nextLine();
				arr[i] = Integer.parseInt(line); //parse strings from input as integers
				i++;
			}
		}
		catch(NumberFormatException e) { //found this exception from running a few files with a blank space in the file
			System.out.println("Input file has extra newline characters. Please reformat and try again.");
		}
		
		int[] arrCopy = Arrays.copyOfRange(arr, 0, arr.length); //Ended up having to make a new copy of the array every time a new sort was performed
		
		wout.write("Heapsort:\r\n");
		Heap heapArr = new Heap(arr);
		long heapTime1 = System.currentTimeMillis(); //Each of the sorts calls the current time in milliseconds before calling the sort
		int[] sortedHeap = heapArr.sort();
		for(int j = 0; j < sortedHeap.length; j++) {
			wout.write(sortedHeap[j] + " ");
		}
		long heapTime2 = System.currentTimeMillis(); //The time is then called again right after the sort 
		long heapTimeTotal = heapTime2 - heapTime1; //The total time to run is calculate and printed after each sort
		wout.write("\r\nTime to run heapsort: " + heapTimeTotal + " ms");
		wout.write("\r\n\r\n");

		int[] shellSeq1 = new int[] {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524}; //The k values for each shell sort
		int[] shellSeq2 = new int[] {1, 5, 17, 53, 149, 373, 1123, 3371, 10111, 30341};
		int[] shellSeq3 = new int[] {1, 10, 30, 60, 120, 360, 1080, 3240, 9720, 29160};
		int[] shellSeq4 = new int[] {1, 2, 25, 50, 200, 500, 1000, 5000, 10000, 30000}; //The fourth set of values I came up with
		
		wout.write("Shell Sort 1:\r\n");
		arr = arrCopy.clone();
		Shell shell1 = new Shell(arr, shellSeq1);
		long shell1Time1 = System.currentTimeMillis();
		int[] sortedShell1 = shell1.ShellSort();
		for(int j = 0; j < sortedShell1.length; j++) {
			if (sortedShell1[j] == 0) {
				continue;
			}
			wout.write(sortedShell1[j] + " ");
		}
		
		wout.write("\r\n");
		long shell1Time2 = System.currentTimeMillis();
		long shell1TimeTotal = shell1Time2 - shell1Time1;
		wout.write("Time to run shellsort using Knuth's sequence (1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524): " + shell1TimeTotal + " ms");
		
		wout.write("\r\n\r\nShell Sort 2:\r\n");
		arr = arrCopy.clone();
		Shell shell2 = new Shell(arr, shellSeq2);
		long shell2Time1 = System.currentTimeMillis();
		int[] sortedShell2 = shell2.ShellSort();
		for(int j = 0; j < sortedShell2.length; j++) {
			if (sortedShell2[j] == 0) {
				continue;
			}
			wout.write(sortedShell2[j] + " ");
		}
		wout.write("\r\n");

		long shell2Time2 = System.currentTimeMillis();
		long shell2TimeTotal = shell2Time2 - shell2Time1;
		wout.write("Time to run shellsort using second sequence (1, 5, 17, 53, 149, 373, 1123, 3371, 10111, 30341): " + shell2TimeTotal + " ms");
		
		wout.write("\r\n\r\nShell Sort 3:\r\n");
		arr = arrCopy.clone();
		Shell shell3 = new Shell(arr, shellSeq3);
		long shell3Time1 = System.currentTimeMillis();
		int[] sortedShell3 = shell3.ShellSort();
		for(int j = 0; j < sortedShell3.length; j++) {
			if (sortedShell3[j] == 0) {
				continue;
			}
			wout.write(sortedShell3[j] + " ");
		}
		wout.write("\r\n");

		long shell3Time2 = System.currentTimeMillis();
		long shell3TimeTotal = shell3Time2 - shell3Time1;
		wout.write("Time to run shellsort using third sequence (1, 10, 30, 60, 120, 360, 1080, 3240, 9720, 29160): " + shell3TimeTotal + " ms");
		
		wout.write("\r\n\r\nShell Sort 4:\r\n");
		arr = arrCopy.clone();
		Shell shell4 = new Shell(arr, shellSeq4);
		long shell4Time1 = System.currentTimeMillis();
		int[] sortedShell4 = shell4.ShellSort();
		for(int j = 0; j < sortedShell4.length; j++) {
			if (sortedShell4[j] == 0) {
				continue;
			}
			wout.write(sortedShell4[j] + " ");
		}
		wout.write("\r\n");

		long shell4Time2 = System.currentTimeMillis();
		long shell4TimeTotal = shell4Time2 - shell4Time1;
		wout.write("Time to run shellsort using fourth sequence (1, 2, 25, 50, 200, 500, 1000, 5000, 10000, 30000): " + shell4TimeTotal + " ms");
		
		wout.write("\r\n\r\nInsertion Sort:\r\n"); //This also does a simple insertion sort for each of the input files
		arr = arrCopy.clone();
		int[] insertionSeq = new int[] {1}; //Insertion sort just calls shell sort but with the only skip value being 1
		Shell simpleInsertion = new Shell(arr, insertionSeq);
		long insertionTime1 = System.currentTimeMillis();
		int[] sortedInsertion = simpleInsertion.InsertionSort(arr); //goes through shell sort but only uses increment of 1
		for(int j = 0; j < sortedInsertion.length; j++) {
			wout.write(sortedInsertion[j] + " ");
		}
		wout.write("\r\n");
		
		long insertionTime2 = System.currentTimeMillis();
		long insertionTimeTotal = insertionTime2 - insertionTime1;
		wout.write("Time to run simple insertion sort: " + insertionTimeTotal + " ms");
		wout.write("\r\n");

		input.close(); //Close out scanner and writer
		wout.close();
	}
}
