package tests;

public class ParticipateInTableTest extends TableTest{

	public ParticipateInTableTest(String filepath) {
		super(filepath);
		data = dp.getParticipateInTableInfo();
	}

	public static void main(String[] args) {
		
		TableTest tester = new ParticipateInTableTest("src/data/SampleData.csv");
		
		tester.printData();
	}
	
}