package tn.esprit.spring.repository;

import tn.esprit.spring.entity.Reaction;
import tn.esprit.spring.entity.ReactionType;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends BaseRepository<Reaction, Long> {
    List<Reaction> findAllByPostId(Long postId);

    Optional<Reaction> findByPostIdAndOwner(Long postId, Long ownerId);
    List<Reaction> findAllByCommentId(Long commentId);
    List<Reaction> findAllByCommentIdAndOwner(Long commentId, Long ownerId);
   //get the nombre of reaction by type
    Integer countAllByReactionTypeAndPostId(ReactionType reactionType, Long postId);
    Integer countAllByReactionTypeAndCommentId(ReactionType reactionType, Long commentId);



}
