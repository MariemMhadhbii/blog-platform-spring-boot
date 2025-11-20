package com.devtiro.blog.controllers;

import com.devtiro.blog.domain.dtos.AuthResponse;
import com.devtiro.blog.domain.dtos.LoginRequest;
import com.devtiro.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);

        // ----------------------------------------------------------------------
        // ✨ CORRECTION : Ajout de la définition du JWT dans un cookie ✨
        // Ceci aide à la persistance immédiate du token dans le navigateur.

        ResponseCookie cookie = ResponseCookie.from("token", tokenValue)
                .httpOnly(true)
                .secure(false) // Mettre à 'true' si le frontend utilise HTTPS
                .path("/")
                .maxAge(86400) // 24 heures (même que expiresIn)
                .sameSite("Lax")
                .build();
        // ----------------------------------------------------------------------

        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400)
                .build();

        // ----------------------------------------------------------------------
        // Retourne la réponse avec le cookie dans les en-têtes
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);
        // ----------------------------------------------------------------------
    }
}