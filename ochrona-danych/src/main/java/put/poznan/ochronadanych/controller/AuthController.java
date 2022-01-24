package put.poznan.ochronadanych.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.ochronadanych.dto.AuthenticationResponse;
import put.poznan.ochronadanych.dto.LoginRequest;
import put.poznan.ochronadanych.dto.RefreshTokenRequest;
import put.poznan.ochronadanych.dto.RegisterRequest;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.service.AuthService;
import put.poznan.ochronadanych.service.RefreshTokenService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws PutODException {
        System.out.println("JESTMW W SIGNUP");
        authService.signup(registerRequest);
        return new ResponseEntity<>("UserRegostratopm Sicces", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws PutODException {
         authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activatedsuccesy", HttpStatus.OK);

    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws PutODException {
        System.out.println("JESTEM W LOGIN");
       return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws PutODException {
        return authService.refreshToken(refreshTokenRequest);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
