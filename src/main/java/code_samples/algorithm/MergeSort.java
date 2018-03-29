package code_samples.algorithm;

public class MergeSort {
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