package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor

/*
 * original: -
 * edited (12/08/2025) response entity를 사용해 json 형식으로 반환: 이민영
 */

public class ProfileController {

    private final ProfileService profileService;

    // 1) 프로필 조회 (유저 정보 + 책 리스트)
    @GetMapping("/{userId}")
    public ResponseEntity<User> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getUserProfile(userId));
    }

    // 2) 비밀번호 변경
    @PatchMapping("/{userId}/password")
    public ResponseEntity<User> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request
    ) {
        String newPassword = request.get("newPassword");
        profileService.changePassword(userId, newPassword);
        return ResponseEntity.noContent().build();
    }

    /*
      // 3) 이메일 변경
    @PatchMapping("/{userId}/userEmail")
    public ResponseEntity<User> changeUserEmail(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request
    ) {
        String newUserEmail = request.get("newUserEmail");
        profileService.changeUserEmail(userId, newUserEmail);
        return ResponseEntity.noContent().build();
    }
    */

    // 4) 닉네임 변경
    @PatchMapping("/{userId}/name")
    public ResponseEntity<User> changeName(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request
    ) {
        String newName = request.get("newName");
        profileService.changeName(userId, newName);
        return ResponseEntity.noContent().build();
    }

    // 5) 계정 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        profileService.deleteUser(userId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
