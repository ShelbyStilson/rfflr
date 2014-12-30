package rfflr.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import rfflr.Model.Tickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.Serializable;

@Component
@Transactional
public interface TicketsDAOImpl extends JpaRepository<Tickets, Serializable> {

    public Tickets getByEmployee(Integer employee, Integer raffleId);

    public Tickets getTickets(Integer employee, Integer raffleId);

}

