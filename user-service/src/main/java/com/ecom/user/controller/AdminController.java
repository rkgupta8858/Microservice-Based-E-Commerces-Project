package com.ecom.user.controller;

import com.ecom.user.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
//    @GetMapping("/api/admin/dashboard")
//    public String adminDashboard(){
//        return "Welcome Admin !!";
//    }

    private final JwtUtil  jwtUtil;

    @GetMapping("/api/admin/dashboard")
    public String adminDashboard(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        String role = jwtUtil.extractRole(token);

        if (!role.equals("ADMIN")) {

            throw new RuntimeException("Access Denied");
        }

        return "Welcome Admin";
    }
}
