package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.RegistrationNotificationDto;
import com.andersen.banking.meeting_db.entity.RegistrationNotification;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for notification.
 */

@Mapper(config = MapperConfig.class)
public interface NotificationMapper {

    RegistrationNotificationDto toNotificationDto(RegistrationNotification registrationNotification);
}
