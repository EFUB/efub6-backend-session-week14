package com.efub.admin.post;

import com.efub.admin.post.dto.AdminPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin/posts")
@RequiredArgsConstructor
public class AdminPostController {

    private final AdminPostService adminPostService;

    @GetMapping
    public List<AdminPostResponse> list() {
        return adminPostService.findAll();
    }

    @PatchMapping("/{id}/hide")
    public AdminPostResponse hide(@PathVariable Long id) {
        return adminPostService.hide(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(NoSuchElementException e) {
        return e.getMessage();
    }
}
