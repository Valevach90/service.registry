package com.andersen.banking.service.payment.meeting_api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "dto for accounts")
public class AccountDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @JsonProperty("issue_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @NotNull
    @JsonProperty("termination_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date terminationDate;

    @NotNull
    @JsonProperty("bank_name")
    private String bankName;

    @NotNull
    @JsonProperty("account_number")
    @Pattern(regexp = "\\d+")
    private String accountNumber;

    @Column(name = "currency", nullable = false)
    private String currency;
}
