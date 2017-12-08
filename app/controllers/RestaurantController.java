package controllers;

import models.Restaurant;
import models.filters.RestaurantFilterModel;
import play.api.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import services.RestaurantService;
import services.exceptions.ServiceException;
import play.mvc.Result;
/**
 * The type Restaurant controller.
 */
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {
    @Transactional
    public Result filter() {
        try {

            play.data.Form<RestaurantFilterModel> form = formFactory.form(RestaurantFilterModel.class).bindFromRequest();
            if(form.hasErrors()) {
                logger.error("Restaurants filter attempt failed, form has errors.", form.errors());
                return badRequest(form.errorsAsJson());
            }

            RestaurantFilterModel data = form.get();

            RestaurantFilterModel rfm = new RestaurantFilterModel()
                    .setName(data.getName())
                    .setPricing(data.getPricing())
                    .setRating(data.getRating())
                    .setCategories(data.getCategories())
                    .setPageNumber(data.getPageNumber())
                    .setPageSize(data.getPageSize());

            return ok(Json.toJson(service.filter(rfm)));

        } catch(ServiceException e) {
            logger.error("Error in RestaurantController@filter", e);
            return internalServerError(Json.toJson("Internal server error in RestaurantController@filter"));
        }

    }
}
