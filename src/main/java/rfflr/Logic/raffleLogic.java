package rfflr.Logic;

import org.springframework.web.servlet.ModelAndView;
import rfflr.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import org.springframework.ui.ModelMap;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class raffleLogic {

    // @Autowired
    // private TicketsDAOImpl ticketsDAO;

    // @Autowired
    // private BucketsDAOImpl bucketsDAO;

    // @Autowired
    // private NumbersDAOImpl numbersDAO;

    private TicketsDAOImpl ticketsDAO;

    private BucketsDAOImpl bucketsDAO;

    private NumbersDAOImpl numbersDAO;

    public static void main(String[] args) {
        SpringApplication.run(raffleLogic.class, args);

    }

    @RequestMapping(value="/winner/{raffleId}/{bucketId}", method=RequestMethod.GET)
    @ResponseBody
    public Integer getWinner(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        try {
            return bucketsDAO.getWinner(raffleId, bucketId).getEntry();
        } catch (Exception e) {
            return 5;
        }
    }

    @RequestMapping(value="/confirm/{employee}/{raffleId}", method=RequestMethod.GET)
    public boolean isEntered(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId) {
        return ticketsDAO.getByEmployee(employee, raffleId) != null;
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

        Tickets ticket = new Tickets();
        Buckets bucket = new Buckets();
        Numbers number = new Numbers();

        if (ticketsDAO.getByEmployee(employee, raffleId) == null) {
            return false;
        } else if (singleEntry && bucketsDAO.isEntered(employee, raffleId) != null) {
            return true;
        }

        Integer totalTickets = ticketsDAO.getTickets(employee, raffleId).getTicketNum();

        if (totalTickets >= amount) {
            ticket.setEmployee(employee);
            ticket.setRaffleId(raffleId);
            ticket.setTicketNum(totalTickets-amount);

            bucket.setRaffleId(raffleId);
            bucket.setBucketId(bucketId);
            bucket.setEntry(employee);
            bucket.setTickets(amount);

            number.setBucketId(bucketId);
            number.setRaffleId(raffleId);

            int inc = numbersDAO.findByBucketId(bucketId).getNumberOfEntries();

            ticketsDAO.save(ticket);

            if (!singleEntry) {
                for (int i = 0; i < amount; i++) {
                    bucketsDAO.save(bucket);
                }

                number.setNumberOfEntries(inc + amount);
                numbersDAO.save(number);
            } else {
                bucketsDAO.save(bucket);
                number.setNumberOfEntries(inc + 1);
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

        Tickets ticket = new Tickets();

        ticket.setRaffleId(raffleId);
        ticket.setEmployee(employee);

        if (ticketsDAO.getByEmployee(employee, raffleId) != null) {
            Integer ticketNum = getTickets(employee, raffleId, response) - ticketDec;

            if (ticketNum < 0) {
                ticketNum = 0;
            }

            ticket.setTicketNum(ticketNum);

            ticketsDAO.save(ticket);

            return true;
        }

        return false;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addTickets(@ModelAttribute("ticketAddition")ticketAmount amount, ModelMap model) {
        ModelAndView view = new ModelAndView("addTickets");

        int raffleId = 1;

        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(amount.getEntry());

        if (ticketsDAO.getByEmployee(amount.getEntry(), raffleId) != null){
            ticket.setTicketNum(amount.getAmount() + ticketsDAO.getTickets(amount.getEntry(),raffleId).getTicketNum());
            ticketsDAO.save(ticket);
        } else {
            ticket.setTicketNum(amount.getAmount());
            ticketsDAO.save(ticket);
        }

        return view;
    }

    @RequestMapping(value = "/validateEntry", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView validateTickets(@ModelAttribute("validate")ticketAmount user, ModelMap model) {
        ModelAndView view = new ModelAndView("validation");

        int raffleId = 1;

        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(user.getEntry());

        if (ticketsDAO.getByEmployee(user.getEntry(), raffleId) != null){
            view.addObject("",true);
        } else {
            view.addObject("",false);
        }

        return view;
    }

    @RequestMapping(value = "/pickPrizes", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView pickPrize(@ModelAttribute("validate")ticketAmount user, ModelMap model) {
        ModelAndView view = new ModelAndView("validation");

        int raffleId = 1;

        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(user.getEntry());

        if (ticketsDAO.getByEmployee(user.getEntry(), raffleId) != null){
            ticket.setTicketNum(user.getAmount() + ticketsDAO.getTickets(user.getEntry(),raffleId).getTicketNum());
            ticketsDAO.save(ticket);
        } else {
            ticket.setTicketNum(user.getAmount());
            ticketsDAO.save(ticket);
        }

        return view;
    }

    @RequestMapping(value="/give/{employee}/{raffleId}/{bucketId}", method=RequestMethod.POST)
    @ResponseBody
    public boolean giveTickets(@PathVariable(value="employee") Integer employee, @PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="tickets") Integer tickets, HttpServletResponse response) {
        Tickets ticket = new Tickets();
        ticket.setRaffleId(raffleId);
        ticket.setEmployee(employee);

        if (ticketsDAO.getByEmployee(employee, raffleId) != null){
            ticket.setTicketNum(tickets + getTickets(employee,raffleId, response));
            ticketsDAO.save(ticket);
        } else {
            ticket.setTicketNum(tickets);
            ticketsDAO.save(ticket);
        }

        return true;
    }

    @RequestMapping(value="/employeedelete/{raffleId}/{employee}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="employee") Integer employee, HttpServletResponse response) {
        Buckets bucket = new Buckets();
        bucket.setEntry(employee);
        bucket.setRaffleId(raffleId);

        bucketsDAO.delete(bucket);
    }

    @RequestMapping(value="/multiplewinners/{raffleId}/{bucketId}/{howMany}", method=RequestMethod.POST)
    @ResponseBody
    public List<Integer> getMultipleForBucket(@PathVariable(value="raffleId") Integer raffleId, @PathVariable(value="bucketId") Integer bucketId, @PathVariable(value="howMany") Integer howMany, HttpServletResponse response) {
        List<Integer> winners = null;
        Integer contestant = 0;

        for (int i = 0; i < howMany; i++) {
            contestant = getWinner(raffleId, bucketId, response);
            winners.add(contestant);

            Buckets bucket = new Buckets();
            bucket.setEntry(contestant);
            bucket.setRaffleId(raffleId);



            bucketsDAO.delete(bucket);
        }

        return winners;
    }

    @RequestMapping(value="/raffledelete/{raffleId}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteRaffleEntries(@PathVariable(value="raffleId") Integer raffleId, HttpServletResponse response) {
        Buckets bucket = new Buckets();
        bucket.setRaffleId(raffleId);

        Numbers number = new Numbers();
        number.setRaffleId(raffleId);

        List<Buckets> list = null;
        list.add(bucket);

        bucketsDAO.deleteInBatch(list);
        numbersDAO.delete(number);
    }

    @RequestMapping(value="/bucketdelete/{bucketId}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteBucketEntries(@PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        Buckets bucket = new Buckets();
        bucket.setBucketId(bucketId);

        Numbers number = new Numbers();
        number.setBucketId(bucketId);

        List<Buckets> list = null;
        list.add(bucket);

        bucketsDAO.deleteInBatch(list);
        numbersDAO.delete(number);
    }

    @RequestMapping(value="/number/{bucketId}", method=RequestMethod.POST)
    @ResponseBody
    public int getNumberInBucket(@PathVariable(value="bucketId") Integer bucketId, HttpServletResponse response) {
        return numbersDAO.findByBucketId(bucketId).getNumberOfEntries();
    }

}