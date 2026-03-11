package lk.ijse.gdse71.identityservice.service;

import lk.ijse.gdse71.identityservice.dto.AuthRequestDto;
import lk.ijse.gdse71.identityservice.dto.UserRegisterDto;

public interface AuthService {
     String authenticate(AuthRequestDto authRequest);
     String register(UserRegisterDto user);
}
