package put.poznan.ochronadanych.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.service.AuthService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws PutODException {
        System.out.println("JESTMW W SIGNUP");
        authService.signup(registerRequest);
        return new ResponseEntity<>("UserRegostratopm Sicces", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws PutODException {
         authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activatedsuccesy", HttpStatus.OK);

    }
}
