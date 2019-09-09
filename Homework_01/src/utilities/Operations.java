package utilities;

import java.util.Hashtable;

import objects.Type;
import objects.Datum;

public class Operations {
	private static Hashtable<String, Datum> symbolTable = new Hashtable<String, Datum>();
	public static void gets(String key, Object value) {
		Type type = Type.NULL;
		if(value instanceof String) type = Type.STRING;
		else if(value instanceof Boolean) type = Type.BOOLEAN;
		else if(value instanceof Integer) type = Type.INTEGER;
		else System.out.println("ya fucked up" + value.getClass());
		symbolTable.put(key, new Datum(type, value));
	}
}
