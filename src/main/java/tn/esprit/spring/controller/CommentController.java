package tn.esprit.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.service.CommentService;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
@Tag(name = "Comment", description = "the Comment API")
public class CommentController extends BaseController<Comment, Long> {

    private final CommentService commentService;

    @Operation(summary = "add ")
    @PostMapping(value = "/post/{postId}", consumes = "application/json")
    ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable("postId") Long postId) throws IOException {
        return new ResponseEntity<>(commentService.addComment(comment, postId), HttpStatus.CREATED);
    }
    @Operation(summary = "update ")
    @PutMapping(value = "/{commentId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId) throws IOException {
        return new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK);
    }
    @Operation(summary = "reply ")
    @PostMapping(value = "reply/{commentId}", consumes = "application/json")
    ResponseEntity<Comment> replyComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId) throws IOException {
        return new ResponseEntity<>(commentService.replyComment(comment, commentId), HttpStatus.CREATED);
    }
    @Operation(summary = "get all by post")
    @GetMapping("/post/{id}")
    ResponseEntity<?> getAllCommentsByPost(@PathVariable Long id) {
        if (commentService.getAllCommentsByPostId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(id), HttpStatus.OK);
    }
    @Operation(summary = "get all by post and owner")
    @GetMapping("/post/{postId}/owner/{userId}")
    ResponseEntity<?> getAllCommentsByPostAndOwner(@PathVariable Long postId, @PathVariable Long userId) {
        if (commentService.getAllCommentsByPostIdAndOwner(postId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(commentService.getAllCommentsByPostIdAndOwner(postId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get all replies by comment")
    @GetMapping("/comment/{id}")
    ResponseEntity<?> getAllRepliesByComment(@PathVariable Long id) {
        if (commentService.getAllRepliesByCommentId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(commentService.getAllRepliesByCommentId(id), HttpStatus.OK);
    }



}
