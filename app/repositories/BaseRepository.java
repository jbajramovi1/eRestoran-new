package repositories;

import com.google.inject.ImplementedBy;

/**
 * The interface Base repository.
 *
 * @param <T> the type parameter
 * @param <M> the type parameter
 */
@ImplementedBy(BaseRepositoryImplementation.class)
public interface BaseRepository<T, M> {

	/**
	 * Find by id m.
	 *
	 * @param id the id
	 * @return the m
	 */
	M findById(T id);

	/**
	 * Create.
	 *
	 * @param model the model
	 * @throws RepositoryException the repository exception
	 */
	void create(M model) throws RepositoryException;

	/**
	 * Update.
	 *
	 * @param model the model
	 * @throws RepositoryException the repository exception
	 */
	void update(M model) throws RepositoryException;

	/**
	 * Delete.
	 *
	 * @param model the model
	 * @throws RepositoryException the repository exception
	 */
	void delete(M model) throws RepositoryException;
}
