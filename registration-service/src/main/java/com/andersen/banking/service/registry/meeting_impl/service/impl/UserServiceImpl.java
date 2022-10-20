package com.andersen.banking.service.registry.meeting_impl.service.impl;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.generateRandomPassword;

import com.andersen.banking.service.registry.meeting_api.dto.UserEmailUpdateRepresentation;
import com.andersen.banking.service.registry.meeting_api.dto.UserRepresentation;
import com.andersen.banking.service.registry.meeting_api.dto.UserRepresentation.Credentials;
import com.andersen.banking.service.registry.meeting_api.dto.UserRepresentationResponse;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import com.andersen.banking.service.registry.meeting_db.entities.User.UserBuilder;
import com.andersen.banking.service.registry.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.service.AdminService;
import com.andersen.banking.service.registry.meeting_impl.service.UserService;
import com.andersen.banking.service.registry.meeting_impl.util.KeycloakUrlUtil;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.Conflict;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakProperties keycloak;

    private final AdminService adminService;

    private final ObjectMapper mapper;

    private final WebClient client = WebClient.create();

    @Override
    public User create(User user) {
        log.info("creating user: {}", user);

        UserRepresentation userRepresentation = setParameter(user);
        setTemporaryPassword(userRepresentation);

        String token = adminService.obtainAccessToken();

        client.method(HttpMethod.POST)
                .uri(KeycloakUrlUtil.getUrlForUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRepresentation)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Object[] users = client.method(HttpMethod.GET)
                .uri(KeycloakUrlUtil.getUrlForGetUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        user.getUsername()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRepresentation)
                .retrieve()
                .bodyToMono(Object[].class)
                .block();

        User savedUser = Arrays.stream(users)
                .map(object -> mapper.convertValue(object, UserRepresentationResponse.class))
                .map(this::getParameter)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NotFoundException(User.class, user.getUsername());
                });

        savedUser.setPassword(userRepresentation.getCredentials()[0].getValue());

        log.info("created user: {}", savedUser);
        return savedUser;
    }

    @Override
    public User findById(UUID id) {
        log.debug("Find user by id: {}", id);

        String token = adminService.obtainAccessToken();

        Object user = client.method(HttpMethod.GET)
                .uri(KeycloakUrlUtil.getUrlForCurrentUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id.toString()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        User findUser = getParameter(mapper.convertValue(user,
                UserRepresentationResponse.class));

        log.debug("Return user: {}", findUser);
        return findUser;
    }

    @Override
    public User findByEmail(String email) {
        log.debug("Find user by email: {}", email);

        String token = adminService.obtainAccessToken();

        Object[] users = client.method(HttpMethod.GET)
                .uri(KeycloakUrlUtil.getUrlForGetUserEmail(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        email
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class)
                .block();
        User findUser = getParameter(mapper.convertValue(users[0],
                UserRepresentationResponse.class));

        log.debug("Return user: {}", findUser);
        return findUser;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("Find all users for pageable: {}", pageable);

        String token = adminService.obtainAccessToken();

        Object[] users = client.method(HttpMethod.GET)
                .uri(KeycloakUrlUtil.getUrlForUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object[].class)
                .block();

        List<User> findUsers = Arrays.stream(users)
                .map(object -> mapper.convertValue(object, UserRepresentationResponse.class))
                .map(this::getParameter)
                .toList();

        Page<User> allUsers = new PageImpl<>(findUsers, pageable, findUsers.size());

        log.info("Found {} users", allUsers.getContent().size());
        return allUsers;
    }

    @Override
    public void update(User user) {
        log.debug("Trying to update user: {}", user);

        String token = adminService.obtainAccessToken();

        UserRepresentation userRepresentation = setParameter(user);

        client.method(HttpMethod.PUT)
                .uri(KeycloakUrlUtil.getUrlForCurrentUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        user.getId().toString()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRepresentation)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        log.debug("Return updated User: {}", user);
    }

    @Override
    public void updateEmail(User user) {
        log.trace("Trying to update users email: {}", user);

        String token = adminService.obtainAccessToken();

        UserEmailUpdateRepresentation userRepresentation = setParameterForUpdateEmail(user);

        try {
            client.method(HttpMethod.PUT)
                    .uri(KeycloakUrlUtil.getUrlForCurrentUser(
                            keycloak.getAuthServerUrl(),
                            keycloak.getRealm(),
                            user.getId().toString()
                    ))
                    .headers(header -> header.setBearerAuth(token))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(userRepresentation)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Conflict e) {
            throw new ValidationException("User with email: " + user.getEmail() + " exist");
        }

        log.trace("Return user with updated email: {}", user);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("deleting user with id: {}", id);

        String token = adminService.obtainAccessToken();

        Object user = client.method(HttpMethod.DELETE)
                .uri(KeycloakUrlUtil.getUrlForCurrentUser(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id.toString()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        log.info("deleted user: {} with id: {}", user, id);
    }

    private UserRepresentation setParameter(User user) {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("phone", user.getPhone());
        if (user.getPatronymic() != null) {
            attributes.put("patronymic", user.getPatronymic());
        }
        return UserRepresentation.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .attributes(attributes)
                .username(user.getUsername())
                .enabled(true)
                .build();
    }

    private UserEmailUpdateRepresentation setParameterForUpdateEmail(User user) {
        return UserEmailUpdateRepresentation.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    private User getParameter(UserRepresentationResponse userRep) {
        UserBuilder builder = User.builder()
                .id(userRep.getId())
                .username(userRep.getUsername())
                .firstName(userRep.getFirstName())
                .lastName(userRep.getLastName())
                .email(userRep.getEmail());
        if (userRep.getAttributes() != null) {
            builder.phone(userRep.getAttributes().getPhone()[0]);
            if (userRep.getAttributes().getPatronymic() != null) {
                builder.patronymic(userRep.getAttributes().getPatronymic()[0]);
            }
        }
        return builder.build();
    }

    private void setTemporaryPassword(UserRepresentation userRepresentation) {
        userRepresentation.setCredentials(
                new Credentials[]{
                        Credentials.builder()
                                .type("password")
                                .value(generateRandomPassword(10))
                                .temporary("true")
                                .build()
                }
        );
    }
}
