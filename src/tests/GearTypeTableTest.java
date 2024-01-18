package tests;

public class GearTypeTableTest extends TableTest{

	public GearTypeTableTest(String filepath) {
		super(filepath);
		data = dp.getGearTypeTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new GearTypeTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}