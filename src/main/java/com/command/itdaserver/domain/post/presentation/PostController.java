package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest.QuestionDto;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.domain.post.service.CreateApplyFormService;
import com.command.itdaserver.domain.post.service.CreatePostService;
import com.command.itdaserver.domain.post.service.GetPostService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/projects")
public class PostController {

    private final GetPostService getPostService;

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return getPostService.getPost(postId);
    }

}
