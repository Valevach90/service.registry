package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.NotificationDto;
import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T02:30:31+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDto toNotificationDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setEmail( notification.getEmail() );
        notificationDto.setCode( notification.getCode() );
        notificationDto.setTime( notification.getTime() );
        notificationDto.setStatus( notification.getStatus() );

        return notificationDto;
    }
}
