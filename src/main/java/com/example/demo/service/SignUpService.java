package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    /**
     * 화원 가입 서비스
     * @param user 신규 회원 dto
     * @return 새로 등록된 신규 회원 dto
     */
    @Transactional
    public boolean createAccount(UserDTO user){
        isDuplicatedMailAddress(user);
        User newUser = toEntity(user);
        User saved =  userRepository.save(newUser);
        if(saved == null) throw new NoSuchElementException("저장에 문제가 생김.."); // 만약 저장에 문제가 있다면 오류 메시지 출력
        return true;
    }
    private void isDuplicatedMailAddress(UserDTO user){
        String address = user.getName();
        if(userRepository.findByName(address).isPresent()) throw new IllegalArgumentException(
                        ACCOUNT_SERVICE_ERROR_MESSAGE.DUPLICATED_ADDRESS.content()); // 중복된 이메일인 경우

    }


    // helper <- 필요할시 변경
    private User toEntity(UserDTO userDTO){
        User user = new User();
        user.setUserEmail(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setGender(userDTO.getGender());
//        user.setAge(userDTO.getAge());
//        user.setRole(userDTO.getRole());
        return user;
    }
}
