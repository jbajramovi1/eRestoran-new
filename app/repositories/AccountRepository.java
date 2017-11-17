package repositories;

import com.google.inject.ImplementedBy;

import models.Account;
import play.mvc.Http;

/**
 * The interface Account repository.
 */
@ImplementedBy(AccountRepositoryImplementation.class)
public interface AccountRepository extends BaseRepository<Account> {
    /**
     * Gets by email.
     *
     * @param account the account
     * @return the by email
     */
    Account getByEmail(Account account);

    /**
     * Gets by email and password.
     *
     * @param account the account
     * @return the by email and password
     */
    Account getByEmailAndPassword(Account account);

    /**
     * Gets current user.
     *
     * @param email the email
     * @return the current user
     */
    Account getCurrentUser(String email);
}
