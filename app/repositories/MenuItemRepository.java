package repositories;

import com.google.inject.ImplementedBy;
import models.MenuItem;

/**
 * The interface MenuItem repository.
 */
@ImplementedBy(MenuItemRepositoryImplementation.class)
public interface MenuItemRepository extends BaseRepository<MenuItem> {
}
