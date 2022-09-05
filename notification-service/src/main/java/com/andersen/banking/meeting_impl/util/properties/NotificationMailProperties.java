package com.andersen.banking.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("notification.mail")
public class NotificationMailProperties {

    private String host;
    private int port;
    private Sender sender;
    private Code code;
    private Blocking blocking;

    @Getter
    @Setter
    public static class Sender {

        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class Code {

        private int length;
        private Time valid;
    }

    @Getter
    @Setter
    public static class Blocking {

        private Time time;
    }

    @Getter
    @Setter
    public static class Time {

        private int millis;
    }
}
