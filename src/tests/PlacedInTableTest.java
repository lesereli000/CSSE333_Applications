package tests;

public class PlacedInTableTest extends TableTest{

	public PlacedInTableTest(String filepath) {
		super(filepath);
		data = dp.getPlacedInTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new PlacedInTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}