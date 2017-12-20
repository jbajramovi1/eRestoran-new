package controllers;

import models.Account;
import models.Role;
import org.slf4j.LoggerFactory;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

/**
 * The type Secured admin.
 */
public class SecuredAdmin extends Security.Authenticator {
    private static AccountService accountService;
    /**
     * The constant logger.
     */
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(Secured.class);

    /**
     * Sets account service.
     *
     * @param accountService the account service
     */
    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String getUsername(Context ctx) {
        String user=ctx.session().get("username");
        if (user==null) {
            return null;
        }

        try {
            Account acc = accountService.getCurrentUser(user);
            if (Role.ADMIN==acc.getRole()) {
                return user;
            }
            else {
                return null;
            }
        }
        catch (ServiceException e){
            logger.error("User not found");
            return null;
        }

    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return badRequest("Admin privilegies required");
    }

}
