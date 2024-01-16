public class StringArrayWrapper implements Comparable<StringArrayWrapper>{

	private String[] data;
	private int size;
	
	public StringArrayWrapper(String[] newData) {
		this.data = newData;
		this.size = newData.length;
	}
	
	public int size() {
		return size;
	}
	
	public String getData(int index) {
		return data[index];
	}

	@Override
	public int compareTo(StringArrayWrapper other) {
		if(this.size != other.size()) {
			throw new IllegalArgumentException();
		}
		return this.data[0].compareTo(other.getData(0));
	}
	
	public String toString() {
		String out = new String();
		
		for(int i = 0; i < data.length - 1; i++) {
			out += data[i] + ", ";
		}
		out += data[data.length - 1];
		
		return out;
	}
	
}
