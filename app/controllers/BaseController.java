package controllers;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.inject.Singleton;

import models.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.BaseService;
import services.exceptions.ServiceException;

/**
 * The type Base controller.
 *
 * @param <M> the type parameter
 * @param <S> the type parameter
 */
@Singleton
public abstract class BaseController<M extends BaseModel<M>, S extends BaseService> extends Controller {
    /**
     * The Service.
     */
    protected S service;
    /**
     * The Form factory.
     */
    protected FormFactory formFactory;
    /**
     * The Logger.
     */
    final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Inject
    public void setService(S service) {
        this.service = service;
    }

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
     * Get result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional(readOnly = true)
    public Result get(Long id) {
        try {
            return ok(Json.toJson(service.get(id)));
        } catch (Exception e) {
            logger.error("Internal server error in BaseController@get",e);
            return internalServerError("Internal server error in BaseController@get");
        }
    }

    /**
     * Create result.
     *
     * @return the result
     */
    @Transactional
    public Result create() {
        try {
            Form<M> form = formFactory.form(getParameterizedClass()).bindFromRequest();
            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }
            return ok(Json.toJson(service.create(form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in BaseController@create",e);
            return badRequest("Service error in BaseController@create");
        } catch (Exception e) {
            logger.error("Internal server error in BaseController@create",e);
            return internalServerError("Internal server error in BaseController@create");
        }
    }

    /**
     * Update result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result update(Long id) {
        try {
            Form<M> form = formFactory.form(getParameterizedClass()).bindFromRequest();
            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }
            return ok(Json.toJson(service.update(id, form.get())));
        } catch (ServiceException e) {
            logger.error("Service error in BaseController@update",e);
            return badRequest("Service error in BaseController@update");
        } catch (Exception e) {
            logger.error("Internal server error in BaseController@update",e);
            return internalServerError("Internal server error in BaseController@update");
        }
    }

    /**
     * Delete result.
     *
     * @param id the id
     * @return the result
     */
    @Transactional
    public Result delete(Long id) {
        try {
            service.delete(id);
            return ok(Json.toJson("success"));
        } catch (ServiceException e) {
            logger.error("Service error in BaseController@delete",e);
            return badRequest("Service error in BaseController@delete");
        } catch (Exception e) {
            logger.error("Internal server error in BaseController@delete",e);
            return internalServerError("Internal server error in BaseController@delete");
        }
    }

    private Class<M> getParameterizedClass() {
        return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
