package controllers;

import models.Comment;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import services.CommentService;
import services.exceptions.ServiceException;

/**
 * The type Comment controller.
 */
public class CommentController extends BaseController<Comment, CommentService>{

    @Transactional
    @Override
    public Result create() {
        try {
            Form<Comment> form = formFactory.form(Comment.class).bindFromRequest();
            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }
            return ok(Json.toJson(service.create(form.get(),session())));
        } catch (ServiceException e) {
            logger.error("Service error in CommentController@create",e);
            return badRequest("Service error in CommentController@create");
        } catch (Exception e) {
            logger.error("Internal server error in CommentController@create",e);
            return internalServerError("Internal server error in CommentController@create");
        }
    }

}
