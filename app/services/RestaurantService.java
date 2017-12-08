package services;

import models.FilterResult;
import models.Restaurant;
import models.filters.RestaurantFilterModel;
import repositories.RestaurantRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;

import java.util.List;

/**
 * The type Restaurant service.
 */
public class RestaurantService extends BaseService<Restaurant, RestaurantRepository> {

    public FilterResult<Restaurant> filter(RestaurantFilterModel restaurantFilterModel) throws ServiceException {
        try {
            return repository.filter(restaurantFilterModel);
        } catch(RepositoryException e) {

            throw new ServiceException("RestaurantService couldn't filter restaurants.", e);
        }
    }

}
