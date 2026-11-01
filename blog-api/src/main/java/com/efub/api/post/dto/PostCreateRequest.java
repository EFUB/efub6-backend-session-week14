package com.efub.api.post.dto;

import com.efub.core.post.Post;

public record PostCreateRequest(
        String title,
        String content
) {
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
