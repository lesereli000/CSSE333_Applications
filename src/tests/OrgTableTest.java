package tests;

public class OrgTableTest extends TableTest{

	public OrgTableTest(String filepath) {
		super(filepath);
		data = dp.getOrgTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new OrgTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}