package demo.Logic;

import demo.DAO.Impl.BucketsDAOImpl;
import demo.Model.Buckets;
import demo.Model.Tickets;
import demo.DAO.Impl.TicketsDAOImpl;
import demo.RaffleEvent;
import demo.RaffleEventDAO;
import demo.RaffleEventDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class raffleLogic {

    public static void main(String[] args) {
        SpringApplication.run(raffleLogic.class, args);

    }

    private BucketsDAOImpl bucketsDAO = new BucketsDAOImpl();
    private TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();

    @RequestMapping(value="/winner/{raffleId}/{bucketId}", method=RequestMethod.GET)
    @ResponseBody
    public Integer getWinner(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        try {
            return bucketsDAO.getWinner(raffleId, bucketId).getEntry();
        } catch (Exception e) {
            return 5;
        }
    }

    @RequestMapping(value="/test", method=RequestMethod.POST)
    public String error() {
        return "<h1>Working</h1>";
    }

    @RequestMapping(value="/add/{employee}/{raffleId}/{bucketId}/{amount}/{singleEntry}", method=RequestMethod.POST)
    @ResponseBody
    public boolean addEntry(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId,
                            @PathVariable(value="bucketId") Integer bucketId, @PathVariable(value="amount") Integer amount, @PathVariable(value="singleEntry") boolean singleEntry
            , HttpServletResponse response) {

        Buckets bucket = new Buckets();
        Tickets ticket = new Tickets();

        Integer totalTickets = ticketsDAO.getTickets(employee, raffleId).getTicketNum();

        if (totalTickets >= amount) {
            ticket.setEmployee(employee);
            ticket.setRaffleId(raffleId);
            ticket.setTicketNum(totalTickets-amount);

            bucket.setRaffleId(raffleId);
            bucket.setBucketId(bucketId);
            bucket.setEntry(employee);
            bucket.setTickets(amount);

            ticketsDAO.update(ticket);

            if (!singleEntry) {
                for (int i = 0; i < amount; i++) {
                    bucketsDAO.add(bucket);
                }
            } else {
                bucketsDAO.add(bucket);
            }

            return true;
        }

        return false;
    }

    @RequestMapping(value="/tickets/{employee}/{raffleId}", method=RequestMethod.POST)
    @ResponseBody
    public Integer getTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, HttpServletResponse response) {
        return ticketsDAO.getTickets(employee,raffleId).getTicketNum();
    }

    @RequestMapping(value="/ticketdelete/{employee}/{raffleId}/{ticketDec}", method=RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="ticketDec") Integer ticketDec, HttpServletResponse response) {

        if (ticketsDAO.getByEmployee(employee, raffleId)) {
            Tickets ticket = new Tickets();

            Integer ticketNum = getTickets(employee, raffleId, response) - ticketDec;

            if (ticketNum < 0) {
                ticketNum = 0;
            }

            ticket.setTicketNum(ticketNum);
            ticket.setRaffleId(raffleId);
            ticket.setEmployee(employee);

            ticketsDAO.update(ticket);

            return true;
        }

        return false;
    }

    @RequestMapping(value="/give/{employee}/{raffleId}/{bucketId}", method=RequestMethod.POST)
    @ResponseBody
    public boolean giveTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="tickets") Integer tickets, HttpServletResponse response) {
        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(employee);

        if (ticketsDAO.getByEmployee(employee, raffleId)){
            ticket.setTicketNum(tickets + getTickets(employee,raffleId, response));
            ticketsDAO.update(ticket);
        } else {
            ticket.setTicketNum(tickets);
            ticketsDAO.add(ticket);
        }

        return false;
    }

    @RequestMapping(value="/employeedelete/{raffleId}/{employee}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="employee") Integer employee, HttpServletResponse response) {
        bucketsDAO.delete(employee, raffleId);
    }

    @RequestMapping(value="/multiplewinners/{raffleId}/{bucketId}/{howMany}", method=RequestMethod.POST)
    @ResponseBody
    public List<Integer> getMultipleForBucket(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId, @PathVariable(value="howMany") Integer howMany, HttpServletResponse response) {
        List<Integer> winners = null;
        Integer contestant = 0;

        for (int i = 0; i < howMany; i++) {
            contestant = getWinner(raffleId, bucketId, response);
            winners.add(contestant);
            bucketsDAO.delete(raffleId, contestant);
        }

        return winners;
    }

    @RequestMapping(value="/raffledelete/{raffleId}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteRaffleEntries(@PathVariable(value="raffleId") Integer raffleId, HttpServletResponse response) {
        bucketsDAO.deleteRaffle(raffleId);
    }

    @RequestMapping(value="/bucketdelete/{bucketId}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteBucketEntries(@PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        bucketsDAO.deleteBucket(bucketId);
    }

    @RequestMapping(value="/number/{bucketId}", method=RequestMethod.POST)
    @ResponseBody
    public int getNumberInBucket(@PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        return 0;
    }

    private static final RaffleEventDAO raedi= new RaffleEventDaoImpl();

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<RaffleEvent> getAllRaffleEvent() {
        return raedi.getAllRaffleEvents();
    }

    @RequestMapping(value="/events", method=RequestMethod.POST)
    public @ResponseBody
    RaffleEvent addRaffleEventEvent(@RequestBody RaffleEvent event) {
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