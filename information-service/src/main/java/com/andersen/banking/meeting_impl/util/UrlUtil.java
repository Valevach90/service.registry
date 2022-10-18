package com.andersen.banking.meeting_impl.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlUtil {

    public static String getUrlForGettingExchangeRates(String url, String currency) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("base", currency)
                .toUriString();
    }
}
