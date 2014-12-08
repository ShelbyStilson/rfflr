package demo.DAO;

import demo.Model.Tickets;

import java.util.List;

/**
 * This DAO handles data persistence for {@link Tickets} objects
 */
public interface TicketsDAO {

    /**
     * Get {@link Tickets} by id
     * @param id the id of the {@link Tickets} to get
     * @return {@link Tickets}
     */
    Tickets getById(int id);

    boolean getByEmployee(Integer employee, Integer raffleId);

    /**
     * Add {@link Tickets}
     * @param ticket the {@link Tickets} to add
     * @return the {@link Tickets} that was added
     */
    Tickets add(Tickets ticket);

    /**
     * Get all {@link Tickets}
     * @param employee - an Integer id that represents that employee
     * @param ticketNum - contains the number of tickets that employee possesses for that raffle
     * @param raffleId - identifies the raffle
     * @return list of {@link Tickets}
     */
    List<Tickets> getAll(Integer employee, Integer ticketNum, Integer raffleId, Integer tickets);

    /**
     * update {@link Tickets}
     * @param ticket the ticket to be updated
     * accepts ticket and finds where in the database the ticket was stored and then updates the information
     */
    void update(Tickets ticket);

    /**
     * Get by position {@link Tickets}
     * @param employee - the identification number of the employee
     * @param raffleId - raffle to be checked on
     * @return ticket number count found based on parameters {@link Tickets}
     */
    Tickets getTickets(Integer employee, Integer raffleId);

}
