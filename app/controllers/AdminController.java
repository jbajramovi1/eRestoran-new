package controllers;


import models.Account;
import models.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.RestaurantService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import static play.mvc.Results.*;

/**
 * The type Admin controller.
 */
@Security.Authenticated(SecuredAdmin.class)
public class AdminController {

    /**
     * The Form factory.
     */
    protected FormFactory formFactory;
    /**
     * The Logger.
     */
    final Logger logger = LoggerFactory.getLogger(AccountController.class);
    /**
     * The Restaurant Service.
     */
    protected RestaurantService restaurantService;
    /**
     * The Account Service.
     */
    protected AccountService accountService;

    /**
     * Sets form factory.
     *
     * @param formFactory the form factory
     */
    @Inject
    public void setFormFactory(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * Sets restaurantService.
     *
     * @param service the service
     */
    @Inject
    public void setRestaurantService(RestaurantService service) {
        this.restaurantService = service;
    }

    /**
     * Sets accountService.
     *
     * @param service the service
     */
    @Inject
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }

    /**
     * Create new restaurant.
     *
     * @return the result
     */
    @Transactional
    public Result insertRestaurant() {

        try {
            Form<Restaurant> form = formFactory.form(Restaurant.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Restaurant insert attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(restaurantService.create(form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@insertRestaurant", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@insertRestaurant", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@insertRestaurant"));
        }
    }

    /**
     * Insert account result.
     *
     * @return the result
     */
    @Transactional
    public Result insertAccount() {

        try {

            Form<Account> form = formFactory.form(Account.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Restaurant insert attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(accountService.create(form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@insertAccount", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@insertAccount", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@insertAccount"));
        }
    }

    /**
     * Delete restaurant result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result deleteRestaurant(Long id) {
        try {

            restaurantService.delete(id);
            return ok();
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@deleteRestaurant", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@deleteRestaurant", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@deleteRestaurant"));
        }
    }

    /**
     * Delete account result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result deleteAccount(Long id) {
        try {

            accountService.delete(id);
            return ok();
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@deleteAccount", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@deleteAccount", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@deleteAccount"));
        }
    }


    /**
     * Update restaurant result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result updateRestaurant(Long id) {

        try {


            Form<Restaurant> form = formFactory.form(Restaurant.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Restaurant update attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(restaurantService.update(id, form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@updateRestaurant", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@insertRestaurant", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@updateRestaurant"));
        }
    }


    /**
     * Update account result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result updateAccount(Long id) {

        try {


            Form<Account> form = formFactory.form(Account.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Restaurant update attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(accountService.update(id, form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AdminController@updateAccount", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AdminController@insertAccount", e);
            return internalServerError(Json.toJson("Internal server error in AdminController@updateAccount"));
        }
    }
}
