package main;
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
	List<String[]> data;
	
	public DataProcessor(String filepath) {
		this.filepath = filepath;
		data = getAllTuples();
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
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> playerSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper playerData = new StringArrayWrapper(new String[] {array[0], array[1], array[2], array[3], array[4], array[5]});
			playerSet.add(playerData);
		}
		
		return playerSet;
	}
	
	public Set<StringArrayWrapper> getGearTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> gearSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper gearData = new StringArrayWrapper(new String[] {array[6], array[8], array[7], array[9], array[10]});
			gearSet.add(gearData);
		}
		
		return gearSet;
	}
	
	public Set<StringArrayWrapper> getTeamTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> teamSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper teamData = new StringArrayWrapper(new String[] {array[12], array[13], array[14]});
			teamSet.add(teamData);
		}
		
		return teamSet;
	}
	
	public Set<StringArrayWrapper> getMatchTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> matchSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper matchData = new StringArrayWrapper(new String[] {array[16], array[17], array[18]});
			matchSet.add(matchData);
		}
		
		return matchSet;
	}
	
	public Set<StringArrayWrapper> getEventTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> eventSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper eventData = new StringArrayWrapper(new String[] {array[22], array[23], array[20], array[21], array[25]});
			eventSet.add(eventData);
		}
		
		return eventSet;
	}
	
	public Set<StringArrayWrapper> getOrgTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> orgSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper orgData = new StringArrayWrapper(new String[] {array[28], array[27], array[26]});
			orgSet.add(orgData);
		}
		
		return orgSet;
	}
	
	public Set<StringArrayWrapper> getHeldTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> heldSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper heldData = new StringArrayWrapper(new String[] {array[26], array[21]});
			heldSet.add(heldData);
		}
		
		return heldSet;
	}
	
	public Set<StringArrayWrapper> getHasTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> hasSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper hasData = new StringArrayWrapper(new String[] {array[21], array[16]});
			hasSet.add(hasData);
		}
		
		return hasSet;
	}
	
	public Set<StringArrayWrapper> getPlacedInTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> placedInSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper placedInData = new StringArrayWrapper(new String[] {array[12], array[21], array[29]});
			placedInSet.add(placedInData);
		}
		
		return placedInSet;
	}
	
	public Set<StringArrayWrapper> getPlayedOnTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> playedOnSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper playedOnData = new StringArrayWrapper(new String[] {array[12], array[16]});
			playedOnSet.add(playedOnData);
		}
		
		return playedOnSet;
	}
	
	public Set<StringArrayWrapper> getParticipateInTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> participateInSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper participateInData = new StringArrayWrapper(new String[] {array[0], array[16], array[19]});
			participateInSet.add(participateInData);
		}
		
		return participateInSet;
	}
	
	public Set<StringArrayWrapper> getPlaysForTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> playsForSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper playsForData = new StringArrayWrapper(new String[] {array[0], array[12]});
			playsForSet.add(playsForData);
		}
		
		return playsForSet;
	}
	
	public Set<StringArrayWrapper> getUsesTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> usesSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper usesData = new StringArrayWrapper(new String[] {array[0], array[6], array[11]});
			usesSet.add(usesData);
		}
		
		return usesSet;
	}
	
	public Set<StringArrayWrapper> getPlayerRoleTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> playerRoleSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper playerRoleData = new StringArrayWrapper(new String[] {array[0], array[5]});
			playerRoleSet.add(playerRoleData);
		}
		
		return playerRoleSet;
	}
	
	public Set<StringArrayWrapper> getRoleTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> roleSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper roleData = new StringArrayWrapper(new String[] {array[5]});
			roleSet.add(roleData);
		}
		
		return roleSet;
	}
	
	public Set<StringArrayWrapper> getGearTypeTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> gearTypeSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper gearTypeData = new StringArrayWrapper(new String[] {array[6], array[7]});
			gearTypeSet.add(gearTypeData);
		}
		
		return gearTypeSet;
	}
	
	public Set<StringArrayWrapper> getTypeTableInfo() {
		
		// use a set to automatically remove duplicates
		Set<StringArrayWrapper> typeSet = new TreeSet<StringArrayWrapper>();
		
		// remove unneeded columns
		for(String[] array : data) {
			StringArrayWrapper typeData = new StringArrayWrapper(new String[] {array[7]});
			typeSet.add(typeData);
		}
		
		return typeSet;
	}

}
