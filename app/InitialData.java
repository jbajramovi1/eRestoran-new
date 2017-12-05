
import models.Account;
import models.Role;
import org.slf4j.LoggerFactory;

import play.Environment;
import play.api.Application;
import play.api.Configuration;
import play.api.Play;
import play.api.inject.ApplicationLifecycle;
import play.db.jpa.JPA;
import play.db.jpa.JPAApi;

import play.db.jpa.Transactional;
import play.libs.F;
import services.AccountService;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class InitialData {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(InitialData.class);
    @Inject
    public InitialData (final JPAApi api, final AccountService accountService) {
        api.withTransaction(()->{
            saveUser(accountService);
        });

    }

    private void saveUser(AccountService service) {
        Account acc = new Account();
        acc.setEmail("admin@abhintern.ba");
        acc.setPassword("pass");
        acc.setRole(Role.ADMIN);
        try {
            service.create(acc);
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@constructor", e);
        }
    }


}
