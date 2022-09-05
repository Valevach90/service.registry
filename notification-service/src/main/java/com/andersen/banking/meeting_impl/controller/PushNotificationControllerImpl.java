package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.PushNotificationController;
import com.andersen.banking.meeting_api.dto.PushNotificationRequest;
import com.andersen.banking.meeting_api.dto.PushNotificationResponse;
import com.andersen.banking.meeting_impl.service.impl.PushNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PushNotificationControllerImpl implements PushNotificationController {

    private PushNotificationService pushNotificationService;

    @Override
    public ResponseEntity<PushNotificationResponse> sendTokenNotification(
            @RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(
                new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."),
                HttpStatus.OK);
    }
}
