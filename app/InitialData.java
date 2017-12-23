
import models.*;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPAApi;
import services.*;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Random;


/**
 * The type Initial data.
 */
@Singleton
public class InitialData {

    /**
     * The Logger.
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(InitialData.class);

    /**
     * Instantiates a new Initial data.
     *
     * @param api                    the api
     * @param accountService         the account service
     * @param restaurantService      the restaurant service
     * @param restaurantTableService the restaurant table service
     * @param menuService            the menu service
     * @param locationService        the location service
     */
    @Inject
    public InitialData(final JPAApi api, final AccountService accountService, final RestaurantService restaurantService, final RestaurantTableService restaurantTableService, final MenuService menuService, final LocationService locationService) {
        api.withTransaction(() -> {
            insertUsers(accountService);
            insertRestaurants(restaurantService);
            insertTables(restaurantTableService, restaurantService);
            insertMenus(menuService, restaurantService);
            insertLocation(locationService);
        });

    }

    private void insertUsers(AccountService service) {
        try {
            if (service.hasData()) return;
            Account acc1 = new Account();
            acc1.setEmail("admin@abhintern.ba");
            acc1.setPassword("pass");
            acc1.setRole(Role.ADMIN);
            Account acc2 = new Account();
            acc2.setEmail("jasmina@abhintern.ba");
            acc2.setPassword("pass");
            acc2.setRole(Role.USER);
            service.create(acc1);
            service.create(acc2);
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@insertUsers", e);
        }
    }

    private void insertRestaurants(RestaurantService service) {
        try {
            Random rand = new Random();
            if (service.hasData()) return;
            String description = "You may be a person who loves to cook. You absolutely love to spend time in your kitchen. As a result, you like to outfit your kitchen with the latest and with the best cookware that is available on the market. However, and with that said, if you are like most people in the 21st century, you also live your life on a budget. You simply do not have a great deal of extra money to blow and spend at will. Therefore, with the goal of outfitting your kitchen with the latest, with the best in cookware, you also want to keep your eyes on ways in which you can save money on your cookware purchases. To this end, you might want to focus your attention on the Internet and the World Wide Web, specifically on overstock merchandise sites that are springing up all over the Net.'";
            String[] categories={"International","Indian | International","Mediterranean | International","Mediterranean | International","International","International"};
            for (int i=0;i<6;i++){
                Restaurant restaurant=new Restaurant();
                restaurant.setName("Restaurant "+(i+1));
                restaurant.setDescription(description);
                restaurant.setMark(rand.nextInt(5) + 1.);
                restaurant.setVotes(rand.nextInt(150)+50L);
                restaurant.setPriceRange(rand.nextInt(5)+20L);
                restaurant.setCategory(categories[i]);
                restaurant.setImageFileName("restaurant"+(i+1));
                restaurant.setCoverFileName("restaurantcover"+(i+1));
                service.create(restaurant);
            }

        } catch (ServiceException e) {
            logger.error("Service error in InitialData@insertRestaurants", e);
        }
    }

    private void insertTables(RestaurantTableService service, RestaurantService restaurantService) {
        try {
            Random rand = new Random();
            if (service.hasData()) return;
            List<Restaurant> restaurants = restaurantService.findAll();
            for (int i = 0; i < 20; i++) {
                RestaurantTable table = new RestaurantTable();
                table.setSittingPlaces(rand.nextInt(8) + 2);
                table.setRestaurant(restaurants.get(rand.nextInt(restaurants.size() - 1)));
                service.create(table);
            }
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@insertTables", e);
        }
    }

    private void insertMenus(MenuService service, RestaurantService restaurantService) {
        try {
            Random rand = new Random();
            if (service.hasData()) return;
            List<Restaurant> restaurants = restaurantService.findAll();
            for (int i = 0; i < 20; i++) {
                Menu menu = new Menu();
                menu.setName("Menu " + (i + 1));
                menu.setRestaurant(restaurants.get(rand.nextInt(restaurants.size() - 1)));
                service.create(menu);
            }
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@insertTables", e);
        }
    }

    private void insertLocation(LocationService service) {
        try {
            Random rand = new Random();
            if (service.hasData()) return;
            for (int i = 0; i < 20; i++) {
                Location location = new Location();
                location.setName("Location " + (i + 1));
                location.setLongitude(-180F + rand.nextFloat() * (180F - (-180F)));
                location.setLatitude(-180F + rand.nextFloat() * (180F - (-180F)));
                service.create(location);
            }
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@insertTables", e);
        }
    }


}
