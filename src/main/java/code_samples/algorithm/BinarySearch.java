package code_samples.algorithm;

public class BinarySearch {
	private final int[] array;

	public BinarySearch(final int[] array) {
		this.array = array;
	}

	public int search(final int toFind, final int left, final int right) {
		if (right >= left) {
			final int middle = left + (right - left) / 2;
			if (this.array[middle] == toFind)
				return middle;
			else if (this.array[middle] > toFind)
				return this.search(toFind, left, middle - 1);
			else
				return this.search(toFind, middle + 1, right);
		}
		return -1;
	}
}