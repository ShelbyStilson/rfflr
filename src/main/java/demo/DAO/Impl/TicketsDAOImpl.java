package demo.DAO.Impl;

import demo.Model.Tickets;
import demo.DAO.TicketsDAO;
import demo.DAO.Impl.BaseDAOImpl;

import java.util.List;

/**
 * This DAO interfaces with Ibatis to handle data persistence for {@link Tickets} objects
 */
public class TicketsDAOImpl extends BaseDAOImpl implements TicketsDAO {

    //Set TYPE so BaseDAOImpl knows which sqlMap to call
    private static final Class<Tickets> TYPE = Tickets.class;

    /**
     * {@inheritDoc}
     */
    public Tickets getById(int id) {
        return super.getById(id, TYPE);
    }

    public boolean getByEmployee(Integer employee, Integer raffleId) {
        Tickets ticket = new Tickets();

        ticket.setEmployee(employee);
        ticket.setRaffleId(raffleId);

        if (super.getByCriteria(ticket, TYPE) == null) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Tickets add(Tickets ticket) {

        if (ticket.getId() != null) {
            return super.add(ticket, TYPE);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void update(Tickets ticket) {
        if (ticket.getId() != null) {
            super.update(ticket, TYPE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer employee, Integer raffleId) {
        Tickets ticket = new Tickets();

        ticket.setEmployee(employee);
        ticket.setRaffleId(raffleId);

        super.delete(ticket, TYPE);
    }

    /**
     * {@inheritDoc}
     */
    public Tickets getTickets(Integer employee, Integer raffleId) {
        Tickets ticket = new Tickets();
        ticket.setEmployee(employee);
        ticket.setRaffleId(raffleId);

        return super.getTickets(ticket, TYPE);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tickets> getAll(Integer employee, Integer ticketNum, Integer raffleId, Integer tickets) {
        return super.getAll(TYPE, employee, ticketNum, raffleId, 0);
    }
}

