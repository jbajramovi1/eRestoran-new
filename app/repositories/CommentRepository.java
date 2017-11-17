package repositories;

import com.google.inject.ImplementedBy;
import models.Comment;

/**
 * The interface Comment repository.
 */
@ImplementedBy(CommentRepositoryImplementation.class)
public interface CommentRepository extends BaseRepository<Comment> {
}
