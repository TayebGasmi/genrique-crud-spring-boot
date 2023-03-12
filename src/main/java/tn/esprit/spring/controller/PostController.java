package tn.esprit.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.service.PostService;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
@Tag(name = "Post", description = "the Post API")
public class PostController extends BaseController<Post, Long> {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "add ")
    ResponseEntity<Post> addPost(@RequestBody Post post) throws IOException {
        return new ResponseEntity<>(postService.addPost(post), HttpStatus.CREATED);
    }
    @PutMapping()
    @Operation(summary = "update ")
    ResponseEntity<Post> updatePost(@RequestBody Post post) throws IOException {
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }


}
