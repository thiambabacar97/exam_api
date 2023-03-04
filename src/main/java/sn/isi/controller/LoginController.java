package sn.isi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.LoginRequest;
import sn.isi.dto.LoginResponse;
import sn.isi.security.LoginService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {

    private  LoginService loginService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:4200")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return loginService.login(loginRequest);
    }

}
