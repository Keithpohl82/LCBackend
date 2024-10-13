package com.launchcode.LCBackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.launchcode.LCBackend.config.CustomLocalDateTimeDeserializer;

import java.time.LocalDateTime;

public record MatchDto(
        @JsonProperty("full_name")
        String fullName,
        String email,
        @JsonProperty("phone_number")
        String phoneNumber,
        String experience,
        String availability,
        String location,
        boolean online,
        @JsonProperty("created_at")
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
        LocalDateTime createdAt,
        String track) {
}
