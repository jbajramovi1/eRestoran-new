package controllers;

import models.Account;
import org.slf4j.LoggerFactory;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import static play.mvc.Controller.session;

public class Secured extends Security.Authenticator {

    private static AccountService accountService;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(Secured.class);

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return badRequest("Login to continue");
    }

    static boolean check(String profile) {
        Account acc=new Account();
        try {
            acc = accountService.getCurrentUser(session().get("username"));
        }
        catch (ServiceException e){
            logger.error("User not found");
        }
        return (acc.getRole().toString()==profile);
    }


}
