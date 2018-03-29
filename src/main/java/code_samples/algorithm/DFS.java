package code_samples.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFS {
	public void iterative(final Node node) {
		final Stack<Node> stack = new Stack<Node>();
		stack.add(node);
		node.setVisited(true);
		while (!stack.isEmpty()) {
			final Node element = stack.pop();
			final Set<Node> neighbours = element.getNeighbours();
			for (final Node neighbour : neighbours)
				if (!neighbour.isVisited()) {
					stack.add(neighbour);
					neighbour.setVisited(true);
				}
		}
	}

	public void recursive(final Node node) {
		final Set<Node> neighbours = node.getNeighbours();
		node.setVisited(true);
		for (final Node neighbour : neighbours)
			if (!neighbour.isVisited())
				this.recursive(neighbour);
	}

	static class Node {
		private final int data;

		private boolean isVisited;

		private final Set<Node> neighbours;

		public Node(final int data) {
			this.data = data;
			this.neighbours = new HashSet<>();
			this.isVisited = false;
		}

		public void addNeighbour(final Node neighbourNode) {
			this.neighbours.add(neighbourNode);
		}

		public void addNeighbours(final List<Node> neighbours) {
			this.neighbours.addAll(neighbours);
		}

		public int getData() {
			return this.data;
		}

		public Set<Node> getNeighbours() {
			return this.neighbours;
		}

		public boolean isVisited() {
			return this.isVisited;
		}

		public void setVisited(final boolean isVisited) {
			this.isVisited = isVisited;
		}
	}
}
