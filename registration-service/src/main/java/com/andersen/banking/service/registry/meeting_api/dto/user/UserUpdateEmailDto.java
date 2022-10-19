package com.andersen.banking.service.registry.meeting_api.dto.user;

import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.DESCRIPTION_EMAIL;
import static com.andersen.banking.service.registry.meeting_api.utils.OpenApiConstants.EXAMPLE_EMAIL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for update email")
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateEmailDto {

    @Schema(hidden = true)
    @JsonProperty("id")
    private UUID id;

    @Schema(hidden = true)
    @JsonProperty("username")
    private String username;

    @Schema(description = DESCRIPTION_EMAIL, example = EXAMPLE_EMAIL, defaultValue = EXAMPLE_EMAIL)
    @JsonProperty("email")
    @Pattern(regexp = "(?=.{1,50}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})",
            message = "Invalid email.")
    private String email;

}
