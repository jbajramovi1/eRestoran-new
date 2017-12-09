package repositories;

import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * The type RestaurantTable repository implementation.
 */
public class RestaurantTableRepositoryImplementation extends BaseRepositoryImplementation<RestaurantTable> implements RestaurantTableRepository {
    public List<RestaurantTable> getByRestaurant(Restaurant restaurant){

        return (List<RestaurantTable>) getBaseCriteria()
                .add(Restrictions.eq("restaurant", restaurant)).list();
    }
}