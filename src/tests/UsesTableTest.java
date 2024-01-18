package tests;

public class UsesTableTest extends TableTest{

	public UsesTableTest(String filepath) {
		super(filepath);
		data = dp.getUsesTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new UsesTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}