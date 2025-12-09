package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public LoginUserDTO login(LoginRequestDTO request) {
        String name = request.getName();
        String password = request.getPassword();

        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!password.equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return LoginUserDTO.fromEntity(user);
    }



}
