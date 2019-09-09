package drivers;

import java.io.File;

import utilities.CodeReader;

public class Main {

	public static void main(String[] args) {
		CodeReader fileReader = new CodeReader(new File ("programs/prog1" + ".txt"));
		while(fileReader.hasNextStatement()) {
			System.out.print(fileReader.nextStatement() + "\n");
		}
//		System.out.println("-------------");
//		CodeReader blockReader = new CodeReader("{ ur = mom ; big += gay ; }");
//		while(blockReader.hasNextStatement()) {
//			System.out.print(blockReader.nextStatement() + "\n");
//		}
	}

}
