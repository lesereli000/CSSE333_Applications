package tests;

public class PlayedOnTableTest extends TableTest{

	public PlayedOnTableTest(String filepath) {
		super(filepath);
		data = dp.getPlayedOnTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new PlayedOnTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}