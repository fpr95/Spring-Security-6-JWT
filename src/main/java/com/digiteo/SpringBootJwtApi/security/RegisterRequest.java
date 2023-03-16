package com.digiteo.SpringBootJwtApi.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String pwd;
    private String email;
}
