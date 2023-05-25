package com.example.demo.controller;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String home() {
        return "Home Page<br>" +
                "<a href='/login'>Login</a><br>" +
                "<a href='/user'>User Page</a><br>" +
                "<a href='/admin-page'>Admin Page</a>";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "User Page <br> " +
                "<a href='/logout'>Logout</a> <br>" +
                "<a href='/other-page'>Go to other page</a>";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/other-page")
    public String user2() {
        return "Other Page <br> " +
                "<a href='/logout'>Logout</a> <br>" +
                "<a href='/user'>Go back to user page</a>";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin-page")
    public String admin() {
        return "Admin Page<br><a href='/logout'>Logout</a>";
    }

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false") String error) {
        String response = "Login <br>" +
                "<form action='/authenticate'" +
                "<label>Username: </label>" +
                "<input type='text' name='username'/> <br>" +
                "<label>Password: </label>" +
                "<input type='password' name='password'/> <br>" +
                "<button type='submit'> Submit </button>" +
                "</form>";

        if (error.equals("true")) {
            String errorHeader = "Invalid Credentials <br>";
            response = errorHeader + response;
        }

        return response;
    }

//    @GetMapping("/authenticate")
//    public String authenticate(
//            @RequestParam String username,
//            @RequestParam String password
//    ) {
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//        String token = jwtUtility.generateToken(userDetails);
//
//        return "Authentication page of " + username + " with password: " + password +
//                "<br> TOKEN: " + token;
//    }

    @GetMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(
            @RequestParam String username,
            @RequestParam String password
    ) throws BadCredentialsException
    {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        String token = jwtUtility.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
