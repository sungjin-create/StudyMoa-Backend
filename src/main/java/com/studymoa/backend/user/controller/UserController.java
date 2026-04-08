package com.studymoa.backend.user.controller;

import com.studymoa.backend.common.response.ApiResponse;
import com.studymoa.backend.user.dto.LoginRequest;
import com.studymoa.backend.user.dto.RegisterUser;
import com.studymoa.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterUser request) {
        userService.register(request.getLoginId(), request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.ok("회원가입이 완료되었습니다.", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody LoginRequest request) {
        userService.login(request.getLoginId(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.ok("로그인이 완료되었습니다.", null));
    }
}
