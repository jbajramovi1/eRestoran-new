package services;

import models.Reservation;
import models.RestaurantTable;
import models.Result;
import play.mvc.Http;
import repositories.ReservationRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.util.List;

/**
 * The type Reservation service.
 */
public class ReservationService extends BaseService<Reservation, ReservationRepository> {
    private AccountService accountService;

    /**
     * Sets account service.
     *
     * @param service the service
     */
    @Inject
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }

    /**
     * Create reservation.
     *
     * @param model   the model
     * @param session the session
     * @return the reservation
     * @throws ServiceException the service exception
     */
    public Reservation create(Reservation model, Http.Session session) throws ServiceException {
        try {
            model.setAccount(accountService.getCurrentUser(session.get("username")));
            repository.create(model);
            return model;
        } catch (RepositoryException e) {
            logger.error("Repository exception in ReservationService@create", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    /**
     * Gets by table and date.
     *
     * @param restaurantTable the restaurant table
     * @param reservation     the reservation
     * @return the by table and date
     * @throws ServiceException the service exception
     */
    public Reservation getByTableAndDate(RestaurantTable restaurantTable, Reservation reservation) throws ServiceException {
        return repository.getByTableAndDate(restaurantTable, reservation);
    }

    /**
     * Clear reservations.
     *
     * @throws ServiceException the service exception
     */
    public void clearReservations() throws ServiceException {
        try {
            List<Reservation> oldReservations = repository.getOldReservations(4);
            for (Reservation reservation : oldReservations) {
                repository.delete(reservation);
            }
        } catch (RepositoryException e) {
            logger.error("Repository exception in ReservationService@clearReservations", e);
            throw new ServiceException("Service couldn't execute update.", e);

        }
    }

    /**
     * Return tables result.
     *
     * @param restaurantTables the restaurant tables
     * @return the result
     * @throws ServiceException the service exception
     */
    public Result returnTables(List<RestaurantTable> restaurantTables) throws ServiceException {
        try {
            return repository.returnTables(restaurantTables);
        } catch (RepositoryException e) {
            logger.error("Repository exception in ReservationService@returnTables", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }
}
