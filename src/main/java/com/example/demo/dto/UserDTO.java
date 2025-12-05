package com.example.demo.dto;

import com.example.demo.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String user_email;
    private String password;
    private String name;
    private User.Gender gender;
    private int age;
    private User.Role role;
}
