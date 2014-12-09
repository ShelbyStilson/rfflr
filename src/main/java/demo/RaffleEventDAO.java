package demo;

import java.util.Date;
import java.util.List;

public interface RaffleEventDAO {
	
	public RaffleEvent getRaffleEvent(int id);
	
	public RaffleEvent createRaffleEvent(String title, 
			Date startTime, 
			Date endTime, 
			String description,
			String host,
			String image,
			String link,
			String contactPhone,
			String contactEmail,
			String location);
	
	public List<RaffleEvent> getAllRaffleEvents();

	public RaffleEvent updateRaffleEvent(RaffleEvent re);
	
	public void deleteRaffleEvent(int id);
}
