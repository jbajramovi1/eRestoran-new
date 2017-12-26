package repositories;

import com.google.inject.ImplementedBy;
import models.Menu;

/**
 * The interface Menu repository.
 */
@ImplementedBy(MenuRepositoryImplementation.class)
public interface MenuRepository extends BaseRepository<Menu> {
}
