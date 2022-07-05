package com.andersen.banking.service.registry.meeting_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response entity for push notification.")
public class PushNotificationResponse {

  private int status;
  private String message;
}
