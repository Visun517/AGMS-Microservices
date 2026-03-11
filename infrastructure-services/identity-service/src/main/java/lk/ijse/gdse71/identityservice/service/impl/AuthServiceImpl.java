package lk.ijse.gdse71.identityservice.service.impl;

import lk.ijse.gdse71.identityservice.dto.AuthRequestDto;
import lk.ijse.gdse71.identityservice.dto.UserRegisterDto;
import lk.ijse.gdse71.identityservice.entity.UserCredentials;
import lk.ijse.gdse71.identityservice.repositroy.UserCredentialsRepository;
import lk.ijse.gdse71.identityservice.service.AuthService;
import lk.ijse.gdse71.identityservice.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserCredentialsRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authenticate(AuthRequestDto authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid Access!");
        }
    }

    @Override
    public String register(UserRegisterDto user) {
        UserCredentials entity = UserCredentials.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        repository.save(entity);
        return "User registered successfully";
    }
}
