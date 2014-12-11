package rfflr.Model;

import rfflr.Model.Tickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public interface TicketsDAOImpl extends CrudRepository<Tickets, Long> {

    public Tickets getByEmployee(Integer employee, Integer raffleId);

    public Tickets getTickets(Integer employee, Integer raffleId);

}

