package com.efub.core.post;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private boolean hidden = false; // 관리자가 숨긴 글인지

    private LocalDateTime createdAt;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // TODO 2. 글을 숨기는 메서드 hide()를 만드세요.
    // 글을 숨긴다는 규칙은 어느 앱에서 쓰든 같으므로 엔티티(core)에 둡니다.
}
