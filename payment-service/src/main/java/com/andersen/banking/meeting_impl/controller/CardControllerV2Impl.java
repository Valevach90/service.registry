package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_impl.util.AuthServiceUtil.extractUUIDFromToken;

import com.andersen.banking.meeting_api.controller.CardControllerV2;
import com.andersen.banking.meeting_api.dto.CardResponseDto;
import com.andersen.banking.meeting_impl.mapper.CardMapper;
import com.andersen.banking.meeting_impl.service.CardService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerV2Impl implements CardControllerV2 {

    private final CardMapper cardMapper;
    private final CardService cardService;


    @Override
    public Page<CardResponseDto> findAll(Authentication authentication, Pageable pageable) {
        log.info(
                "Receiving request for getting all cards for user: {}",
                authentication.getDetails());

        Jwt jwt = (Jwt) authentication.getPrincipal();

        UUID user_uuid = extractUUIDFromToken(jwt);

        Page<CardResponseDto> result =
                cardService.findByOwnerId(user_uuid, pageable).map(cardMapper::toCardResponseDto);

        log.info("Returning page of cards: {}", result.getContent());

        return result;
    }
}
