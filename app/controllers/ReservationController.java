package controllers;

import akka.http.javadsl.model.ResponseEntity;
import models.Reservation;
import models.RestaurantTable;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import services.ReservationService;
import services.RestaurantTableService;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Reservation controller.
 */
//@Security.Authenticated(Secured.class)
public class ReservationController extends BaseController<Reservation, ReservationService>{
    private RestaurantTableService restaurantTableService;

    /**
     * Sets RestaurantTable service.
     *
     * @param service the service
     */
    @Inject
    public void setRestaurantTableService(RestaurantTableService service) { this.restaurantTableService=service;}

    @Transactional
    @Override
    public Result create() {
        try {
            Form<Reservation> form = formFactory.form(Reservation.class).bindFromRequest();
            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }
            List<RestaurantTable> tablesMatchingSeats=restaurantTableService.getByRestaurantAndSeats(form.get());
            for (RestaurantTable restaurantTable:tablesMatchingSeats
                 ) {
                if (service.getByTableAndDate(restaurantTable,form.get())==null) {
                    form.get().setRestaurantTable(restaurantTable);
                    return ok(Json.toJson(service.create(form.get(),session())));
                }
            }
            List<RestaurantTable> tablesInRestaurant=restaurantTableService.getByRestaurant(form.get().getRestaurant());
            ArrayList<RestaurantTable> availableTables=new ArrayList<RestaurantTable>();
            for (RestaurantTable restaurantTable:tablesInRestaurant
                    ) {
                if (service.getByTableAndDate(restaurantTable,form.get())==null && restaurantTable.getSittingPlaces()>form.get().getTables()) {
                    availableTables.add(restaurantTable);
                }
            }
            return badRequest(Json.toJson(availableTables));

        } catch (ServiceException e) {
            logger.error("Service error in ReservationController@create",e);
            return badRequest("Service error in ReservationController@create");
        } catch (Exception e) {
            logger.error("Internal server error in ReservationController@create",e);
            return internalServerError("Internal server error in ReservationController@create");
        }
    }


}
