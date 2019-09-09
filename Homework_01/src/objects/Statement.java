package objects;

import java.util.ArrayList;

import utilities.CodeReader;
import utilities.Equals;
import utilities.For;
import utilities.Key;
import utilities.Print;
import utilities.Stop;

public class Statement {
	ArrayList<Object> instructions;

	public Statement(String statementString) {
//		System.out.println("string=" + statementString);
		instructions = new ArrayList<Object>();
		String prog = "";
		boolean isString = false;
		int blockCount = 0;
		for (int i = 0; i < statementString.length(); i++) {
			char temp = statementString.charAt(i);
			if (temp == '\"')
				isString = !isString;
			if (temp == '{')
				blockCount++;
			if (temp == '}')
				blockCount--;
			if ((temp == ' ' || temp == '}') && !isString && blockCount == 0) {
//				System.out.println(prog);
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
//			System.out.println("prog=" + prog);
			CodeReader reader = new CodeReader(prog);
			while (reader.hasNextStatement()) {
				Block temp = reader.nextStatement();
				if (!temp.getBlockString().contentEquals("")) {
					System.out.println("block=" + temp);
					instructions.add(temp);
				}
			}
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
		return instructions.toString();
	}

}
