package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;

import java.util.NoSuchElementException;

public class SignUpService {
    RepositorySomething userRepository;
    /**
     * 화원 가입 서비스
     * @param user 신규 회원 dto
     * @return 새로 등록된 신규 회원 dto
     */
    public User createAccount(UserDTO user){
        isDuplicatedMailAddress(user);
        User saved =  userRepository.save();
        if(saved == null) throw new NoSuchElementException("저장에 문제가 생김.."); // 만약 저장에 문제가 있다면 오류 메시지 출력
        return saved;
    }
    private void isDuplicatedMailAddress(UserDTO user){
        String address = user.getUser_email();
        userRepository.findByEmail(address).isPresent(
                ()-> new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE. // 중복된 이메일인 경우
                        DUPLICATED_ADDRESS.content()));

    }
}
