package tests;

public class HeldTableTest extends TableTest{

	public HeldTableTest(String filepath) {
		super(filepath);
		data = dp.getHeldTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new HeldTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}