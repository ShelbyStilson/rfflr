package demo.DAO;

import java.util.List;

/**
 * The BaseDao provides data access methods to save and retrieve data
 */
public interface BaseDAO {


    /**
     * Get all objects of type T
     * @param type the class object of the type of object to get
     * @param orderBy field to order results by, defaults to id
     * @param order the order to return the results in asc, or desc defaults to desc
     * @param offset the amount of offset for pagination
     * @param limit the max results to be returned
     * @param <T> the object type to get
     * @return List of T
     */
    <T> List<T> getAll(final Class<T> type, final Integer orderBy, final Integer order, final Integer offset, final Integer limit);

    /**
     * Add object of type T
     * @param object the object to add
     * @param type the class object of the type to add
     * @param <T> the object type to add
     * @return the object the was added
     */
    <T> T add(final T object, final Class<T> type);

    /**
     * Get object of Type T by id
     * @param id the id of the object to get
     * @param type the class object of the type of object to getById
     * @param <T> the object type to get
     * @return the object of type T
     */
    <T> T getById(final Integer id, final Class<T> type);

    /**
     * Get by Criteria (Gets the object in the database based on given criteria)
     * @param object the object to be searched for in the database
     * @param type the class object of the type to be searched for in the database
     * @return object found based on given criteria
     */
    <T> T getByCriteria(final T object, final Class<T> type);

    /**
     * Get by Tickets (Gets the object in the database based on given criteria)
     * @param object the object to be searched for in the database
     * @param type the class object of the type to be searched for in the database
     * @return object found based on given criteria
     */
    <T> T getTickets(final T object, final Class<T> type);

    /**
     * Updates the object of Type T based on where it is located in the database
     * @param object the object to be updated in the database
     * @param type the class object of the type to be updated in the database
     * accepts object and find where in the database the object was stored and then updates the information
     */
    <T> T update(final T object, final Class<T> type);

    /**
     * Deletes the object of Type T based on where it is located in the database
     * @param object the object to be deleted in the database
     * @param type the class object of the type to be deleted in the database
     * accepts object and find where in the database the object was stored and then deletes the information
     */
    <T> T delete(final T object, final Class<T> type);

    <T> T deleteEvent(final T object, final Class<T> type);

    <T> T getWinner(final T object, final Class<T> type);

}
