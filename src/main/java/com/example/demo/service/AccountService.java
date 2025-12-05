package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 *  실제 로그인, 회원 가입 서비스
 */

@Service
@RequiredArgsConstructor
public class AccountService {
    RepositorySomething userRepository;

    /**
     * 화원 가입 서비스
     * @param user 신규 회원 dto
     * @return 새로 등록된 신규 회원 dto
     */
    public User createAccount(UserDTO user){
        isDuplicatedMailAddress(user);
        User saved =  userRepository.save();
        if(saved == null) throw new NoSuchElementException(ERRORMESSAGE); // 만약 저장에 문제가 있다면 오류 메시지 출력
        return saved;
    }
    private void isDuplicatedMailAddress(UserDTO user){
        if(userRepository.findByEmail(user.getEmail).isPresent()){
            throw new IllegalArgumentException(ERRORMESSAGE);
        }
    }

    public boolean login(Request request){
        String email = request. get email;
        String password = request. get password;
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(ERRORMESSAGE));

        return user.getPassword(password);
    }



}
