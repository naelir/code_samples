package code_samples.other;

import java.util.Stack;

public class ParenthesisMatcher {

	public boolean match(String value) {
		final Stack<Character> stack = new Stack<>();
		for (int i = 0; i < value.length(); i++) {
			final char symbol = value.charAt(i);
			if ((symbol == '[') || (symbol == '(') || (symbol == '{'))
				stack.push(symbol);
			else if (symbol == ']') {
				if (stack.isEmpty() || (stack.pop() != '['))
					return false;
			} else if (symbol == ')') {
				if (stack.isEmpty() || (stack.pop() != '('))
					return false;
			} else if (symbol == '}')
				if (stack.isEmpty() || (stack.pop() != '{'))
					return false;
		}
		return stack.isEmpty();
	}

}
