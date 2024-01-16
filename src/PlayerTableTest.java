
import java.util.Set;

public class PlayerTableTest {

	public static void main(String[] args) {
		
		DataProcessor data = new DataProcessor("src/data/SampleData.csv");
		
		Set<StringArrayWrapper> playerData = data.getPlayerTableInfo();
		
		for(StringArrayWrapper player : playerData) {
			System.out.println(player.toString());
		}
	}
	
}