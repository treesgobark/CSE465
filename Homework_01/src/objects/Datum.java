package objects;

public class Datum {
	public Type type;
	public Object value;
	public Datum(Type type, Object value) {
		this.type = type;
		this.value = value;
	}
	public String toString() {
		return "{" + this.type + ", " + this.value;
	}
}
