package com.efub.admin.post.dto;

import com.efub.core.post.Post;

import java.time.LocalDateTime;

public record AdminPostResponse(
        Long id,
        String title,
        boolean hidden,
        LocalDateTime createdAt
) {
    public static AdminPostResponse from(Post post) {
        return new AdminPostResponse(post.getId(), post.getTitle(), post.isHidden(), post.getCreatedAt());
    }
}
