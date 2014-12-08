package demo.DAO.Impl;

import demo.DAO.BaseDAO;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

/**
 * This BaseDAOImpl provides methods to support data persistence with Ibatis
 */
@SuppressWarnings("unchecked")
public abstract class BaseDAOImpl extends SqlMapClientDaoSupport  implements BaseDAO {

    /**
     * Get all fields for type
     * @param type the Class object for the type being used
     * @return ArrayList of field names
     */
    protected static ArrayList<String> getFields(Class type) {
        ArrayList<String> rval = new ArrayList<String>();

        Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {
            rval.add(field.getName());
        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    public <T> List<T> getAll(final Class<T> type, final Integer orderBy, final Integer order, final Integer offset, final Integer limit) {
        List rval = null;


        try {
            rval = getSqlMapClient().queryForList(type.getSimpleName() + ".getAll");
        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    public <T> T add(final T object, final Class<T> type) {
        T rval = null;
        Integer id = 0;

        try {
            id =  (Integer)getSqlMapClient().insert(type.getSimpleName() + ".add", object);
            rval = getById(id, type);
        } catch (DuplicateKeyException e) {

        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    public <T> T update(final T object, final Class<T> type) {
        T rval = null;
        Integer id = 0;

        try {
            id =  (Integer)getSqlMapClient().insert(type.getSimpleName() + ".update", object);
            rval = getById(id, type);
        } catch (DuplicateKeyException e) {

        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T getById(final Integer id, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".getById", id);
        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T getTickets(final T object, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".getTickets", object);
        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T getByCriteria(final T object, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".getByCriteria", object);
        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T delete(final T object, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".deletes", object);
        } catch (SQLException e) {

        }

        return rval;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T deleteEvent(final T object, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".deletes", object);
        } catch (SQLException e) {

        }

        return rval;
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T getWinner(final T object, final Class<T> type) {
        T rval = null;

        try {
            rval = (T) getSqlMapClient().queryForObject(type.getSimpleName() + ".getWinner", object);
        } catch (SQLException e) {

        }

        return rval;
    }    
}