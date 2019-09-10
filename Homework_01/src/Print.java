

public class Print {
	private String key;

	public Print() {
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void evaluate() {
		Operations.print(key);
	}
	
	public String toString() {
		return "PRINT";
	}
}
