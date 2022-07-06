package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.NotificationDto;
import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for notification.
 */

@Mapper(config = MapperConfig.class)
public interface NotificationMapper {

    NotificationDto toNotificationDto(Notification notification);
}
