package tests;

public class GearTableTest extends TableTest{

	public GearTableTest(String filepath) {
		super(filepath);
		data = dp.getGearTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new GearTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}