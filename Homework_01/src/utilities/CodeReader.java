package utilities;

import java.io.*;
import java.util.ArrayList;

import objects.Block;

public class CodeReader {
	private char[] fileArray;
	private File file;
	private FileReader in;
	private int placeholder = 0;

	public CodeReader(File file) {
		this.file = file;
		fileToCharArray();
	}

	/**
	 * 
	 * @param codeBlock - this is a block of statements that starts with '{' and
	 *                  ends with '}'
	 */
	public CodeReader(String codeBlock) {
		String temp = codeBlock;
		temp = temp.substring(1, temp.length() - 1);
		fileArray = temp.toCharArray();
	}

	public Block nextStatement() {
		StringBuilder sb = new StringBuilder();
		int braceDepth = 0;
		boolean loop = true;
		while (placeholder < fileArray.length && fileArray[placeholder] == ' ')
			placeholder++;
		while (placeholder < fileArray.length && loop) {
			switch (fileArray[placeholder]) {
			case ';':
				sb.append(';');
				if (braceDepth == 0)
					loop = false;
				break;
			case '{':
				sb.append('{');
				braceDepth++;
				break;
			case '}':
				sb.append('}');
				if (--braceDepth == 0)
					loop = false;
				break;
			default:
				sb.append(fileArray[placeholder]);
				break;
			}
			placeholder++;
		}
		return new Block(sb.toString());
	}

	private void fileToCharArray() {
		ArrayList<Character> list = new ArrayList<Character>();
		try {
			in = new FileReader(file);
			int c;
			while ((c = in.read()) != -1) {
				if (c != 13 && c != 10)
					list.add((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileArray = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			fileArray[i] = list.get(i);
		}
	}

	public boolean hasNextStatement() {
		return placeholder < fileArray.length;
	}
}
