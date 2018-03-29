package code_samples.algorithm;

public class Combination {
	private final int[] array;

	public Combination(final int[] array) {
		this.array = array;
	}

	void iterative(final int combinationLength) {
		final int length = this.array.length;
		final int[] result = new int[length];
		for (int i = 0; i < length; i++)
			result[i] = i;
		while (result[combinationLength - 1] < length) {
			for (int i = 0; i < combinationLength; i++)
				System.out.print(this.array[result[i]]);
			System.out.println();
			int last = combinationLength - 1;
			while ((last != 0) && (result[last] == ((result.length - combinationLength) + last)))
				last--;
			result[last]++;
			for (int i = last + 1; i < combinationLength; i++)
				result[i] = result[i - 1] + 1;
		}
	}
}
