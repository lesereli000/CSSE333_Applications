package tests;
import java.util.Set;

import main.DataProcessor;
import main.StringArrayWrapper;

public class TableTest {

	protected DataProcessor dp;
	protected Set<StringArrayWrapper> data;
	
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
