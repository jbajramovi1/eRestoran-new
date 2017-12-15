package controllers;

import models.Restaurant;
import models.filters.RestaurantFilterModel;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import services.RestaurantService;
import services.exceptions.ServiceException;
/**
 * The type Restaurant controller.
 */
public class RestaurantController extends BaseController<Restaurant, RestaurantService> {
    @Transactional
    public Result filter() {
        try {

            RestaurantFilterModel data = new RestaurantFilterModel();

            data.setName(this.getString("name",""));
            data.setPricing(this.getInteger("pricing",null));
            data.setRating(this.getInteger("rating",null));
            data.setPageSize(this.getInteger("pageSize",service.count()));
            data.setPageNumber(this.getInteger("pageNumber",0));
            return ok(Json.toJson(service.filter(data)));

        } catch(ServiceException e) {
            logger.error("Error in RestaurantController@filter", e);
            return internalServerError(Json.toJson("Internal server error in RestaurantController@filter"));
        }

    }
}
