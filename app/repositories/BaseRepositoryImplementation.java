package repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import play.db.jpa.JPA;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import play.db.jpa.JPA;

import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * The type Base repository implementation.
 *
 * @param <T> the type parameter
 * @param <M> the type parameter
 */
public class BaseRepositoryImplementation<T, M> implements BaseRepository<T, M> {

	private final Logger logger = LoggerFactory.getLogger(BaseRepositoryImplementation.class);


	public M findById(T id) {
		return JPA.em().find(getParameterizedClassId(), id);
	}

	public void create(M model) throws RepositoryException {
		try {
			JPA.em().persist(model);
			JPA.em().flush();
		} catch (PersistenceException e) {
			logger.error("Failed to create model of type ".concat(getParameterizedClassModel().getSimpleName()), e);
			throw new RepositoryException(e);
		}
	}

	public void update(M model) throws RepositoryException {
		try {
			JPA.em().merge(model);
			JPA.em().flush();
		} catch (PersistenceException e) {
			logger.error("Failed to update model of type ".concat(getParameterizedClassModel().getSimpleName()), e);
			throw new RepositoryException(e);
		}
	}

	public void delete(M model) throws RepositoryException {
		try {
			JPA.em().remove(model);
			JPA.em().flush();
		} catch (PersistenceException e) {
			logger.error("Failed to delete model of type ".concat(getParameterizedClassModel().getSimpleName()), e);
			throw new RepositoryException(e);
		}
	}

	/**
	 * Gets base criteria.
	 *
	 * @return the base criteria
	 */
	protected Criteria getBaseCriteria() {
		Session session = ((HibernateEntityManager) JPA.em()).getSession();
		return session.createCriteria(getParameterizedClassModel());
	}

	/**
	 * Gets session.
	 *
	 * @return the session
	 */
	public Session getSession() {
		return JPA.em().unwrap(Session.class);
	}

	private Class<M> getParameterizedClassId() {
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private Class<M> getParameterizedClassModel() {
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
}
