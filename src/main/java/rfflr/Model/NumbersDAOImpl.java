package rfflr.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.Serializable;

@Component
@Transactional
public interface NumbersDAOImpl extends JpaRepository<Numbers, Serializable> {

    public Numbers findByRaffleId(Integer raffleId);

    public Numbers findByBucketId(Integer bucketId);

}
