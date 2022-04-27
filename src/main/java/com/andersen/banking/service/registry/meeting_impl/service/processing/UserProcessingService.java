package com.andersen.banking.service.registry.meeting_impl.service.processing;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Processing Service for working with users.
 */

@Service
public interface UserProcessingService {

    /**
     * Find by id user DTO
     *
     * @param userId
     * @return userDRO
     */
    UserDto findByIdUserDto(Long userId);

    /**
     * Return all user DTO
     * @return List<UserDto>
     */
    List<UserDto> findAllUsersDto();

    /**
     * Update user by id
     *
     * @param id
     * @param userDto
     */
    void updateUser(Long id, UserDto userDto);


}
