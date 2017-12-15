package services;

import models.Reservation;
import models.ResponseType;
import models.RestaurantTable;
import models.Result;
import repositories.ReservationRepository;
import play.mvc.Http;
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
    public void setAccountService(AccountService service) { this.accountService=service;}

    /**
     * Create reservation.
     *
     * @param model   the model
     * @param session the session
     * @return the reservation
     * @throws ServiceException the service exception
     */
    public Result create(Reservation model, Http.Session session) throws ServiceException{
        try {
            model.setAccount(accountService.getCurrentUser(session.get("username")));
            repository.create(model);
            return new Result(ResponseType.SUCCESS,model);
        } catch (RepositoryException e) {
            logger.error("Repository exception in ReservationService@create", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    public Reservation getByTableAndDate(RestaurantTable restaurantTable,Reservation reservation) throws ServiceException{
            return repository.getByTableAndDate(restaurantTable,reservation);
    }

    public void clearReservations() throws ServiceException{
        try{
            List<Reservation> oldReservations=repository.getOldReservations();
            for (Reservation reservation:oldReservations){
                repository.delete(reservation);
            }
        } catch (RepositoryException e){
            logger.error("Repository exception in ReservationService@clearReservations", e);
            throw new ServiceException("Service couldn't execute update.", e);

        }
    }

    public Result returnTables(List<RestaurantTable> restaurantTables) throws ServiceException{
        try{
            return repository.returnTables(restaurantTables);
        } catch (RepositoryException e) {
            logger.error("Repository exception in ReservationService@returnTables", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }
}
