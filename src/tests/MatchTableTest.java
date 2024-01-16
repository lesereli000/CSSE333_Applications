package tests;

public class MatchTableTest extends TableTest{

	public MatchTableTest(String filepath) {
		super(filepath);
		data = dp.getMatchTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new MatchTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}