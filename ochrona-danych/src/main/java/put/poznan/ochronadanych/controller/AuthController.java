package put.poznan.ochronadanych.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws PutODException {
        authService.signup(registerRequest);
        return new ResponseEntity<>("UserRegostratopm Sicces", HttpStatus.OK);

    }
}
