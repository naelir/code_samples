package code_samples.algorithm;

public class QuickSort {
	private final int[] array;

	public QuickSort(final int array[]) {
		this.array = array;
	}

	/*
	 * This function takes last element as pivot, places the pivot element
	 * at its correct position in sorted array, and places all smaller
	 * (smaller than pivot) to left of pivot and all greater elements to
	 * right of pivot
	 */
	private int partition(final int low, final int high) {
		final int pivot = this.array[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++)
			if (this.array[j] <= pivot) {
				i++;
				// swap arr[i] and arr[j]
				final int temp = this.array[i];
				this.array[i] = this.array[j];
				this.array[j] = temp;
			}
		// swap arr[i+1] and arr[high] (or pivot)
		final int temp = this.array[i + 1];
		this.array[i + 1] = this.array[high];
		this.array[high] = temp;
		return i + 1;
	}

	/*
	 * The main function that implements QuickSort() arr[] --> Array to be
	 * sorted, low --> Starting index, high --> Ending index
	 */
	public void sort(final int low, final int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			final int partitionIndex = this.partition(low, high);
			// Recursively sort elements before
			// partition and after partition
			this.sort(low, partitionIndex - 1);
			this.sort(partitionIndex + 1, high);
		}
	}
}
