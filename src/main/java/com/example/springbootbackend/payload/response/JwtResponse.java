package com.example.springbootbackend.payload.response;

import com.example.springbootbackend.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

public class JwtResponse {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private final String type = "Bearer";
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles){
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

