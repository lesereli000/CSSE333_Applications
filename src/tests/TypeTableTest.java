package tests;

public class TypeTableTest extends TableTest{

	public TypeTableTest(String filepath) {
		super(filepath);
		data = dp.getTypeTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new TypeTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}