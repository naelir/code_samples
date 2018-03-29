package code_samples.algorithm;

public class BubbleSort {
	private final int[] array;

	public BubbleSort(final int[] array) {
		this.array = array;
	}

	public int[] sort() {
		final int[] result = new int[this.array.length];
		System.arraycopy(this.array, 0, result, 0, this.array.length);
		final int length = result.length;
		int temp = 0;
		for (int i = 0; i < length; i++)
			for (int j = 1; j < (length - i); j++)
				if (result[j - 1] > result[j]) {
					temp = result[j - 1];
					result[j - 1] = result[j];
					result[j] = temp;
				}
		return result;
	}
}