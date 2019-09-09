package objects;

import java.util.ArrayList;

import utilities.Equals;
import utilities.For;
import utilities.Key;
import utilities.Print;

public class Statement {
	ArrayList<Object> instructions;

	public Statement(String statementString) {
		instructions = new ArrayList<Object>();
		String prog = "";
		boolean isString = false;
		for(int i = 0; i < statementString.length(); i++ ) {
			char temp = statementString.charAt(i);
			if(temp == '\"') isString = !isString;
			if(temp == ' ' && !isString) {
				instructions.add(convert(prog));
				prog = "";
			} else prog += temp;
		}
	}

	private Object convert(String prog) {
		if(prog.contentEquals("PRINT")) return new Print();
		if(prog.contentEquals("FOR")) return new For();
		if(prog.contentEquals("TRUE")) return new Boolean(true);
		if(prog.contentEquals("FALSE")) return new Boolean(false);
		if(prog.contentEquals("=")) return new Equals(0);
		if(prog.contentEquals("+=")) return new Equals(1);
		if(prog.contentEquals("*=")) return new Equals(2);
		if(prog.contentEquals("&=")) return new Equals(3);
		if(isNumeric(prog)) return Integer.parseInt(prog);
		if(prog.contains("\"")) return prog.substring(1, prog.length()-1);
		return new Key(prog);
	}
	
	private boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public String toString() {
		return instructions.toString();
	}
	
}
