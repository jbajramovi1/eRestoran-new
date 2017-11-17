package repositories;

import com.google.inject.ImplementedBy;
import models.Restaurant;

/**
 * The interface Restaurant repository.
 */
@ImplementedBy(RestaurantRepositoryImplementation.class)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
}
