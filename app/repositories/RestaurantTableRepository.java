package repositories;

import com.google.inject.ImplementedBy;
import models.Reservation;
import models.Restaurant;
import models.RestaurantTable;

import java.util.List;

/**
 * The interface RestaurantTable repository.
 */
@ImplementedBy(RestaurantTableRepositoryImplementation.class)
public interface RestaurantTableRepository extends BaseRepository<RestaurantTable> {
    /**
     * Gets by restaurant.
     *
     * @param restaurant the restaurant
     * @return the by restaurant
     */
    List<RestaurantTable> getByRestaurant(Restaurant restaurant);

    /**
     * Gets by restaurant and seats.
     *
     * @param reservation the reservation
     * @return the by restaurant and seats
     */
    List<RestaurantTable> getByRestaurantAndSeats(Reservation reservation);
}
