package tn.esprit.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Reaction;
import tn.esprit.spring.entity.ReactionType;
import tn.esprit.spring.service.ReactionService;

@RestController
@AllArgsConstructor
@RequestMapping("/reaction")
@Tag(name = "Reaction", description = "the Reaction API")
@Slf4j
public class ReactionController extends BaseController<Reaction, Long> {

    private final ReactionService reactionService;

    @Operation(summary = "add to post ")
    @PostMapping("/post/{postId}")
    ResponseEntity<Reaction> addReaction(@RequestBody Reaction reaction, @PathVariable Long postId) {
        return new ResponseEntity<>(reactionService.addReactionToPost(reaction, postId), HttpStatus.CREATED);
    }
    @Operation(summary = "add to comment ")
    @PostMapping("/comment/{commentId}")
    ResponseEntity<Reaction> addReactionToComment(@RequestBody Reaction reaction, @PathVariable Long commentId) {
        return new ResponseEntity<>(reactionService.addReactionToComment(reaction, commentId), HttpStatus.CREATED);
    }

    @Operation(summary = "get all  by post")
    @GetMapping("/post/{id}")
    ResponseEntity<?> getAllReactionsByPost(@PathVariable Long id) {
        if (reactionService.getAllReactionsByPostId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByPostId(id), HttpStatus.OK);
    }

    @Operation(summary = "get all  by post and owner")
    @GetMapping("/post/{postId}/owner/{userId}")
    ResponseEntity<?> getAllReactionsByPostAndOwner(@PathVariable Long postId, @PathVariable Long userId) {
        if (reactionService.getAllReactionsByPostIdAndOwner(postId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByPostIdAndOwner(postId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get all  by comment")
    @GetMapping("/comment/{id}")
    ResponseEntity<?> getAllReactionsByComment(@PathVariable Long id) {
        if (reactionService.getAllReactionsByCommentId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByCommentId(id), HttpStatus.OK);
    }
    @Operation(summary = "get all  by comment and owner")
    @GetMapping("/comment/{commentId}/owner/{userId}")
    ResponseEntity<?> getAllReactionsByCommentAndOwner(@PathVariable Long commentId, @PathVariable Long userId) {
        if (reactionService.getAllReactionsByCommentIdAndOwner(commentId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByCommentIdAndOwner(commentId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get nbr of reactions by post and type")
    @GetMapping("/post/{postId}/type/{type}")
    ResponseEntity<?> getNbrOfReactionsByPostAndType(@PathVariable("postId") Long postId, @PathVariable("type") ReactionType type) {
        return new ResponseEntity<>(reactionService.countAllByReactionTypeAndPostId(postId,type), HttpStatus.OK);
    }
    @Operation(summary = "get nbr of reactions by comment and type")
    @GetMapping("/comment/{commentId}/type/{type}")
    ResponseEntity<?> getNbrOfReactionsByCommentAndType(@PathVariable Long commentId, @PathVariable ReactionType type) {
        return new ResponseEntity<>(reactionService.countAllByReactionTypeAndCommentId(commentId,type), HttpStatus.OK);
    }
    @Operation(summary = "get post reactions for every type")
    @GetMapping("/details/post/{postId}")
    ResponseEntity<?> getPostReactionsForEveryType(@PathVariable Long postId) {
        return new ResponseEntity<>(reactionService.getReactionCountByPostId(postId), HttpStatus.OK);
    }
    @Operation(summary = "get comment reactions for every type")
    @GetMapping("/details/comment/{commentId}")
    ResponseEntity<?> getCommentReactionsForEveryType(@PathVariable Long commentId) {
        return new ResponseEntity<>(reactionService.getReactionCountByCommentId(commentId), HttpStatus.OK);
    }
}

