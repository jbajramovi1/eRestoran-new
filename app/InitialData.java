
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
            if (service.hasData()) return;
            String description = "You may be a person who loves to cook. You absolutely love to spend time in your kitchen. As a result, you like to outfit your kitchen with the latest and with the best cookware that is available on the market. However, and with that said, if you are like most people in the 21st century, you also live your life on a budget. You simply do not have a great deal of extra money to blow and spend at will. Therefore, with the goal of outfitting your kitchen with the latest, with the best in cookware, you also want to keep your eyes on ways in which you can save money on your cookware purchases. To this end, you might want to focus your attention on the Internet and the World Wide Web, specifically on overstock merchandise sites that are springing up all over the Net.'";
            Restaurant restaurant1 = new Restaurant();
            restaurant1.setName("Restaurant 1");
            restaurant1.setDescription(description);
            restaurant1.setMark(3D);
            restaurant1.setVotes(100L);
            restaurant1.setPriceRange(8L);
            restaurant1.setImageFileName("/assets/images/restaurant-abe48bd47b7ba31c6934481b714d9dc8.jpg");
            restaurant1.setCoverFileName("x");
            restaurant1.setCategory("International");

            Restaurant restaurant2 = new Restaurant();
            restaurant2.setName("Restaurant 2");
            restaurant2.setDescription(description);
            restaurant2.setMark(4D);
            restaurant2.setVotes(200L);
            restaurant2.setPriceRange(10L);
            restaurant2.setImageFileName("/assets/images/restaurant2-9de29a7635b8f54f880f12517e3bec87.jpg");
            restaurant2.setCoverFileName("x");
            restaurant2.setCategory("Indian | International");

            Restaurant restaurant3 = new Restaurant();
            restaurant3.setName("Restaurant 3");
            restaurant3.setDescription(description);
            restaurant3.setMark(2D);
            restaurant3.setVotes(300L);
            restaurant3.setPriceRange(29L);
            restaurant3.setImageFileName("/assets/images/restaurant3-90aefa27e1c5b11065c78f14ad8cdf2d.jpg");
            restaurant3.setCoverFileName("x");
            restaurant3.setCategory("Italian | International");

            Restaurant restaurant4 = new Restaurant();
            restaurant4.setName("Restaurant 4");
            restaurant4.setDescription(description);
            restaurant4.setMark(2D);
            restaurant4.setVotes(120L);
            restaurant4.setPriceRange(40L);
            restaurant4.setImageFileName("/assets/images/restaurant4-67524742acc849a2faf92c15476f3127.jpg");
            restaurant4.setCoverFileName("x");
            restaurant4.setCategory("Mediterranean | International");

            Restaurant restaurant5 = new Restaurant();
            restaurant5.setName("Restaurant 5");
            restaurant5.setDescription(description);
            restaurant5.setMark(5D);
            restaurant5.setVotes(150L);
            restaurant5.setPriceRange(50L);
            restaurant5.setImageFileName("/assets/images/restaurant5-894a242c7707f9d06e2489e5f4b0e4a4.jpg");
            restaurant5.setCoverFileName("x");
            restaurant5.setCategory("International");

            Restaurant restaurant6 = new Restaurant();
            restaurant6.setName("Restaurant 6");
            restaurant6.setDescription(description);
            restaurant6.setMark(1D);
            restaurant6.setVotes(90L);
            restaurant6.setPriceRange(65L);
            restaurant6.setImageFileName("/assets/images/restaurant6-82cc6170fdd91516dc035538518a14c2.jpg");
            restaurant6.setCoverFileName("x");
            restaurant6.setCategory("International");

            service.create(restaurant1);
            service.create(restaurant2);
            service.create(restaurant3);
            service.create(restaurant4);
            service.create(restaurant5);
            service.create(restaurant6);


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
