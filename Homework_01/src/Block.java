

import java.util.ArrayList;

public class Block {
	private String blockString;
	public ArrayList<Object> instructions = new ArrayList<Object>();

	public Block(ArrayList<Object> instructions) {
		this.instructions = instructions;
	}
	
	public Block(String blockString) {
		this.blockString = blockString;
//		System.out.println(blockString);
		String prog = "";
		boolean isString = false;
		int blockCount = 0;
		for (int i = 0; i < blockString.length(); i++) {
			char temp = blockString.charAt(i);
			if (temp == '\"')
				isString = !isString;
			if (temp == '{')
				blockCount++;
			if (temp == '}')
				blockCount--;
			if ((temp == ' ' || temp == '}') && !isString && blockCount == 0) {
//					System.out.println(prog);
				if (temp == '}') {
					prog += temp;
					i++;
				}
				convert(prog);
				prog = "";
			} else
				prog += temp;
		}
	}
	
	private void convert(String prog) {
//		System.out.println("prog=" + prog);
		if (prog.contentEquals("PRINT")) {
			instructions.add(new Print());
		}
		else if (prog.contentEquals("FOR")) {
			instructions.add(new For());
		}
		else if (prog.contentEquals("TRUE")) {
			instructions.add(new Boolean(true));
		}
		else if (prog.contentEquals("FALSE")) {
			instructions.add(new Boolean(false));
		}
		else if (prog.contentEquals("=")) {
			instructions.add(new Equals(0));
		}
		else if (prog.contentEquals("+=")) {
			instructions.add(new Equals(1));
		}
		else if (prog.contentEquals("*=")) {
			instructions.add(new Equals(2));
		}
		else if (prog.contentEquals("&=")) {
			instructions.add(new Equals(3));
		}
		else if (prog.contentEquals(";")) {
			instructions.add(new Stop());
		}
		else if (isNumeric(prog)) {
			instructions.add(Integer.parseInt(prog));
		}
		else if (prog.contains("{") && prog.contains("}")) {
			CodeReader r = new CodeReader(prog);
			ArrayList<Object> newInstructions = new ArrayList<Object>();
			while (r.hasNextStatement()) {
				Block temp = r.nextStatement();
//				System.out.println("block=" + temp);
				if(!temp.blockString.contentEquals(""))
					newInstructions.add(temp);
			}
			instructions.add(new Block(newInstructions));
		}
		else if (prog.contains("\"")) {
			instructions.add(prog.substring(1, prog.length() - 1));
		}
		else {
			instructions.add(new Key(prog));
		}
	}
	
	private boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}

	public String toString() {
		return "{" + instructions.toString().substring(1, instructions.toString().length()-1) + "}";
//		return instructions.toString();
	}
	
	public String getBlockString() {
		return blockString;
	}
}
