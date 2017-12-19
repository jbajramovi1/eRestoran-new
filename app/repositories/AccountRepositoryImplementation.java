package repositories;

import models.Account;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;


/**
 * The type Account repository implementation.
 */
public class AccountRepositoryImplementation extends BaseRepositoryImplementation<Account> implements AccountRepository {

    @Override
    public Account getByEmail(Account account) {
        return (Account) getBaseCriteria()
                .add(Restrictions.eq("email", account.getEmail())).uniqueResult();
    }

    @Override
    public Account getByEmailAndPassword(Account account) {
        Account acc = (Account) getBaseCriteria()
                .add(Restrictions.eq("email", account.getEmail()))
                .uniqueResult();
        if (acc != null && BCrypt.checkpw(account.getPassword(), acc.getPassword())) {
            return account;
        }
        return null;
    }

    @Override
    public Account getCurrentUser(String email) {
        return (Account) getBaseCriteria()
                .add(Restrictions.eq("email", email))
                .uniqueResult();

    }

}