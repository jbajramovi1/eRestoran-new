package repositories;

import com.google.inject.ImplementedBy;

import repositories.exceptions.RepositoryException;

/**
 * The interface Base repository.
 *
 * @param <M> the type parameter
 */
@ImplementedBy(BaseRepositoryImplementation.class)
public interface BaseRepository<M> {
    /**
     * Find by id m.
     *
     * @param id the id
     * @return the m
     */
    M findById(Long id);

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