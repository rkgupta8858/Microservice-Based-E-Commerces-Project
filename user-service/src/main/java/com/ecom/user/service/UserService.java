package com.ecom.user.service;


import com.ecom.user.dto.UserRequest;
import com.ecom.user.entity.User;
import com.ecom.user.exception.UserAlreadyExistsException;
import com.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String registerUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }
        if (userRepository.existsByMobile(request.getMobile())) {
            throw new UserAlreadyExistsException("Mobile Already Exists");
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

}
