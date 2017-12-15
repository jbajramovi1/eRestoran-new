package repositories;

import models.FilterResult;
import models.Restaurant;
import models.filters.RestaurantFilterModel;
import org.hibernate.Criteria;
import repositories.exceptions.RepositoryException;

import javax.persistence.PersistenceException;

/**
 * The type Restaurant repository implementation.
 */
public class RestaurantRepositoryImplementation extends BaseRepositoryImplementation<Restaurant> implements RestaurantRepository {
    public FilterResult<Restaurant> filter(RestaurantFilterModel restaurantFilterModel) throws RepositoryException{
        try {
            Criteria criteria = restaurantFilterModel.addConditions(getBaseCriteria());
            FilterResult<Restaurant> result=new FilterResult<Restaurant>();
            result.setData(criteria.list());
            result.setCount(count()/restaurantFilterModel.getPageSize());
            result.setPageSize(restaurantFilterModel.getPageSize());
            result.setPageNumber(restaurantFilterModel.getPageNumber());
            return result;
        } catch(PersistenceException e) {
            throw new RepositoryException("RestaurantRepository couldn't filter restaurants.", e);
        }
    }
}
