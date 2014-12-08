package demo.Logic;

import demo.DAO.Impl.BucketsDAOImpl;
import demo.Model.Buckets;
import demo.Model.Tickets;
import demo.DAO.Impl.TicketsDAOImpl;

import java.util.List;

public class raffleLogic {

    BucketsDAOImpl bucketsDAO = new BucketsDAOImpl();
    TicketsDAOImpl ticketsDAO = new TicketsDAOImpl();

    public int getWinner(Integer raffleId, Integer bucketId) {
        return bucketsDAO.getWinner(raffleId, bucketId).getEntry();
    }

    public boolean addEntry(Integer employee, Integer raffleId, Integer bucketId, Integer amount, boolean singleEntry) {

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

    public Integer getTickets(Integer employee, Integer raffleId) {
        return ticketsDAO.getTickets(employee,raffleId).getTicketNum();
    }

    public boolean deleteTickets(Integer employee, Integer raffleId, Integer ticketDec) {

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

    public boolean giveTickets(Integer employee, Integer raffleId, Integer tickets) {
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

    public void deleteEmployee(Integer raffleId, Integer employee) {
        bucketsDAO.delete(employee, raffleId);
    }

    public List<Integer> getMultipleForBucket(Integer raffleId, Integer bucketId, Integer howMany) {
        List<Integer> winners = null;
        Integer contestant = 0;

        for (int i = 0; i < howMany; i++) {
            contestant = getWinner(raffleId,bucketId);
            winners.add(contestant);
            bucketsDAO.delete(raffleId, contestant);
        }

        return winners;
    }

    public void deleteRaffleEntries(Integer raffleId) {
        bucketsDAO.deleteRaffle(raffleId);
    }

    public void deleteBucketEntries(Integer bucketId) {
        bucketsDAO.deleteBucket(bucketId);
    }

    public int getNumberInBucket(Integer bucketId) {
        return 0;
    }
}