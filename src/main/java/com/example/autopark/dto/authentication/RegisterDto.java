package com.example.autopark.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

}
