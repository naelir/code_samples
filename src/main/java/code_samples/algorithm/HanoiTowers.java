package code_samples.algorithm;

public class HanoiTowers {
	public class Iterative {
		private final int discCount;

		public Iterative(final int discCount) {
			this.discCount = discCount;
		}

		public void execute() {
			// NUMBER OF ITERATIONS = 2^n - 1
			final int limit = (int) Math.pow(2, this.discCount) - 1;
			for (int i = 0; i < limit; i++) {
				// DISK TO BE MOVED
				final int disk = this.getNextDisc(i);
				// SOURCE PEG
				final int source = (this.getMovementCount(i, disk) * this.getDirection(disk, this.discCount)) % 3;
				// DESTINATION PEG
				final int destination = (source + this.getDirection(disk, this.discCount)) % 3;
				this.printMove(disk, source, destination);
			}
		}

		private int getDirection(final int disc, final int discCount) {
			return 2 - ((discCount + disc) % 2);
		}

		private int getMovementCount(int iteration, int disc) {
			while (disc-- != 0)
				iteration /= 2;
			return (iteration + 1) / 2;
		}

		private int getNextDisc(final int iteration) {
			// SINCE FOR STARTS WITH 0, ADDING 1
			int nextDisc, x = iteration + 1;
			for (nextDisc = 0; (x % 2) == 0; nextDisc++)
				x /= 2;
			return nextDisc;
		}

		private void printMove(final int disk, final int source, final int destination) {
			System.out
				.println("Move Disk " + (disk + 1) + " from Tower " + (source + 1) + " to Tower " + (destination + 1));
		}
	}

	public static class Recursive {
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
}
