package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.CardControllerV2;
import com.andersen.banking.meeting_api.dto.CardResponseDto;

import java.util.UUID;

import com.andersen.banking.meeting_impl.mapper.CardMapper;
import com.andersen.banking.meeting_impl.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.andersen.banking.meeting_impl.util.AuthServiceUtil.extractIdFromToken;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerV2Impl implements CardControllerV2 {

    private final CardMapper cardMapper;
    private final CardService cardService;


    @Override
    public CardResponseDto findById(Authentication authentication, UUID id) {
        return null;
    }

    @Override
    public Page<CardResponseDto> findAll(Authentication authentication, Pageable pageable) {
        log.info("Receiving request for getting all cards for user: {}", authentication.getDetails());

        try {

            Jwt jwt = (Jwt) authentication.getPrincipal();

            String user_id = extractIdFromToken(jwt);

            UUID user_uuid = UUID.fromString(user_id);

            Page<CardResponseDto> result =
                    cardService.findByOwnerId(user_uuid, pageable).map(cardMapper::toCardResponseDto);

            log.info("Returning page of cards: {}", result.getContent());

            return result;
        } catch (ClassCastException e) {
            log.info("Can't get token for auth : {}", authentication);

            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Your token is invalid", e);
        } catch (Exception e) {
            log.error("Exception for auth {} : {}", authentication, e);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
