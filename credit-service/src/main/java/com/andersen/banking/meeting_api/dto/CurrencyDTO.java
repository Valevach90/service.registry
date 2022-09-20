package com.andersen.banking.meeting_api.dto;

import com.andersen.banking.meeting_api.utils.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "currency dto")
public class CurrencyDTO {

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY_ID,
        example = OpenApiConstants.EXAMPLE_ID)
    @JsonProperty("id")
    @NotNull(message = "Currency id can't be null")
    private UUID id;

    @Schema(description = OpenApiConstants.DESCRIPTION_CURRENCY,
        example = OpenApiConstants.EXAMPLE_CURRENCY)
    @JsonProperty("currency_name")
    @NotNull(message = "Currency can't be null")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrencyDTO that = (CurrencyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
