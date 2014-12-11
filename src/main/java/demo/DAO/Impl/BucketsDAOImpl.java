package demo.DAO.Impl;

import demo.Model.Buckets;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BucketsDAOImpl extends CrudRepository<Buckets, Long> {

    public Buckets findByRaffleId(Integer raffleId);

    public Buckets getWinner(Integer raffleId, Integer bucketId);
}
