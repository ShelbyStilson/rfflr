package demo.DAO.Impl;

import demo.Model.Tickets;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TicketsDAOImpl extends CrudRepository<Tickets, Long> {

    public Tickets getByEmployee(Integer employee, Integer raffleId);

    public Tickets getTickets(Integer employee, Integer raffleId);

}

