package services;

import models.Reservation;
import models.RestaurantTable;
import repositories.ReservationRepository;
import play.mvc.Http;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;

import javax.inject.Inject;

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
    public void setAccountService(AccountService service) { this.accountService=service;}

    /**
     * Create reservation.
     *
     * @param model   the model
     * @param session the session
     * @return the reservation
     * @throws ServiceException the service exception
     */
    public Reservation create(Reservation model, Http.Session session) throws ServiceException{
        try {
            model.setAccount(accountService.getCurrentUser(session.get("username")));
            repository.create(model);
            return model;
        } catch (RepositoryException e) {
            logger.error("Repository exception in CommentService@create", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    public Reservation getByTableAndDate(RestaurantTable restaurantTable,Reservation reservation) throws ServiceException{
            return repository.getByTableAndDate(restaurantTable,reservation);
    }
}
