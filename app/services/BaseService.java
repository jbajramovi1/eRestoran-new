package services;

import models.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.BaseRepository;
import repositories.RepositoryException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The type Base service.
 *
 * @param <T> the type parameter
 * @param <M> the type parameter
 * @param <R> the type parameter
 */
@Singleton
public abstract class BaseService<T, M extends BaseModel<T, M>, R extends BaseRepository<T, M>> {

	/**
	 * The Repository.
	 */
	protected R repository;
	private final Logger logger = LoggerFactory.getLogger(BaseService.class);

	/**
	 * Sets repository.
	 *
	 * @param repository the repository
	 */
	@Inject
	public void setRepository(R repository) {
		this.repository = repository;
	}

	/**
	 * Get model.
	 *
	 * @param id the id
	 * @return the m
	 * @throws ServiceException the service exception
	 */
	public M get(T id) throws ServiceException {

		M model = repository.findById(id);

		if (model != null) {
			return model;
		}

		final String message = String.format("Failed to fetch model with id %s", id);
		logger.error(message);
		throw new ServiceException(message);
	}

	/**
	 * Create model.
	 *
	 * @param model the model
	 * @return the m
	 * @throws ServiceException the service exception
	 */
	public M create(M model) throws ServiceException {
		try {
			repository.create(model);
			return model;
		} catch (RepositoryException e) {
			logger.error("Failed to save model of type", model.getClass().getSimpleName(), e);
			throw new ServiceException(e);
		}
	}
}
