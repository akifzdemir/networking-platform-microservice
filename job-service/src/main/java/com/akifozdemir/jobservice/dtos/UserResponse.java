package com.akifozdemir.jobservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
}

