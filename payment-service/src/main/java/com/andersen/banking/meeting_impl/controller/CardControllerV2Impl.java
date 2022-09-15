package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.CardControllerV2;
import com.andersen.banking.meeting_api.dto.CardResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerV2Impl implements CardControllerV2 {

    @Override
    public CardResponseDto findById(Authentication authentication, UUID id) {
        return null;
    }

    @Override
    public Page<CardResponseDto> findAll(Authentication authentication, Pageable pageable) {
        log.trace("Receiving request for getting all cards by token with user id: {}");
        Jwt jwt = (Jwt) authentication.getPrincipal();
        if (jwt != null) {
            String user_id = extractIdFromToken(jwt);
        }



        Page<CardResponseDto> result =
                cardService.findByOwnerId(id, pageable).map(cardMapper::toCardResponseDto);

        log.trace("Returning page of cards: {}", result.getContent());

        return result;
    }
}
