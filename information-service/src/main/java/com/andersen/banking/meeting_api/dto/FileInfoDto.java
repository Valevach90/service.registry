package com.andersen.banking.meeting_api.dto;

import static com.andersen.banking.meeting_api.utility.OpenApiConstants.DESCRIPTION_DATE_OF_CREATION;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.DESCRIPTION_DATE_OF_UPDATE;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.DESCRIPTION_FILE_INFO_ID;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.DESCRIPTION_FILE_NAME;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.DESCRIPTION_LINK;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_DATE;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_FILE_NAME;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LINK;
import static com.andersen.banking.meeting_api.utility.OpenApiConstants.EXAMPLE_LONG;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto for file info.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for file info")
public class FileInfoDto {

    @Schema(description = DESCRIPTION_FILE_INFO_ID, example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    @NotNull(message = "Id can't be null.")
    private Long id;

    @Schema(description = DESCRIPTION_FILE_NAME, example = EXAMPLE_FILE_NAME, defaultValue = EXAMPLE_FILE_NAME)
    @JsonProperty("fileName")
    @NotNull(message = "File name can't be null.")
    private String fileName;

    @Schema(description = DESCRIPTION_LINK, example = EXAMPLE_LINK, defaultValue = EXAMPLE_LINK)
    @JsonProperty("link")
    @NotNull(message = "Link can't be null.")
    private String link;

    @Schema(description = DESCRIPTION_DATE_OF_CREATION, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("dateOfCreation")
    @NotNull(message = "Date of creation can't be null.")
    private Timestamp dateOfCreation;

    @Schema(description = DESCRIPTION_DATE_OF_UPDATE, example = EXAMPLE_DATE, defaultValue = EXAMPLE_DATE)
    @JsonProperty("dateOfUpdate")
    @NotNull(message = "Date of update can't be null.")
    private Timestamp dateOfUpdate;
}
