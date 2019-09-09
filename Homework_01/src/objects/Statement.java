package objects;

import java.util.ArrayList;

public class Statement {
	private String statementString;
	private ArrayList<Object> instructions = new ArrayList<Object>();
	
	public Statement(String statementString) {
		this.statementString = statementString;
	}
	
	public String toString() {
		return statementString;
	}
}
