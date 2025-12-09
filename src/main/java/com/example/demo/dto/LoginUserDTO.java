package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDTO {
//    private String userEmail;
    private String name;
    private User.Gender gender;
//    private int age;
//    private User.Role role;
    private Long userId;

    // User 엔티티를 LoginUserDTO로 변환하는 정적 팩토리 메서드
    public static LoginUserDTO fromEntity(User user) {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setUserId(user.getUserId());
//        dto.setUserEmail(user.getUserEmail());
        dto.setName(user.getName());
        dto.setGender(user.getGender());
//        dto.setAge(user.getAge());
//        dto.setRole(user.getRole());
        return dto;
    }
}
