package com.backendPDG.backend_Hurtado_Garaicoa_PDG.controller;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.User;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.UserService;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.request.AuthRequest;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.request.RegisterRequest;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    public final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);

    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest authenticationRequest){
        return new ResponseEntity<>(userService.authenticate(authenticationRequest), HttpStatus.OK);

    }
    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
