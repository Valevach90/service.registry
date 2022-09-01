package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.NotificationDto;
import com.andersen.banking.meeting_db.entity.Notification;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for notification.
 */

@Mapper(config = MapperConfig.class)
public interface NotificationMapper {

    NotificationDto toNotificationDto(Notification notification);
}
