package Example;

import Backtracking.DynamicStack;

/**
 * Created by diegobaldassare on 3/30/16.
 */
public class PuzzleStack {
    private DynamicStack<PuzzlePosition> stack;

    public PuzzleStack() {
        this.stack = new DynamicStack<>();
    }

    public void pushStack(PuzzlePosition position) {
        this.stack.push(position);
    }

    public PuzzlePosition peekStack() {
        if (stack.size() > 0) {
            return stack.peek();
        } else {
            return null;
        }
    }

    public PuzzlePosition popStack() {
        if (stack.size() > 0) {
            return stack.pop();
        } else {
            return null;
        }
    }
}
