package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO { // TODO: setting validations
//    private String userEmail;
    private String password;
    private String name;
    private User.Gender gender;
//    private int age;
//    private User.Role role;
}
