package com.ecom.user.controller;

import com.ecom.user.dto.ForgotPasswordRequest;
import com.ecom.user.dto.ResetPasswordRequest;
import com.ecom.user.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/forgot")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request){
        return passwordService.forgotPassword(request);
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestBody ResetPasswordRequest request){
        return passwordService.resetPassword(request);
    }
}
