package com.command.itdaserver.domain.auth.presentation;

import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.auth.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signUp(@Valid @RequestBody SignUpRequest request){
        signUpService.signUp(request);
        return ResponseEntity.ok(Map.of(
                "message", "회원가입이 되었습니다, 로그인 후 서비스를 이용해주세요."
        ));
    }
}
