package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.Book;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

/**
 *
 */
public class ProfileService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    // 1) 유저 + 해당 유저의 책 목록 조회
    public Map<String, Object> getUserProfile(Long userId) {

        // User 엔티티의 PK 필드가 userId 라서 이 코드가 딱 맞음
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Book.user.userId 경로 기반으로 자동 쿼리 생성
        List<Book> books = bookRepository.findByUser_UserId(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("books", books);

        return result;
    }

    // 2) 비밀번호 변경
    public void changePassword(Long userId, String newPassword) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(newPassword);
        // @Transactional 덕분에 save() 없어도 flush 되지만, 보기 좋게 남겨두어도 OK
        userRepository.save(user);
    }

    // 3) 회원 삭제
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
