package tn.esprit.spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Reaction;
import tn.esprit.spring.entity.ReactionEntity;
import tn.esprit.spring.entity.ReactionType;
import tn.esprit.spring.repository.ReactionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ReactionService extends BaseService<Reaction, Long> {

    private final ReactionRepository reactionRepository;

    private final PostService postService;
    private final CommentService commentService;

    public Reaction addReactionToPost(Reaction reaction, Long postId) {
        reaction.setEntity(ReactionEntity.POST);
        reaction.setPost(postService.findById(postId).get());
        return reactionRepository.save(reaction);
    }

    public List<Reaction> getAllReactionsByPostId(Long postId) {
        return reactionRepository.findAllByPostId(postId);
    }

    public Optional<Reaction> getAllReactionsByPostIdAndOwner(Long postId, Long userId) {
        return reactionRepository.findByPostIdAndOwner(postId, userId);
    }
    public Reaction addReactionToComment(Reaction reaction,Long CommentId){
        reaction.setEntity(ReactionEntity.COMMENT);
        reaction.setComment(commentService.findById(CommentId).get());
        return reactionRepository.save(reaction);
    }
    public List<Reaction> getAllReactionsByCommentId(Long commentId){
        return reactionRepository.findAllByCommentId(commentId);
    }
     public Integer countAllByReactionTypeAndPostId(Long postId, ReactionType reactionType){
          return reactionRepository.countAllByReactionTypeAndPostId(reactionType,postId);
     }
    public Integer countAllByReactionTypeAndCommentId(Long commentId, ReactionType reactionType) {
        return reactionRepository.countAllByReactionTypeAndCommentId(reactionType, commentId);
    }
    public List<Reaction> getAllReactionsByCommentIdAndOwner(Long commentId, Long userId) {
        return reactionRepository.findAllByCommentIdAndOwner(commentId, userId);
    }
    public Map<String,Integer> getReactionCountByPostId(Long postId){
        Map<String,Integer> map=new HashMap<>();
        for(ReactionType reactionType:ReactionType.values()){
            map.put(reactionType.name(),countAllByReactionTypeAndPostId(postId,reactionType));
        }
        return map;
    }
    public Map<String,Integer> getReactionCountByCommentId(Long commentId){
        Map<String,Integer> map=new HashMap<>();
        for(ReactionType reactionType:ReactionType.values()){
            map.put(reactionType.name(),countAllByReactionTypeAndCommentId(commentId,reactionType));
        }
        return map;
    }
}
