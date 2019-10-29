

public class Evaluator {
	public static void eval(Block block) {
//		System.out.println(block);
		for (int i = 0; i < block.instructions.size(); i++) {
			Object current = block.instructions.get(i);
			if (current instanceof Key) {
				
			}
			else if (current instanceof Equals) {
				Equals temp = (Equals) current;
				String key = ((Key) block.instructions.get(i-1)).key;
				Object value = block.instructions.get(i+1);
				if (value instanceof Key)
					value = Operations.symbolTable.get(((Key) value).key).value;
				temp.setKey(key);
				temp.setValue(value);
				temp.evaluate();
			}
			else if (current instanceof Integer) {
				
			}
			else if (current instanceof Print) {
				Print temp = (Print) current;
				String key = ((Key) block.instructions.get(i+1)).key;
				temp.setKey(key);
				temp.evaluate();
			}
			else if (current instanceof For) {
				For temp = (For) current;
				int iterations = (Integer) block.instructions.get(i+1);
				Block forLoopStatements = (Block) block.instructions.get(i+2);
				temp.setIterations(iterations);
				temp.setBlock(forLoopStatements);
				temp.evaluate();
			}
			else if (current instanceof Block) {
				Block temp = (Block) current;
				Evaluator.eval(temp);
			}
		}
	}
}
