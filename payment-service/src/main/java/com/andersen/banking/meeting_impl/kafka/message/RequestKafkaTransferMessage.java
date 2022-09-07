package com.andersen.banking.meeting_impl.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestKafkaTransferMessage {

    @NotNull private UUID transferId;

    @NotNull private UUID userId;

    @NotBlank private String sourceNumber;

    @NotBlank private String sourceType;

    @NotBlank private String destinationNumber;

    @NotBlank private String destinationType;

    @NotNull private Long amount;

    @NotBlank private String currencyName;
}
