package rfflr.Model;

import rfflr.Model.Buckets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public interface BucketsDAOImpl extends CrudRepository<Buckets, Long> {

    public Buckets findByRaffleId(Integer raffleId);

    public Buckets getWinner(Integer raffleId, Integer bucketId);
}
