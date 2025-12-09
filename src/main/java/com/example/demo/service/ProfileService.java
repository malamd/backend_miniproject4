package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional

/*
 *original : -
 * edited :  이민영 (12/08/2025)
 */
public class ProfileService {

    private final UserRepository userRepository;

    // 1) 유저 + 해당 유저의 책 목록 조회
    public User getUserProfile(Long userId) {
        // findById를 통해 User를 조회하면, 해당 유저의 책 정보까지 함께 조회할 수 있습니다.
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 2) 비밀번호 변경
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(newPassword);
        // @Transactional 덕분에 save() 없어도 flush 되지만, 보기 좋게 남겨두어도 OK
       userRepository.save(user);
    }

    /*
    // 3) 이메일 변경
    public void changeUserEmail(Long userId, String newUserEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserEmail(newUserEmail);
        // @Transactional 덕분에 save() 없어도 flush 되지만, 보기 좋게 남겨두어도 OK
        userRepository.save(user);
    }
    */

    // 4) 닉네임 변경
    public void changeName(Long userId, String newName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(newName);
        // @Transactional 덕분에 save() 없어도 flush 되지만, 보기 좋게 남겨두어도 OK
        userRepository.save(user);
    }

    // 5) 회원 삭제
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
