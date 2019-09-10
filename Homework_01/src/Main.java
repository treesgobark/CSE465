

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		if (args.length > 0) {
			CodeReader fileReader = new CodeReader(new File("programs/" + args[0]));
			try {
				while (fileReader.hasNextStatement()) {
					Evaluator.eval(fileReader.nextStatement());
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		} else {
			final int PROGRAM = 1;
			final int TRIALS = 10000;
			double timeL = testL(PROGRAM, TRIALS);
			double timeJava = testJava(PROGRAM, TRIALS);
			System.out.println("\nOver " + TRIALS + " trials:");
			System.out.println("Program in L    : " + timeL + "ms");
			System.out.println("Program in Java : " + timeJava + "ms");
		}
	}
	
	/**
	 * tests program in L
	 * @param program
	 * @param trials
	 * @return time elapsed in milliseconds
	 */
	private static double testL(int program, int trials) {
		CodeReader fileReader;
		System.out.println("Program " + program + ": ");
		long avg = 0;
		try {
			for (int j = 0; j < trials; j++) {
				fileReader = new CodeReader(new File("programs/prog" + program + ".txt"));
				long start = System.nanoTime();
				while (fileReader.hasNextStatement()) {
					Evaluator.eval(fileReader.nextStatement());
				}
				long end = System.nanoTime();
				avg += end-start;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return (int)avg/trials/1000/(double)1000;
	}
	
	/**
	 * tests program in java
	 * @param trials
	 * @return time elapsed in milliseconds
	 */
	private static double testJava(int program, int trials) {
		double avg = 0;
		if (program == 1) {
			for (int i = 0; i < trials; i++) {
				long start = System.nanoTime();
				int a = 45;
				a *= 30;
				int b = 0;
				b += a;
				b += b;
				a = a;
				a = 40;
				b *= 2;
				System.out.print("a=" + a);
				System.out.print("b=" + b);
				long end = System.nanoTime();
				avg += end-start;
			}
		} else if (program == 2) {
			for (int i = 0; i < trials; i++ ) {
				long start = System.nanoTime();
				int B = 10 ;
				int A = B ;
				int C = 30 ;
				int D = 14 ;
				int E = 101 ;
				E += C ;
				E += D ;
				A += E ;
				E += E ;
				System.out.print("A=" + A);
				System.out.print("B=" + B);
				System.out.print("C=" + C);
				System.out.print("D=" + D);
				System.out.print("E=" + E);
				long end = System.nanoTime();
				avg += end-start;
			}
		}
		return (int)avg/trials/1000/(double)1000;
	}

}
