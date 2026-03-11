package lk.ijse.gdse71.identityservice.controller;

import lk.ijse.gdse71.identityservice.dto.ApiResponseDto;
import lk.ijse.gdse71.identityservice.dto.AuthRequestDto;
import lk.ijse.gdse71.identityservice.dto.UserRegisterDto;
import lk.ijse.gdse71.identityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto> addNewUser(@RequestBody UserRegisterDto user) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        201,
                        "User registered successfully",
                        service.register(user)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody AuthRequestDto authRequest) {
        return ResponseEntity.ok(
                new ApiResponseDto(
                        200,
                        "User login successfully",
                        service.authenticate(authRequest)
                )
        );
    }
}
