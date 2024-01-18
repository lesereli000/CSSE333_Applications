package tests;

public class PlaysForTableTest extends TableTest{

	public PlaysForTableTest(String filepath) {
		super(filepath);
		data = dp.getPlaysForTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new PlaysForTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}