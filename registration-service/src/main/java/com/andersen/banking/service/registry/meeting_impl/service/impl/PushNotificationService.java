package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_api.dto.PushNotificationRequest;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotificationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PushNotificationService {

  private FCMService fcmService;

  public void sendPushNotificationToToken(PushNotificationRequest request) {
    try {
      fcmService.sendMessageToToken(request);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new NotificationException("Notification wasn't send.");
    }
  }
}
