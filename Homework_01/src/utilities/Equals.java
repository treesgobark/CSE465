package utilities;

public class Equals {
	private int type;
	private String key;
	private Object value;

	public Equals(int type) {
		this.type = type;
	}

	private void setKey(String key) {
		this.key = key;
	}

	private void setValue(Object value) {
		this.value = value;
	}

	public void evaluate() {
		if (type == 0)
			Operations.gets(key, value);
		if (type == 1)
			Operations.plusEquals(key, value);
		if (type == 2)
			Operations.timesEquals(key, value);
		if (type == 3)
			Operations.andEquals(key, value);
	}
	
	public String toString() {
		String temp;
		if (type == 0)
			temp = "=";
		else if (type == 1)
			temp = "+=";
		else if (type == 2)
			temp = "*=";
		else if (type == 3)
			temp = "&=";
		else
			temp = "equals?";
		return temp;
	}
}
