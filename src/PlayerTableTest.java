public class PlayerTableTest extends TableTest{

	public PlayerTableTest(String filepath) {
		super(filepath);
		data = dp.getPlayerTableInfo();
	}

	public static void main(String[] args) {
		
		PlayerTableTest tester = new PlayerTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}