package services;

import models.Account;
import org.mindrot.jbcrypt.BCrypt;
import play.mvc.Http.Session;
import repositories.AccountRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;


/**
 * The type Account service.
 */
public class AccountService extends BaseService<Account, AccountRepository> {

    /**
     * Gets by email.
     *
     * @param data the data
     * @return the by email
     * @throws ServiceException the service exception
     */
    public Account getByEmail(Account data) throws ServiceException {
        Account account = repository.getByEmail(data);
        if (account == null) {
            throw new ServiceException("entity not found");
        }
        return account;
    }

    /**
     * Gets by email and password.
     *
     * @param data    the data
     * @param session the session
     * @return the by email and password
     * @throws ServiceException the service exception
     */
    public Account getByEmailAndPassword(Account data, Session session) throws ServiceException {
        Account account = repository.getByEmailAndPassword(data);
        if (account == null) {
            logger.error("entity not found", data.getEmail(), data.getPassword());
            throw new ServiceException("entity not found");
        }
        session.clear();
        session.put("username", account.getEmail());
        return account;
    }

    /**
     * Gets current user.
     *
     * @param email the email
     * @return the current user
     * @throws ServiceException the service exception
     */
    public Account getCurrentUser(String email) throws ServiceException{
        Account account=repository.getCurrentUser(email);
        if (account == null) {
            logger.error("entity not found");
            throw new ServiceException("entity not found");
        }
        return account;
    }

    @Override
    public Account create(Account model) throws ServiceException {
        try {
            model.setPassword((BCrypt.hashpw(model.getPassword(), BCrypt.gensalt())));
            repository.create(model);
            return model;
        } catch (RepositoryException e) {
            logger.error("Repository exception in AccountService@create", e);
            throw new ServiceException("Service couldn't create model.", e);
        }
    }
}
