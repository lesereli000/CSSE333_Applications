package tests;

public class EventTableTest extends TableTest{

	public EventTableTest(String filepath) {
		super(filepath);
		data = dp.getEventTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new EventTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}