package com.efub.admin.post;

import com.efub.admin.post.dto.AdminPostResponse;
import com.efub.core.post.Post;
import com.efub.core.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminPostService {

    private final PostRepository postRepository;

    public List<AdminPostResponse> findAll() { // 관리자는 숨긴 글까지 전부 본다
        return postRepository.findAll().stream()
                .map(AdminPostResponse::from)
                .toList();
    }

    @Transactional
    public AdminPostResponse hide(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("글이 없습니다. id="+id));

        post.hide();
        return AdminPostResponse.from(post);
    }


}
