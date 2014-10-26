// Alex von Hafften
// Fall Semester 2014
// As of October 26, 2014

import java.util.Scanner;

/**
 * This class holds implementations of a number of different sorting algorithms.
 * The comparison sorts included are Insertion Sort, Bubble Sort, Merge Sort,
 * Heap Sort, Quick Sort. It also contains methods for randomly generating
 * an array to sort, getting an array from the user and printing an array. This
 * class was produced using the pseudo code from Introduction to Algorithms
 * [Third Edition] by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest,
 * and Clifford Stein. Published by the MIT Press, Copyrighted in 2009.
 *
 * @author Alex von Hafften
 */
public class Sort {
	public static void main(String[] args) {
		// Sets array to be sorted.
		// int[] a = inputArray();
		// int[] a = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int[] a = randomArrayGenerator(100, 0, 100);

		// Prints unsorted array.
		print(a);

		// Sorts array.
		insertionSort(a);
		// bubbleSort(a);
		// mergeSort(a);
		// heapSort(a);
		// quickSort(a);

		// Prints sorted array.
		print(a);
	}

	// Comparison Sorts: Insertion Sort, Bubble Sort, Merge Sort, Heap Sort, and Quick Sort.

	/**
	 * Implementation of Insertion Sort.
	 *
	 * @param a
	 *            is an int array.
	 */
	public static void insertionSort(int[] a) {
		int key;
		for (int j = 1; j < a.length; j++) {
			key = a[j];
			int i = j - 1;
			while (i >= 0 && a[i] > key) {
				a[i + 1] = a[i];
				i--;
			}
			a[i + 1] = key;
		}
	}

	/**
	 * Implementation of Bubble Sort.
	 *
	 * @param a
	 *            is an int array
	 */
	public static void bubbleSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = a.length - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					int swap = a[j];
					a[j] = a[j - 1];
					a[j - 1] = swap;
				}
			}
		}
	}

	/**
	 * Implementation of Merge Sort. October 26, 2014: I couldn't get the pseudo
	 * code from Intro to Algorithm to work. Used
	 * http://www.vogella.com/tutorials
	 * /JavaAlgorithmsMergesort/article.html#mergesort.
	 *
	 * @param a
	 *            is an int array.
	 */
	public static void mergeSort(int[] a) {
		int[] helper = new int[a.length];
		mergeSort(a, helper, 0, a.length - 1);
	}

	/**
	 * Implementation of Merge Sort.
	 *
	 * @param a
	 *            is an int array.
	 * @param helper
	 *            is an int array used in merging sorted subarrays.
	 * @param low
	 *            is the index of the first element in the array or subarray.
	 * @param high
	 *            is the index of the last element in the array or subarray.
	 */
	public static void mergeSort(int[] a, int[] helper, int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			mergeSort(a, helper, low, middle);
			mergeSort(a, helper, middle + 1, high);
			merge(a, helper, low, middle, high);
		}
	}

	/**
	 * Merges two sorted subarrays in array a.
	 *
	 * @param a
	 *            is an int array.
	 * @param helper
	 *            is an int array used in merging sorted subarrays.
	 * @param low
	 *            is the index of the first element of the first subarray.
	 * @param middle
	 *            is the index of the last element of the first subarray.
	 * @param high
	 *            is the index of the last element of the second subarray.
	 */
	public static void merge(int[] a, int[] helper, int low, int middle,
			int high) {
		for (int i = low; i < helper.length; i++) {
			helper[i] = a[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				a[k] = helper[i];
				i++;
			} else {
				a[k] = helper[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			a[k] = helper[i];
			k++;
			i++;
		}
	}

	/**
	 * Implementation of Heap Sort.
	 *
	 * @param a
	 *            is an int array.
	 */
	public static void heapSort(int[] a) {
		buildMaxHeap(a);
		int size = a.length;
		for (int i = a.length - 1; i >= 0; i--) {
			int swap = a[0];
			a[0] = a[i];
			a[i] = swap;
			size--;
			maxHeapify(a, 0, size);
		}
	}

	/**
	 * Converts an int array into a max-Heap. A max-heap is a binary tree where
	 * a parent is always larger than their children. When a heap is stored as
	 * an array the root is the element at index = 0, The root's left child is
	 * at index = 1. The root's right child is at index = 2. the left most
	 * grandchild is at index = 3. The left child's right child is at index = 4.
	 * Etcetera for all items in the heap.
	 *
	 * @param a
	 *            is an int array.
	 */
	public static void buildMaxHeap(int[] a) {
		for (int i = a.length / 2; i >= 0; i--) {
			maxHeapify(a, i, a.length);
		}
	}

	/**
	 * Converts an array into a max-heap for element i.
	 *
	 * @param a
	 *            is an int array.
	 * @param i
	 *            is the index of the element being considered.
	 * @param size
	 *            is the size of the heap.
	 */
	public static void maxHeapify(int[] a, int i, int size) {
		int left = (i * 2) + 1;
		int right = (i + 1) * 2;
		int largest = i;
		if (left <= size - 1 && a[i] < a[left]) {
			largest = left;
		}
		if (right <= size - 1 && a[right] > a[largest]) {
			largest = right;
		}
		if (largest != i) {
			int swap = a[i];
			a[i] = a[largest];
			a[largest] = swap;
			maxHeapify(a, largest, size);
		}
	}

	/**
	 * Implementation of Quick Sort.
	 *
	 * @param a
	 *            is an int array.
	 */
	public static void quickSort(int[] a) {
		quickSort(a, false, 0, a.length - 1);
	}

	/**
	 * Implementation of Quick Sort.
	 *
	 * @param a
	 *            is an int array.
	 * @param randomPivot
	 *            if true, pivot is chosen at random.
	 */
	public static void quickSort(int[] a, boolean randomPivot) {
		quickSort(a, randomPivot, 0, a.length - 1);
	}

	/**
	 * Implementation of Quick Sort.
	 *
	 * @param a
	 *            is an int array.
	 * @param randomPivot
	 *            if true, pivot is chosen at random.
	 * @param p
	 *            index of the first element of subarray.
	 * @param r
	 *            index of the second element of subarray.
	 */
	public static void quickSort(int[] a, boolean randomPivot, int p, int r) {
		if (p < r) {
			int q = -1;
			if (randomPivot == true) {
				q = randomPartition(a, p, r);
			} else {
				q = partition(a, p, r);
			}
			quickSort(a, randomPivot, p, q - 1);
			quickSort(a, randomPivot, q + 1, r);
		}
	}

	/**
	 * Switches last element (a[r]) of the subarray (a[p] to a[r]) of a with a
	 * random element. Calls partition(). Note: Quick Sort works without using a
	 * random partition, but random partitions helps optimize it.
	 *
	 * @param a
	 *            is an int array.
	 * @param p
	 *            is the index of the first element of the subarray.
	 * @param r
	 *            is the index of the last element of the subarray.
	 * @return final sorted index of random element in subarray.
	 */
	public static int randomPartition(int[] a, int p, int r) {
		int x = p + (int) Math.random() * (r - p);
		int swap = a[r];
		a[r] = a[x];
		a[x] = a[swap];
		return partition(a, p, r);
	}

	/**
	 * Partitions the subarray (a[p] to a[r]) as either smaller or larger than
	 * the last element (a[r]). Element at a[r] is then moved to final sorted
	 * index.
	 *
	 * @param a
	 *            is an int array.
	 * @param p
	 *            is the index of the first element of the subarray.
	 * @param r
	 *            is the index of the last element of the subarray.
	 * @return final index of the previously last element in subarray.
	 */
	public static int partition(int[] a, int p, int r) {
		int x = a[r];
		int i = p - 1;
		int swap;
		for (int j = p; j < r; j++) {
			if (a[j] <= x) {
				i++;
				swap = a[i];
				a[i] = a[j];
				a[j] = swap;
			}
		}
		swap = a[i + 1];
		a[i + 1] = a[r];
		a[r] = swap;
		return i + 1;
	}

	// Utility methods: array from user input, array from random generator, and printer.

	/**
	 * Gets an int array from user. Maximum length is 100 elements.
	 *
	 * @return Inputted int array.
	 */
	public static int[] inputArray() {
		Scanner input = new Scanner(System.in);
		int[] a = new int[100];
		int size = 0;
		// Gets ints for user
		for (int i = 0; i < a.length; i++) {
			System.out
					.print("Enter int to add to array, otherwise press another key: ");
			if (input.hasNextInt() == true) {
				a[i] = input.nextInt();
				size++;
			} else {
				break;
			}
		}
		input.close();
		// Copies into properly sized array
		int[] result = new int[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = a[i];
		}
		return result;
	}

	/**
	 * Randomly generates an int array.
	 *
	 * @param size
	 *            is the size of the array to be generated.
	 * @param min
	 *            is the minimum value of any a[i].
	 * @param max
	 *            is the maximum value of any a[i].
	 * @return randomly generated int array.
	 */
	public static int[] randomArrayGenerator(int size, int min, int max) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = (int) (Math.random() * (max - min));
		}
		return a;
	}

	/**
	 * Prints an array. Example: "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]"
	 *
	 * @param a
	 *            is an array.
	 */
	public static void print(int[] a) {
		System.out.print("[");
		for (int i = 0; i < a.length - 1; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println(a[a.length - 1] + "]");
	}
}
