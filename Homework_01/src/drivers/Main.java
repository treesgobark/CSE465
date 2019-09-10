package drivers;

import java.io.File;
import java.util.Scanner;

import utilities.CodeReader;
import utilities.Evaluator;

public class Main {

	public static void main(String[] args) {
		boolean loop = true;
		while (loop) {
			Scanner sc = new Scanner(System.in);
			int temp = sc.nextInt();
			CodeReader fileReader = new CodeReader(new File("programs/" + args[1]));
			while (fileReader.hasNextStatement()) {
				Evaluator.eval(fileReader.nextStatement());
			}
		}
	}

}
