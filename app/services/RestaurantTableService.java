package services;

import models.Restaurant;
import models.RestaurantTable;
import repositories.ReservationRepository;
import repositories.RestaurantTableRepository;
import services.exceptions.ServiceException;

import java.util.List;

/**
 * The type RestaurantTable service.
 */
public class RestaurantTableService extends BaseService<RestaurantTable, RestaurantTableRepository> {
    /**
     * Gets by restaurant.
     *
     * @param data the data
     * @return the by restaurant
     * @throws ServiceException the service exception
     */
    public List<RestaurantTable> getByRestaurant(Restaurant data) throws ServiceException {
        List<RestaurantTable> tables = repository.getByRestaurant(data);
        if (tables == null) {
            throw new ServiceException("Entity not found");
        }
        return tables;
    }
}

