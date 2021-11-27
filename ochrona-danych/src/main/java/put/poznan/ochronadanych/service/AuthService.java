package put.poznan.ochronadanych.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import put.poznan.ochronadanych.controller.RegisterRequest;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.model.NotificationEmail;
import put.poznan.ochronadanych.model.User;
import put.poznan.ochronadanych.model.VerificationToken;
import put.poznan.ochronadanych.repository.UserRepository;
import put.poznan.ochronadanych.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) throws PutODException {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        //user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your accounet", user.getEmail(),
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user){
       String token = UUID.randomUUID().toString();
       VerificationToken verificationToken = new VerificationToken();
       verificationToken.setToken(token);
       verificationToken.setUser(user);

       verificationTokenRepository.save(verificationToken);

       return token;
    }
}
