package code_samples;

public class Algorithms {
	public static class BinarySearch {
		public int search(final int[] array, final int left, final int right, final int toFind) {
			if (right >= left) {
				final int middle = left + (right - left) / 2;
				// If the element is present at the middle itself
				if (array[middle] == toFind)
					return middle;
				// If element is smaller than mid, then it can only be present in left subarray
				if (array[middle] > toFind)
					return this.search(array, left, middle - 1, toFind);
				// Else the element can only be present in right subarray
				return this.search(array, middle + 1, right, toFind);
			}
			// We reach here when element is not present in array
			return -1;
		}
	}

	public static class BubbleSort {
		public void sort(final int[] array) {
			final int length = array.length;
			int temp = 0;
			for (int i = 0; i < length; i++)
				for (int j = 1; j < (length - i); j++)
					if (array[j - 1] > array[j]) {
						temp = array[j - 1];
						array[j - 1] = array[j];
						array[j] = temp;
					}
		}
	}

	public static class HanoiTowers {
		public void move(final int discs, final char from, final char inter, final char to) {
			if (discs == 1)
				System.out.println("Disk 1 from " + from + " to " + to);
			else {
				this.move(discs - 1, from, to, inter);
				System.out.println("Disk " + discs + " from " + from + " to " + to);
				this.move(discs - 1, inter, from, to);
			}
		}
	}

	public static class Mergesort {
		private void merge(final int low, final int middle, final int high, final int[] values,
				final int[] helper) {
			// Copy both parts into the helper array
			for (int i = low; i <= high; i++)
				helper[i] = values[i];
			int i = low;
			int j = middle + 1;
			int k = low;
			// Copy the smallest values from either the left or the right side back
			// to the original array
			while (i <= middle && j <= high) {
				if (helper[i] <= helper[j]) {
					values[k] = helper[i];
					i++;
				} else {
					values[k] = helper[j];
					j++;
				}
				k++;
			}
			// Copy the rest of the left side of the array into the target array
			while (i <= middle) {
				values[k] = helper[i];
				k++;
				i++;
			}
			// Since we are sorting in-place any leftover elements from the right side
			// are already at the right position.
		}

		private void mergesort(final int low, final int high, final int[] values,
				final int[] helper) {
			// check if low is smaller than high, if not then the array is sorted
			if (low < high) {
				// Get the index of the element which is in the middle
				final int middle = low + (high - low) / 2;
				// Sort the left side of the array
				this.mergesort(low, middle, values, helper);
				// Sort the right side of the array
				this.mergesort(middle + 1, high, values, helper);
				// Combine them both
				this.merge(low, middle, high, values, helper);
			}
		}

		public void sort(final int[] values) {
			final int length = values.length;
			final int[] helper = new int[length];
			this.mergesort(0, length - 1, values, helper);
		}
	}

	public static class QuickSort {
		/*
		 * This function takes last element as pivot, places the pivot element at its
		 * correct position in sorted array, and places all smaller (smaller than pivot)
		 * to left of pivot and all greater elements to right of pivot
		 */
		private int partition(final int array[], final int low, final int high) {
			final int pivot = array[high];
			int i = (low - 1); // index of smaller element
			for (int j = low; j < high; j++)
				if (array[j] <= pivot) {
					i++;
					// swap arr[i] and arr[j]
					final int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			// swap arr[i+1] and arr[high] (or pivot)
			final int temp = array[i + 1];
			array[i + 1] = array[high];
			array[high] = temp;
			return i + 1;
		}

		/*
		 * The main function that implements QuickSort() arr[] --> Array to be sorted,
		 * low --> Starting index, high --> Ending index
		 */
		public void sort(final int array[], final int low, final int high) {
			if (low < high) {
				/*
				 * pi is partitioning index, arr[pi] is now at right place
				 */
				final int partitionIndex = this.partition(array, low, high);
				// Recursively sort elements before
				// partition and after partition
				this.sort(array, low, partitionIndex - 1);
				this.sort(array, partitionIndex + 1, high);
			}
		}
	}
}
