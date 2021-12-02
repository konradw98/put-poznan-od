package put.poznan.ochronadanych.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import put.poznan.ochronadanych.controller.RegisterRequest;
import put.poznan.ochronadanych.exception.PutODException;
import put.poznan.ochronadanych.model.NotificationEmail;
import put.poznan.ochronadanych.model.WebUser;
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
        WebUser webUser = new WebUser();
        webUser.setUsername(registerRequest.getUsername());
        webUser.setEmail(registerRequest.getEmail());
        webUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        //webUser.setPassword(registerRequest.getPassword());
        webUser.setCreated(Instant.now());
        webUser.setEnabled(false);

        userRepository.save(webUser);

       String token = generateVerificationToken(webUser);
        mailService.sendMail(new NotificationEmail("Please Activate your accounet", webUser.getEmail(),
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(WebUser webUser){
       String token = UUID.randomUUID().toString();
       VerificationToken verificationToken = new VerificationToken();
       verificationToken.setToken(token);
       verificationToken.setWebUser(webUser);
        System.out.println("TOKEN for activate account"+token);
       verificationTokenRepository.save(verificationToken);

       return token;
    }
}
