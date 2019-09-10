package utilities;

import java.util.HashMap;
import java.util.Hashtable;

import objects.Type;
import objects.Datum;

public class Operations {
	public static HashMap<String, Datum> symbolTable = new HashMap<String, Datum>();

	public static void gets(String key, Object value) {
		Type type = Type.NULL;
		if (value instanceof String)
			type = Type.STRING;
		else if (value instanceof Boolean)
			type = Type.BOOLEAN;
		else if (value instanceof Integer)
			type = Type.INTEGER;
		else
			System.out.println("ya fucked up" + value.getClass());
		symbolTable.put(key, new Datum(type, value));
	}

	public static void plusEquals(String key, Object value) {
		if (symbolTable.get(key).type == Type.BOOLEAN || !areCompatible(symbolTable.get(key).type, value)) {
			throw new RuntimeException();
		}
		if (symbolTable.get(key).type == Type.STRING) {
			String temp = (String) symbolTable.get(key).value;
			symbolTable.put(key, new Datum(Type.STRING, temp + (String) value));
		} else if (symbolTable.get(key).type == Type.INTEGER) {
			Integer temp = (Integer) symbolTable.get(key).value;
			symbolTable.put(key, new Datum(Type.INTEGER, temp + (Integer) value));
		}
	}

	public static void timesEquals(String key, Object value) {
		if (symbolTable.get(key).type != Type.INTEGER || !areCompatible(symbolTable.get(key).type, value)) {
			throw new RuntimeException();
		}
		Integer temp = (Integer) symbolTable.get(key).value;
		symbolTable.put(key, new Datum(Type.INTEGER, temp * (Integer) value));
	}

	public static void andEquals(String key, Object value) {
		if (symbolTable.get(key).type != Type.BOOLEAN || !areCompatible(symbolTable.get(key).type, value)) {
			throw new RuntimeException();
		}
		Boolean temp = (Boolean) symbolTable.get(key).value;
		symbolTable.put(key, new Datum(Type.BOOLEAN, temp && (Boolean) value));
	}

	public static void print(String key) {
		System.out.println(key + "=" + symbolTable.get(key).value);
	}

	public static boolean areCompatible(Type t, Object o) {
		if (o instanceof String)
			return t == Type.STRING;
		if (o instanceof Boolean)
			return t == Type.BOOLEAN;
		return t == Type.INTEGER;
	}
}
