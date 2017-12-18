package repositories;

import com.google.inject.ImplementedBy;
import models.Reservation;
import models.RestaurantTable;
import models.Result;
import repositories.exceptions.RepositoryException;

import java.util.List;

/**
 * The interface Reservation repository.
 */
@ImplementedBy(ReservationRepositoryImplementation.class)
public interface ReservationRepository extends BaseRepository<Reservation> {
    /**
     * Gets by table and date.
     *
     * @param restaurantTable the restaurant table
     * @param reservation     the reservation
     * @return the by table and date
     */
    Reservation getByTableAndDate(RestaurantTable restaurantTable, Reservation reservation);

    /**
     * Gets old reservations.
     *
     * @param hours the hours
     * @return the old reservations
     * @throws RepositoryException the repository exception
     */
    List<Reservation> getOldReservations(int hours) throws RepositoryException;

    /**
     * Return tables result.
     *
     * @param restaurantTables the restaurant tables
     * @return the result
     * @throws RepositoryException the repository exception
     */
    Result returnTables(List<RestaurantTable> restaurantTables) throws RepositoryException;

}
