import models.Account;
import models.Role;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.AccountService;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.swing.*;

import static play.mvc.Results.ok;
@Transactional
public class InitialData extends JDialog{

    protected AccountService accountService;
    final org.slf4j.Logger logger = LoggerFactory.getLogger(InitialData.class);

    public static class Factory {
        @Inject private AccountService accountService;

        public InitialData create(JFrame parent) {
            return new InitialData(parent, accountService);
        }
    }

    @Inject
    public InitialData(JFrame parent, AccountService accountService) {
        super(parent, "", true);
        this.accountService=accountService;
        Account acc = new Account();
        acc.setEmail("admin@abhintern.ba");
        acc.setPassword("pass");
        acc.setRole(Role.ADMIN);
        try {
            accountService.create(acc);
        } catch (ServiceException e) {
            logger.error("Service error in InitialData@constructor", e);
        }
    }
}
