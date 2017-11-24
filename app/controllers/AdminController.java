package controllers;

import models.Account;
import models.Restaurant;
import models.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import services.AccountService;
import play.mvc.Http.Session;
import services.RestaurantService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;


public class AdminController {

    protected FormFactory formFactory;
    /**
     * The Logger.
     */
    final Logger logger = LoggerFactory.getLogger(AccountController.class);
    /**
     * Sets form factory.
     *
     * @param formFactory the form factory
     */
    @Inject
    public void setFormFactory(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    @Transactional
    public Result insertRestaurant() {

        try {

            RestaurantService service=new RestaurantService();

            if (session().isEmpty()){
                logger.error("No privilegies to access admin panel");
                return badRequest("No privilegies to access admin panel");
            }
            AccountService accountService=new AccountService();
            Account sessionAccount=accountService.getCurrentUser(session().get("username"));
            if (sessionAccount==null || sessionAccount.getRole()!= Role.ADMIN){
                logger.error("No privilegies to access admin panel");
                return badRequest("No privilegies to access admin panel");
            }

            Form<Restaurant> form = formFactory.form(Restaurant.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Restaurant insert attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(service.create(form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@insertRestaurant", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@insertRestaurant", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@insertRestaurant"));
        }
    }
}
