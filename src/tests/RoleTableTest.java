package tests;

public class RoleTableTest extends TableTest{

	public RoleTableTest(String filepath) {
		super(filepath);
		data = dp.getRoleTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new RoleTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}