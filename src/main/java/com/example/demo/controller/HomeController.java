package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "Home Page\n<a href='/user'>User Login</a>\n<a href='/admin-page'>Admin Login</a>";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "User Page\n<a href='/logout'>Logout</a>";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin-page")
    public String admin() {
        return "Admin Page\n<a href='/logout'>Logout</a>";
    }
}
