package repositories;

import com.google.inject.ImplementedBy;
import models.FilterResult;
import models.Restaurant;
import models.filters.RestaurantFilterModel;
import repositories.exceptions.RepositoryException;

import java.util.List;
import java.util.logging.Filter;

/**
 * The interface Restaurant repository.
 */
@ImplementedBy(RestaurantRepositoryImplementation.class)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    FilterResult<Restaurant> filter(RestaurantFilterModel restaurantFilterModel) throws RepositoryException;
}
