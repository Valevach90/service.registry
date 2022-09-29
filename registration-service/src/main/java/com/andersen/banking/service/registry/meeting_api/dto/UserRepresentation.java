package com.andersen.banking.service.registry.meeting_api.dto;

import java.util.Map;
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
public class UserRepresentation {

    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private boolean enabled;
    private Map<String, String> attributes;
    private Credentials[] credentials;

    @Getter
    @Setter
    @Builder
    public static class Credentials {
        private String type;
        private String value;
        private String temporary;
    }
}
