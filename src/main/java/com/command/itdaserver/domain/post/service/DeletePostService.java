package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import com.command.itdaserver.domain.post.domain.repository.AnswerRepository;
import com.command.itdaserver.domain.post.domain.repository.ApplicationRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;
    private final AnswerRepository answerRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void execute(Long postId, CustomUserDetails userDetails) {

        var post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        // 작성자 권한 확인
        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        // Answer가 Question을 참조하므로 먼저 삭제
        ApplyForm applyForm = post.getApplyForm();
        if (applyForm != null) {
            answerRepository.deleteAllByQuestionIn(applyForm.getQuestions());
        }

        // Application이 post_id FK를 가지므로 Post 삭제 전에 먼저 삭제 (cascade 적용을 위해 영속성 컨텍스트 통해 삭제)
        applicationRepository.deleteAll(applicationRepository.findAllByPost(post));

        postRepository.delete(post);
    }
}
