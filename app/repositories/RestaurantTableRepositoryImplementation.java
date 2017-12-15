package repositories;

import models.Reservation;
import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * The type RestaurantTable repository implementation.
 */
public class RestaurantTableRepositoryImplementation extends BaseRepositoryImplementation<RestaurantTable> implements RestaurantTableRepository {
    public List<RestaurantTable> getByRestaurant(Restaurant restaurant){
        return (List<RestaurantTable>) getBaseCriteria()
                .add(Restrictions.eq("restaurant", restaurant))
                .addOrder(Order.asc("sittingPlaces"))
                .list();
    }

    public List<RestaurantTable> getByRestaurantAndSeats(Reservation reservation){
        return (List<RestaurantTable>) getBaseCriteria()
                .add(Restrictions.eq("restaurant", reservation.getRestaurant()))
                .add(Restrictions.eq("sittingPlaces", reservation.getTables())).list();
    }
}