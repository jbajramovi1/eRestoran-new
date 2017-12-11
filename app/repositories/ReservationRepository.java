package repositories;

import com.google.inject.ImplementedBy;
import models.Reservation;
import models.RestaurantTable;
import repositories.exceptions.RepositoryException;

import java.util.List;

/**
 * The interface Reservation repository.
 */
@ImplementedBy(ReservationRepositoryImplementation.class)
public interface ReservationRepository extends BaseRepository<Reservation> {
    Reservation getByTableAndDate(RestaurantTable restaurantTable,Reservation reservation);
    List<Reservation> getOldReservations() throws RepositoryException;
}
