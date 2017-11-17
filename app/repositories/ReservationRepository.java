package repositories;

import com.google.inject.ImplementedBy;
import models.Reservation;

/**
 * The interface Reservation repository.
 */
@ImplementedBy(ReservationRepositoryImplementation.class)
public interface ReservationRepository extends BaseRepository<Reservation> {
}
