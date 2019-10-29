

import java.util.ArrayList;

public class For {
	private int iterations;
	private Block block;

	public For() {
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	public void evaluate() {
		for (int i = 0; i < iterations-1; i++) {
			Evaluator.eval(block);
		}
	}
	
	public String toString() {
		return "FOR";
	}
}
