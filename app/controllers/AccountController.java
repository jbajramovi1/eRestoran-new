package controllers;

import models.Account;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import services.AccountService;
import services.exceptions.ServiceException;

/**
 * The type Account controller.
 */
public class AccountController extends BaseController<Account, AccountService> {
    /**
     * Login result.
     *
     * @return the result
     */
    @Transactional(readOnly = true)
    public Result login() {
        try {
            Form<Account> form = formFactory.form(Account.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Login attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(service.getByEmailAndPassword(form.get(), session())));
        } catch (ServiceException e) {
            logger.error("Service error in AccountController@login", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AccountController@login", e);
            return internalServerError(Json.toJson("Internal server error in AccountController@login"));
        }

    }

    /**
     * Register result.
     *
     * @return the result
     */
    @Transactional
    public Result register() {
        try {
            Form<Account> form = formFactory.form(Account.class).bindFromRequest();
            if (form.hasErrors()) {
                logger.error("Register attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            return ok(Json.toJson(service.create(form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in AccountController@register", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AccountController@register", e);
            return internalServerError(Json.toJson("Internal server error in AccountController@register"));
        }
    }

    /**
     * Logout result.
     *
     * @return the result
     */
    @Transactional
    public Result logout(){

        session().clear();
        return ok();
    }

    /**
     * Get user session result.
     *
     * @return the result
     */
    @Transactional
    public Result getUserSession(){
        try {
            return ok(Json.toJson(service.getCurrentUser(session().get("username"))));
        }
        catch (ServiceException e) {
            logger.error("Service error in AccountController@session", e);
            return badRequest(Json.toJson(""));
        } catch (Exception e) {
            logger.error("Error in AccountController@session", e);
            return internalServerError(Json.toJson("Internal server error in AccountController@session"));
        }
    }

}
