package repositories;

import com.google.inject.ImplementedBy;
import models.RestaurantTable;

/**
 * The interface RestaurantTable repository.
 */
@ImplementedBy(RestaurantTableRepositoryImplementation.class)
public interface RestaurantTableRepository extends BaseRepository<RestaurantTable> {
}
