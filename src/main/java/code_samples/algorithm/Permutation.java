package code_samples.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {
	private final List<Integer> list;

	public Permutation(final List<Integer> list) {
		this.list = list;
	}

	void permute(final int start) {
		for (int current = start; current < this.list.size(); current++) {
			Collections.swap(this.list, current, start);
			this.permute(start + 1);
			Collections.swap(this.list, start, current);
		}
		if (start == this.list.size() - 1)
			System.out.println(Arrays.toString(this.list.toArray()));
	}
}
