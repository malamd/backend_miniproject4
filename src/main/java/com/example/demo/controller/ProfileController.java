package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor

/**
 * original: ?
 * edited (12/08/2025) response entity를 사용해 json 형식으로 반환: 이민영
 */

public class ProfileController {

    private final ProfileService profileService;

    // 1) 프로필 조회 (유저 정보 + 책 리스트)
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getUserProfile(userId));
    }

    // 2) 비밀번호 변경
    @PatchMapping("/{userId}/password")
    public ResponseEntity<Object> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request
    ) {
        String newPassword = request.get("newPassword");
        profileService.changePassword(userId, newPassword);
        return ResponseEntity.ok("Password updated");
    }

    // 3) 계정 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        profileService.deleteUser(userId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
