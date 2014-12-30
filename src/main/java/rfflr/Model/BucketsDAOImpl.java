package rfflr.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import rfflr.Model.Buckets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.Serializable;

@Component
@Transactional
public interface BucketsDAOImpl extends JpaRepository<Buckets, Serializable> {

    public Buckets findByRaffleId(Integer raffleId);

    public Buckets getWinner(Integer raffleId, Integer bucketId);

    public Buckets isEntered(Integer employee, Integer raffleId);
}
