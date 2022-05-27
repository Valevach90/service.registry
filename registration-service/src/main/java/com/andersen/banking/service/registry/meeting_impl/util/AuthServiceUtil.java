package com.andersen.banking.service.registry.meeting_impl.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthServiceUtil {

    public static MultiValueMap<String, String> prepareBodyToGetAccessToken(String userName, String password, String grantType, String clientId){

        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("username", userName);
        bodyValues.add("password", password);
        bodyValues.add("grant_type", grantType);
        bodyValues.add("client_id", clientId);

        return bodyValues;
    }
    public static String extractAccessTokenFromJson(String token){

        final String json = token;
        final ObjectNode node;
        try {
            node = new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        final String accessToken = String.valueOf(node.get("access_token"));

        return accessToken.substring(1, accessToken.length() - 1);
    }
    public static String prepareRoleInJson(String roleId, String roleName){

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode role = mapper.createObjectNode();
        role.put("id", roleId);
        role.put("name", roleName);

        String roles;
        try {
            roles = "[" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(role) + "]";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    public static String extractIdFromToken(Jwt jwt){

        return jwt.getClaim("sub").toString();
    }
    public static String extractLoginFromToken(Jwt jwt){

        return jwt.getClaim("preferred_username").toString();
    }
    public static boolean doesUserHaveNoRoles(Jwt jwt){

        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");

        if (resourceAccess.get("gateway") == null) {
            return true;
        }
        return false;
    }
}
