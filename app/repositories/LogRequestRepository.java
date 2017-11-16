package repositories;

import com.google.inject.ImplementedBy;
import models.LogRequest;

/**
 * The interface Log request repository.
 */
@ImplementedBy(LogRequestRepositoryImplementation.class)
public interface LogRequestRepository extends BaseRepository<Long, LogRequest> {
}
