package tests;

public class PlayerRoleTableTest extends TableTest{

	public PlayerRoleTableTest(String filepath) {
		super(filepath);
		data = dp.getPlayerRoleTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new PlayerRoleTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}