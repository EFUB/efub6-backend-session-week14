package com.efub.api.post;

import com.efub.api.post.dto.PostCreateRequest;
import com.efub.api.post.dto.PostResponse;
import com.efub.core.post.Post;
import com.efub.core.post.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    // blog-core의 리포지토리를 그대로 주입
    private final PostRepository postRepository;

    @Transactional
    public PostResponse create(PostCreateRequest request) {
        Post saved = postRepository.save(request.toEntity());
        return PostResponse.from(saved);
    }

    public List<PostResponse> findVisiblePosts() {
        return postRepository.findAllByHiddenFalseOrderByIdDesc().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse findOne(Long id) {
        Post post = postRepository.findById(id)
                .filter(p -> !p.isHidden())
                .orElseThrow(() -> new NoSuchElementException("글이 없습니다. id="+id));

        return PostResponse.from(post);
    }
}
