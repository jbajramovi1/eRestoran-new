package repositories;

import com.google.inject.ImplementedBy;
import models.FilterResult;
import models.Restaurant;
import models.filters.RestaurantFilterModel;
import repositories.exceptions.RepositoryException;

/**
 * The interface Restaurant repository.
 */
@ImplementedBy(RestaurantRepositoryImplementation.class)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    /**
     * Filter filter result.
     *
     * @param restaurantFilterModel the restaurant filter model
     * @return the filter result
     * @throws RepositoryException the repository exception
     */
    FilterResult<Restaurant> filter(RestaurantFilterModel restaurantFilterModel) throws RepositoryException;
}
