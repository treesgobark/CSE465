package drivers;

import java.io.File;

import utilities.CodeReader;

public class Main {

	public static void main(String[] args) {
		CodeReader reader = new CodeReader(new File ("programs/prog13.txt"));
		while(reader.hasNextStatement()) {
			System.out.print(reader.nextStatement() + "\n");
		}
	}

}
