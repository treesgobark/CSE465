package objects;

import java.util.ArrayList;

public class Block {
	private String blockString;
	private ArrayList<Statement> contents = new ArrayList<Statement>();
	
	public Block(String blockString) {
		this.blockString = blockString;
		if(!blockString.contains("{")) contents.add(new Statement(blockString));
	}
	
	public ArrayList<Statement> getStatements() {
		return contents;
	}
	
	public String toString() {
		return contents.toString();
	}
}
