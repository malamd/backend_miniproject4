package controller;

import com.example.demo.service.LoginService;
import com.example.demo.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    LoginService loginService;
    SignUpService signInService;

}
