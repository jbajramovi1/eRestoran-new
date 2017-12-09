package controllers;

import models.Reservation;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import services.ReservationService;
import services.exceptions.ServiceException;

/**
 * The type Reservation controller.
 */
@Security.Authenticated(Secured.class)
public class ReservationController extends BaseController<Reservation, ReservationService>{
    @Transactional
    @Override
    public Result create() {
        try {
            Form<Reservation> form = formFactory.form(Reservation.class).bindFromRequest();
            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }
            if (session().isEmpty()) {
                return badRequest("Login to leave a reservation");
            }
            return ok(Json.toJson(service.create(form.get(),session())));
        } catch (ServiceException e) {
            logger.error("Service error in ReservationController@create",e);
            return badRequest("Service error in ReservationController@create");
        } catch (Exception e) {
            logger.error("Internal server error in ReservationController@create",e);
            return internalServerError("Internal server error in ReservationController@create");
        }
    }
}
