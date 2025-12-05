package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDTO;
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
        if(saved == null) throw new NoSuchElementException(ACCO); // 만약 저장에 문제가 있다면 오류 메시지 출력
        return saved;
    }
    private void isDuplicatedMailAddress(UserDTO user){
        String address = user.getUser_email();
        userRepository.findByEmail(address).isPresent(
                ()-> new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE. // 중복된 이메일인 경우
                        DUPLICATED_ADDRESS.content()));

    }

    public boolean login(LoginRequestDTO request){
        String email = request.getUser_email();
        String password = request.getPassword();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE // 주어진 이메일이 없는 경우
                        .EMAIL_NOT_FOUND.content()));

        if (!password.equals(user.getPassword()))
            throw new IllegalArgumentException(ACCOUNT_SERVICE_ERROR_MESSAGE.
                    WRONG_PASSWORD.content()); // 패스워드가 맞지 않는 경우

        return true;
    }


    /**
     * 에러 메시지용 enum, refactoring / clean up 시 따로 클래스로 빼도 ok
     */
    enum ACCOUNT_SERVICE_ERROR_MESSAGE {
        DUPLICATED_ADDRESS("이미 가입한 회원입니다."),
        EMAIL_NOT_FOUND("이메일을 찾을 수 없습니다."),
        WRONG_PASSWORD("비밀번호가 잘못되었습니다.");
        private final String message;
        ACCOUNT_SERVICE_ERROR_MESSAGE(String s) {
            message = s;
        }
        public String content() {
            return message;
        }
    }


}
