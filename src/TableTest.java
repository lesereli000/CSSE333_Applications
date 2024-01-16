import java.util.Set;

public abstract class TableTest {

	DataProcessor dp;
	Set<StringArrayWrapper> data;
	
	public TableTest(String filepath) {
		dp = new DataProcessor(filepath);
		data = null;
	}
	
	public void printData() {
		for(StringArrayWrapper row : data) {
			System.out.println(row.toString());
		}
	}
}
