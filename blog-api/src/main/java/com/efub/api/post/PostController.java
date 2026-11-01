package com.efub.api.post;

import com.efub.api.post.dto.PostCreateRequest;
import com.efub.api.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@RequestBody PostCreateRequest request) {
        return postService.create(request);
    }

    @GetMapping
    public List<PostResponse> list() {
        return postService.findVisiblePosts();
    }

    @GetMapping("/{id}")
    public PostResponse findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(NoSuchElementException e) {
        return e.getMessage();
    }
}
