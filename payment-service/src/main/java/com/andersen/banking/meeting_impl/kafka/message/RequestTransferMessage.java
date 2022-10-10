package com.andersen.banking.meeting_impl.kafka.message;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTransferMessage {

    @NotNull
    private UUID transferId;

    @NotNull
    private UUID userId;

    @NotBlank
    private String sourceNumber;

    @NotBlank
    private String sourceType;

    @NotBlank
    private String destinationNumber;

    @NotBlank
    private String destinationType;

    @NotNull
    private Long amount;

    @NotNull
    private Integer status;

    @NotBlank
    private String currencyName;

    private UUID regularId;
}
