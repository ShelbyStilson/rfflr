package demo;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaffleEventController {
	
    private static final RaffleEventDAO raedi= new RaffleEventDaoImpl();

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<RaffleEvent> getAllRaffleEvent() {
        return raedi.getAllRaffleEvents();
    }
    
    @RequestMapping(value="/events", method=RequestMethod.POST)
    public @ResponseBody RaffleEvent addRaffleEventEvent(@RequestBody RaffleEvent event) {
    	return raedi.createRaffleEvent(event.getTitle(),
    			event.getStartTime(),
    			event.getEndTime(),
    			event.getDescription(),
    			event.getHost(),
    			event.getImage(),
    			event.getLink(),
    			event.getContactPhone(),
    			event.getContactEmail(),
    			event.getLocation());
    }
     
    @RequestMapping(value="/events/{id}", method=RequestMethod.GET)
    public @ResponseBody RaffleEvent getRaffleEventById(@PathVariable(value="id") int id) {
        return raedi.getRaffleEvent(id);
    }
    
    @RequestMapping(value="/events/{id}", method=RequestMethod.POST)
    public @ResponseBody RaffleEvent updateRaffleEvent(@RequestBody RaffleEvent event, @PathVariable(value="id")  int id) {
    	event.setId(id);
    	return raedi.updateRaffleEvent(event);
    }

    
    @RequestMapping(value="/events/{id}", method=RequestMethod.DELETE)
    public @ResponseBody void deleteRaffleEvent(@PathVariable(value="id") int id) {
    	raedi.deleteRaffleEvent(id);
    }
    
    
}
