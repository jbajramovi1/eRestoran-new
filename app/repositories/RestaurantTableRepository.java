package repositories;

import com.google.inject.ImplementedBy;
import models.Restaurant;
import models.RestaurantTable;
import services.exceptions.ServiceException;

import java.util.List;

/**
 * The interface RestaurantTable repository.
 */
@ImplementedBy(RestaurantTableRepositoryImplementation.class)
public interface RestaurantTableRepository extends BaseRepository<RestaurantTable> {
    List<RestaurantTable> getByRestaurant(Restaurant restaurant);
}
