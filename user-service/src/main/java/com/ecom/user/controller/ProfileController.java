package com.ecom.user.controller;

import com.ecom.user.dto.UpdateProfileRequest;
import com.ecom.user.dto.UserProfileResponse;
import com.ecom.user.security.JwtUtil;
import com.ecom.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public UserProfileResponse getProfile(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return  profileService.getProfile(email);
    }

    @PutMapping("/update")
    public String updateProfile(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UpdateProfileRequest request){
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        return  profileService.updateProfile(email, request);
    }

}
