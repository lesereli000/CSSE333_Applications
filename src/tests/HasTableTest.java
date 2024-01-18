package tests;

public class HasTableTest extends TableTest{

	public HasTableTest(String filepath) {
		super(filepath);
		data = dp.getHasTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new HasTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}