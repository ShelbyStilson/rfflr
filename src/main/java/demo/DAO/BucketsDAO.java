package demo.DAO;

import demo.Model.Buckets;

import java.util.List;

/**
 * This DAO handles data persistence for {@link Buckets} objects
 */
public interface BucketsDAO {

    /**
     * Get {@link Buckets} by id
     * @param id the id of the {@link Buckets} to get
     * @return {@link Buckets}
     */
    Buckets getById(int id);

    /**
     * Add {@link Buckets}
     * @param buckets the {@link Buckets} to add
     * @return the {@link Buckets} that was added
     */
    Buckets add(Buckets buckets);

    /**
     * Get all {@link Buckets}
     * @param raffleId to represent that current raffle
     * @param bucketId to represent the bucket within the raffle
     * @param entry to represent the employee id number
     * @param tickets to represent the amount of tickets
     * @return list of {@link Buckets}
     */
    List<Buckets> getAll(Integer raffleId, Integer bucketId, Integer entry, Integer tickets);

    /**
     * update {@link Buckets}
     * @param buckets the buckets to be updated
     * accepts buckets and find where in the database the buckets was stored and then updates the information
     */
    void update(Buckets buckets);

    /**
     * Get by position {@link Buckets}
     * @param raffleId to represent that current raffle
     * @param bucketId to represent the bucket within the raffle
     * @return buckets found based on parameters {@link Buckets}
     */
    Buckets getByCriteria(Integer raffleId, Integer bucketId);

    Buckets getWinner(Integer raffleId, Integer bucketId);

}
