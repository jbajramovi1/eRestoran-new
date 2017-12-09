package repositories;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPAApi;
import repositories.exceptions.RepositoryException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * The type Base repository implementation.
 *
 * @param <M> the type parameter
 */
public class BaseRepositoryImplementation<M> implements BaseRepository<M> {
    /**
     * The Logger.
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseRepositoryImplementation.class);
    private JPAApi jpaApi;
    @Inject
    public void setJpaApi(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }



    private Class<M> getParameterizedClass() {
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public M findById(Long id) {
        return jpaApi.em().find(getParameterizedClass(), id);
    }

    /**
     * Gets base criteria.
     *
     * @return the base criteria
     */
    protected Criteria getBaseCriteria() {
        Session session = ((HibernateEntityManager) jpaApi.em()).getSession();
        return session.createCriteria(getParameterizedClass());
    }

    /**
     * Gets session.
     *
     * @return the session
     */
    public Session getSession() {
        return jpaApi.em().unwrap(Session.class);
    }

    public void create(M model) throws RepositoryException {
        try {
            jpaApi.em().persist(model);
            jpaApi.em().flush();
        } catch (PersistenceException e) {
            logger.error("ServiceException in BaseRepository@create", e);
            throw new RepositoryException(e.toString());
        }
    }

    public void update(M model) throws RepositoryException {
        try {
            jpaApi.em().merge(model);
            jpaApi.em().flush();
        } catch (PersistenceException e) {
            logger.error("ServiceException in BaseRepository@update", e);
            throw new RepositoryException(e.toString());
        }
    }

    public void delete(M model) throws RepositoryException {
        try {
            jpaApi.em().remove(model);
            jpaApi.em().flush();
        } catch (PersistenceException e) {
            logger.error("ServiceException in BaseRepository@delete", e);
            throw new RepositoryException(e.toString());
        }
    }

    public List<M> findAll() throws RepositoryException{
        try{
            return getBaseCriteria().list();
        }  catch (PersistenceException e){
            logger.error("ServiceException in BaseRepository@findAll", e);
            throw new RepositoryException(e.toString());
        }
    }


    public Integer count() throws RepositoryException{
        try{
            return ((Number)getBaseCriteria().setProjection(Projections.rowCount()).list().get(0)).intValue();
        } catch (PersistenceException e){
            logger.error("ServiceException in BaseRepository@count", e);
            throw new RepositoryException(e.toString());
        }

    }


    public boolean hasData() throws RepositoryException{
        try{
            return ((Number)getBaseCriteria().setProjection(Projections.rowCount()).list().get(0)).intValue()!=0;

        } catch (PersistenceException e){
            logger.error("ServiceException in BaseRepository@hasData", e);
            throw new RepositoryException(e.toString());
        }
    }

}
