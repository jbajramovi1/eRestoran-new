package repositories;

import com.google.inject.ImplementedBy;
import models.Location;

/**
 * The interface MenuItem repository.
 */
@ImplementedBy(LocationRepositoryImplementation.class)
public interface LocationRepository extends BaseRepository<Location> {
}
