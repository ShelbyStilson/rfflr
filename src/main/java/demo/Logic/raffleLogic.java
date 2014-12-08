package demo.Logic;

import demo.DAO.Impl.BucketsDAOImpl;
import demo.Model.Buckets;
import demo.Model.Tickets;
import demo.DAO.Impl.TicketsDAOImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public int getWinner(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId) {
        return bucketsDAO.getWinner(raffleId, bucketId).getEntry();
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public boolean working() {
        return true;
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String error() {
        return "<h1>Working</h1>";
    }

    @RequestMapping(value="/add/{employee}/{raffleId}/{bucketId}/{amount}/{singleEntry}", method=RequestMethod.POST)
    public boolean addEntry(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId,
                            @PathVariable(value="bucketId") Integer bucketId, @PathVariable(value="amount") Integer amount, @PathVariable(value="singleEntry") boolean singleEntry) {

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

    @RequestMapping(value="/tickets/{employee}/{raffleId}", method=RequestMethod.GET)
    public Integer getTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId) {
        return ticketsDAO.getTickets(employee,raffleId).getTicketNum();
    }

    @RequestMapping(value="/ticketdelete/{employee}/{raffleId}/{ticketDec}", method=RequestMethod.DELETE)
    public boolean deleteTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="ticketDec") Integer ticketDec) {

        if (ticketsDAO.getByEmployee(employee, raffleId)) {
            Tickets ticket = new Tickets();

            Integer ticketNum = getTickets(employee, raffleId) - ticketDec;

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
    public boolean giveTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="tickets") Integer tickets) {
        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(employee);

        if (ticketsDAO.getByEmployee(employee, raffleId)){
            ticket.setTicketNum(tickets + getTickets(employee,raffleId));
            ticketsDAO.update(ticket);
        } else {
            ticket.setTicketNum(tickets);
            ticketsDAO.add(ticket);
        }

        return false;
    }

    @RequestMapping(value="/employeedelete/{raffleId}/{employee}", method=RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="employee") Integer employee) {
        bucketsDAO.delete(employee, raffleId);
    }

    @RequestMapping(value="/multiplewinners/{raffleId}/{bucketId}/{howMany}", method=RequestMethod.GET)
    public List<Integer> getMultipleForBucket(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId, @PathVariable(value="howMany") Integer howMany) {
        List<Integer> winners = null;
        Integer contestant = 0;

        for (int i = 0; i < howMany; i++) {
            contestant = getWinner(raffleId,bucketId);
            winners.add(contestant);
            bucketsDAO.delete(raffleId, contestant);
        }

        return winners;
    }

    @RequestMapping(value="/raffledelete/{raffleId}", method=RequestMethod.DELETE)
    public void deleteRaffleEntries(@PathVariable(value="raffleId") Integer raffleId) {
        bucketsDAO.deleteRaffle(raffleId);
    }

    @RequestMapping(value="/bucketdelete/{bucketId}", method=RequestMethod.DELETE)
    public void deleteBucketEntries(@PathVariable(value="bucketId") Integer bucketId) {
        bucketsDAO.deleteBucket(bucketId);
    }

    @RequestMapping(value="/number/{bucketId}", method=RequestMethod.GET)
    public int getNumberInBucket(@PathVariable(value="bucketId") Integer bucketId) {
        return 0;
    }
}