package demo.DAO.Impl;

import demo.Model.Buckets;
import demo.DAO.BucketsDAO;

import java.util.List;

/**
 * This DAO interfaces with Ibatis to handle data persistence for {@link Buckets} objects
 */
public class BucketsDAOImpl extends BaseDAOImpl implements BucketsDAO {

    //Set TYPE so BaseDAOImpl knows which sqlMap to call
    private static final Class<Buckets> TYPE = Buckets.class;

    /**
     * {@inheritDoc}
     */
    public Buckets getById(int id) {
        return super.getById(id, TYPE);
    }

    /**
     * {@inheritDoc}
     */
    public Buckets add(Buckets bucket) {

        if (bucket.getId() != null) {
            return super.add(bucket,TYPE);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void update(Buckets bucket) {
        if (bucket.getId() != null) {
            super.update(bucket,TYPE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer raffleId, Integer entry) {
        Buckets bucket = new Buckets();
        bucket.setEntry(entry);
        bucket.setRaffleId(raffleId);

        super.delete(bucket,TYPE);
    }

    public void deleteRaffle(Integer raffleId) {
        Buckets bucket = new Buckets();
        bucket.setRaffleId(raffleId);

        super.deleteEvent(bucket,TYPE);
    }

    public void deleteBucket(Integer bucketId) {
        Buckets bucket = new Buckets();
        bucket.setBucketId(bucketId);

        super.deleteEvent(bucket,TYPE);
    }

    /**
     * {@inheritDoc}
     */
    public Buckets getByCriteria(Integer raffleId, Integer bucketId) {
        Buckets bucket = new Buckets();
        bucket.setRaffleId(raffleId);
        bucket.setBucketId(bucketId);

        return super.getByCriteria(bucket, TYPE);
    }

    /**
     * {@inheritDoc}
     */
    public List<Buckets> getAll(Integer raffleId, Integer bucketId, Integer entry, Integer tickets) {
        return super.getAll(TYPE, raffleId, bucketId, entry, tickets);
    }

    /**
     * {@inheritDoc}
     */
    public Buckets getWinner(Integer raffleId, Integer bucketId) {
        Buckets bucket = new Buckets();
        bucket.setBucketId(bucketId);
        bucket.setRaffleId(raffleId);

        return super.getWinner(bucket, TYPE);
    }
}
