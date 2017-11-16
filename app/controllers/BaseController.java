package controllers;

import models.BaseModel;
import play.Logger;
import play.mvc.Controller;
import play.libs.Json;

import play.db.jpa.Transactional;
import play.mvc.Result;
import services.BaseService;
import services.ServiceException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.ParameterizedType;

@Singleton
public class BaseController<T, M extends BaseModel<T, M>, S extends BaseService<T, M, ?>> extends Controller {

	protected S service;

	@Inject
	public void setService(S service) {
		this.service = service;
	}

	@Transactional(readOnly = true)
	public Result get(T id) {
		try {
			M model = service.get(id);
			return ok(Json.toJson(model));
		} catch (Exception e) {
			Logger.error("Failed to complete get request.", e);
			return internalServerError();
		}
	}

	@Transactional
	public Result create() {
		try {
//			Form<M> form = FORM.bindFromRequest();
//			if (form.hasErrors()) {
//				Logger.error("Failed to create model. Form has errors.", form.errors());
//				return badRequest();
//			}
//			return ok(Json.toJson(service.create(form.get())));
			return ok();
//		} catch (ServiceException e) {
//			Logger.error("Failed to save model of type {}", getModelClassParametarType().getSimpleName(), e);
//			return badRequest();
		} catch (Exception e) {
			Logger.error("Failed to save model of type {}", getModelClassParametarType().getSimpleName(), e);
			return internalServerError();

		}
	}

	private Class<M> getModelClassParametarType() {
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
}
