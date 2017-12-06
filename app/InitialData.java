
import models.Account;
import models.Restaurant;
import models.Role;
import org.slf4j.LoggerFactory;

import play.Environment;
import play.api.Application;
import play.api.Configuration;
import play.api.Play;
import play.api.inject.ApplicationLifecycle;
import play.db.jpa.JPA;
import play.db.jpa.JPAApi;

import play.db.jpa.Transactional;
import play.libs.F;
import services.AccountService;
import services.RestaurantService;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class InitialData {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(InitialData.class);
    @Inject
    public InitialData (final JPAApi api, final AccountService accountService, final RestaurantService restaurantService) {
        api.withTransaction(()->{
            if (!getAccounts(accountService)) insertAdmin(accountService);
            if (!getRestaurants(restaurantService)) insertRestaurants(restaurantService);
        });

    }

    private boolean getAccounts(AccountService service){
        try{
            return (!service.findAll().isEmpty());
        }
        catch (ServiceException e){
            logger.error("Service error in InitialData@getAccounts", e);
        }
        return false;
    }

    private boolean getRestaurants(RestaurantService service){
        try{
            return (!service.findAll().isEmpty());
        }
        catch (ServiceException e){
            logger.error("Service error in InitialData@getRestaurants", e);
        }
        return false;
    }

    private void insertAdmin(AccountService service) {
        try {
            Account acc = new Account();
            acc.setEmail("admin@abhintern.ba");
            acc.setPassword("pass");
            acc.setRole(Role.ADMIN);
            service.create(acc);
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@constructor", e);
        }
    }

    private void insertRestaurants(RestaurantService service){
        try {
            String description="You may be a person who loves to cook. You absolutely love to spend time in your kitchen. As a result, you like to outfit your kitchen with the latest and with the best cookware that is available on the market. However, and with that said, if you are like most people in the 21st century, you also live your life on a budget. You simply do not have a great deal of extra money to blow and spend at will. Therefore, with the goal of outfitting your kitchen with the latest, with the best in cookware, you also want to keep your eyes on ways in which you can save money on your cookware purchases. To this end, you might want to focus your attention on the Internet and the World Wide Web, specifically on overstock merchandise sites that are springing up all over the Net.'";
            Restaurant restaurant1=new Restaurant();
            restaurant1.setName("Restaurant 1");
            restaurant1.setDescription(description);
            restaurant1.setMark(3.);
            restaurant1.setVotes(new Long(100));
            restaurant1.setPriceRange(new Long(10));
            restaurant1.setImageFileName("/assets/images/restaurant-abe48bd47b7ba31c6934481b714d9dc8.jpg");
            restaurant1.setCoverFileName("x");
            restaurant1.setCategory("International");

            Restaurant restaurant2=new Restaurant();
            restaurant2.setName("Restaurant 2");
            restaurant2.setDescription(description);
            restaurant2.setMark(4.);
            restaurant2.setVotes(new Long(100));
            restaurant2.setPriceRange(new Long(10));
            restaurant2.setImageFileName("/assets/images/restaurant2-9de29a7635b8f54f880f12517e3bec87.jpg");
            restaurant2.setCoverFileName("x");
            restaurant2.setCategory("Indian | International");

            Restaurant restaurant3=new Restaurant();
            restaurant3.setName("Restaurant 3");
            restaurant3.setDescription(description);
            restaurant3.setMark(2.);
            restaurant3.setVotes(new Long(100));
            restaurant3.setPriceRange(new Long(10));
            restaurant3.setImageFileName("/assets/images/restaurant3-90aefa27e1c5b11065c78f14ad8cdf2d.jpg");
            restaurant3.setCoverFileName("x");
            restaurant3.setCategory("Italian | International");

            Restaurant restaurant4=new Restaurant();
            restaurant4.setName("Restaurant 4");
            restaurant4.setDescription(description);
            restaurant4.setMark(2.);
            restaurant4.setVotes(new Long(100));
            restaurant4.setPriceRange(new Long(10));
            restaurant4.setImageFileName("/assets/images/restaurant4-67524742acc849a2faf92c15476f3127.jpg");
            restaurant4.setCoverFileName("x");
            restaurant4.setCategory("Mediterranean | International");

            Restaurant restaurant5=new Restaurant();
            restaurant5.setName("Restaurant 5");
            restaurant5.setDescription(description);
            restaurant5.setMark(5.);
            restaurant5.setVotes(new Long(100));
            restaurant5.setPriceRange(new Long(10));
            restaurant5.setImageFileName("/assets/images/restaurant5-894a242c7707f9d06e2489e5f4b0e4a4.jpg");
            restaurant5.setCoverFileName("x");
            restaurant5.setCategory("International");

            Restaurant restaurant6=new Restaurant();
            restaurant6.setName("Restaurant 6");
            restaurant6.setDescription(description);
            restaurant6.setMark(1.);
            restaurant6.setVotes(new Long(100));
            restaurant6.setPriceRange(new Long(10));
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
            logger.error("Service error in InitialData@constructor", e);
        }
    }


}
