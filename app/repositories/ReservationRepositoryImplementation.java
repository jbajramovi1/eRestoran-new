package repositories;

import models.Reservation;
import models.RestaurantTable;
import org.hibernate.criterion.Restrictions;

/**
 * The type Reservation repository implementation.
 */
public class ReservationRepositoryImplementation extends BaseRepositoryImplementation<Reservation> implements ReservationRepository {
    @Override
    public Reservation getByTableAndDate(RestaurantTable restaurantTable, Reservation reservation) {
        return (Reservation)getBaseCriteria()
                .add(Restrictions.eq("restaurantTable",restaurantTable))
                .add(Restrictions.eq("reservationDate",reservation.getReservationDate())).uniqueResult();
    }
}
