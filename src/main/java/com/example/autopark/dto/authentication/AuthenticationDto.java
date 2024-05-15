package com.example.autopark.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationDto {

    @JsonProperty("token")
    private String token;

}
