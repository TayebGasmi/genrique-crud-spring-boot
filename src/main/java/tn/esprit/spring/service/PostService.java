package tn.esprit.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.exception.BadWordException;
import tn.esprit.spring.helpers.BadWordFilter;
import tn.esprit.spring.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@AllArgsConstructor
public class PostService extends BaseService<Post, Long> {

    private final PostRepository postRepository;
    private final BadWordFilter badWordFilter;

    public Post addPost(Post post) throws IOException {
        if (badWordFilter.checkBadWord(post.getDescription()))
            throw new BadWordException("Bad word detected");

        return postRepository.save(post);
    }
    public Post updatePost(Post post) throws IOException {
        if (badWordFilter.checkBadWord(post.getDescription()))
            throw new BadWordException("Bad word detected");

        return postRepository.save(post);
    }

}
