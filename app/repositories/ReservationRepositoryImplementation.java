package repositories;

import akka.http.impl.util.JavaMapping;
import models.Reservation;
import models.ResponseType;
import models.RestaurantTable;
import models.Result;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import repositories.exceptions.RepositoryException;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Reservation> getOldReservations() throws RepositoryException{

        try {
            DateTime dateTime = new DateTime().minusHours(4);
            Date date=dateTime.toDate();
            return (List<Reservation>)getBaseCriteria()
                    .add(Restrictions.le("reservationDate",date)).list();

        }catch (PersistenceException e) {
            logger.error("ServiceException in ReservationRepository@clearReservations", e);
            throw new RepositoryException(e.toString());
        }
    }

    public Result returnTables(List<RestaurantTable> restaurantTables) throws RepositoryException{
        try{
            Result result=new Result(ResponseType.OPTIONAL,restaurantTables);
            return result;
        } catch(PersistenceException e) {
            throw new RepositoryException("ReservationRepository couldn't get available tables.", e);
        }
    }
}
