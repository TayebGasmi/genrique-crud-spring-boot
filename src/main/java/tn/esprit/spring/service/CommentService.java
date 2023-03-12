package tn.esprit.spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.CommentType;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.exception.BadWordException;
import tn.esprit.spring.helpers.BadWordFilter;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class CommentService extends BaseService<Comment, Long> {
    private final BadWordFilter badWordFilter;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public Comment addComment(Comment comment, Long postId) throws IOException, BadWordException {
        if (badWordFilter.checkBadWord(comment.getContent()))
            throw new BadWordException("Bad word detected");
        Post post = postRepository.findById(postId).get();
        comment.setType(CommentType.COMMENT);
        comment.setPost(post);
        return commentRepository.save(comment);
    }
    public Comment updateComment(Comment comment) throws IOException, BadWordException {
        if(badWordFilter.checkBadWord(comment.getContent()))
            throw new BadWordException("Bad word detected");
        return commentRepository.save(comment);
    }
    public List<Comment> getAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByTypeAndPostId(CommentType.COMMENT, postId);
    }


    public List<Comment> getAllCommentsByPostIdAndOwner(Long postId, Long userId) {
        return commentRepository.findAllByPostIdAndOwnerAndType(postId, userId, CommentType.COMMENT);
    }
    public Comment replyComment(Comment comment, Long commentId) throws IOException, BadWordException{
        if(badWordFilter.checkBadWord(comment.getContent()))
            throw new BadWordException("Bad word detected");
        comment.setType(CommentType.REPLY);
        Comment parent=commentRepository.findById(commentId).get();
        comment.setParent(parent);
        return commentRepository.save(comment);
    }
    public List<Comment> getAllRepliesByCommentId(Long commentId){
        return commentRepository.findAllByTypeAndParentId(CommentType.REPLY, commentId);
    }

}
