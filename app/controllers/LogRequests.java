package controllers;

import models.LogRequest;
import play.mvc.*;
import play.Logger;
import services.LogRequestService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class LogRequests extends BaseController<Long, LogRequest, LogRequestService> {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Logger.debug(LogRequests.class.getSimpleName().concat("index"));
        return ok(views.html.index.render());
    }

}
