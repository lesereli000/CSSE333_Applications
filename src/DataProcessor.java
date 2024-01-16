import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class DataProcessor {
	
	private String filepath;
	
	public DataProcessor(String filepath) {
		this.filepath = filepath;
	}
	
	private List<String[]> getAllTuples() {
		FileReader filereader;
		List<String[]> allData = null;
		
		try {
			
			filereader = new FileReader(filepath);
			// create csvReader object and skip first Line (Headers)
	        CSVReader csvReader = new CSVReaderBuilder(filereader) 
	                                  .withSkipLines(1) 
	                                  .build(); 
	        allData = csvReader.readAll();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		
		return allData; 
	}
	
	public Set<StringArrayWrapper> getPlayerTableInfo() {
		// get all data
		List<String[]> data = getAllTuples();
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> playerSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper playerData = new StringArrayWrapper(new String[] {array[0], array[1], array[2], array[3], array[4], array[5]});
			playerSet.add(playerData);
		}
		
		return playerSet;
	}

}
