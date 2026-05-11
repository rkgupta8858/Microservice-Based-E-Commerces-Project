package com.ecom.user.service;

import com.ecom.user.dto.ForgotPasswordRequest;
import com.ecom.user.dto.ResetPasswordRequest;
import com.ecom.user.entity.User;
import com.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email not Found"));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);
        return "Reset Token : " + token;
    }

    public String resetPassword(ResetPasswordRequest request){
        User user = userRepository.findByResetToken(request.getToken()).orElseThrow(() -> new RuntimeException("Invalid Token"));
        if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetToken(null);
        user.setTokenExpiry(null);

        userRepository.save(user);
        return "Password Reset Successfully";
    }

}
