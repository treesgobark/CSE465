package objects;

import java.util.ArrayList;

import utilities.CodeReader;

public class Block {
	private String blockString;
	private ArrayList<Statement> contents = new ArrayList<Statement>();

	public Block(String blockString) {
		CodeReader reader;
		this.blockString = blockString;
		contents.add(new Statement(blockString));
	}

	public ArrayList<Statement> getStatements() {
		return contents;
	}

	public String toString() {
		return "{" + contents.toString().substring(2, contents.toString().length()-2) + "}";
	}
	
	public String getBlockString() {
		return blockString;
	}
}
