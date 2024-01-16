package tests;

public class TeamTableTest extends TableTest{

	public TeamTableTest(String filepath) {
		super(filepath);
		data = dp.getTeamTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new TeamTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}