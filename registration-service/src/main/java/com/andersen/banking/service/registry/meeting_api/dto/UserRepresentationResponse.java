package com.andersen.banking.service.registry.meeting_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRepresentationResponse {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Attributes attributes;

    @Getter
    @Setter
    public static class Attributes {
        private String[] phone;
        private String[] patronymic;
    }
}
