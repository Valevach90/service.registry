package com.andersen.banking.service.registry.meeting_api.dto;

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
public class UserEmailUpdateRepresentation {

    private String email;
    private String username;
    private boolean enabled;

    @Getter
    @Setter
    @Builder
    public static class Credentials {
        private String type;
        private String value;
        private String temporary;
    }

}
