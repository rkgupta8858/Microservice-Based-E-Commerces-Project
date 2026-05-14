package com.ecom.user.service;

import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserProfileResponse;
import com.ecom.user.entity.User;
import com.ecom.user.exception.ResourceNotFoundException;
import com.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public UserProfileResponse getProfile(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        return UserProfileResponse.builder().id(user.getId()).fullName(user.getFullName()).email(user.getEmail()).mobile(user.getMobile()).build();

    }

    public String updateProfile(String email, UpdateProfileRequest request){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setFullName(request.getFullName());
        user.setMobile(request.getMobile());
        userRepository.save(user);
        return "Profile Updated Successfully";
    }
}
