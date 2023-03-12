package tn.esprit.spring.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.CommentType;
import java.util.List;

@Repository
public interface CommentRepository extends BaseRepository<Comment, Long> {


    List<Comment> findAllByPostIdAndOwnerAndType(Long postId, Long ownerId, CommentType commentType);

    List<Comment> findAllByTypeAndParentId(CommentType commentType,Long parentId);
    List <Comment> findAllByTypeAndPostId(CommentType commentType, Long postId);


}
