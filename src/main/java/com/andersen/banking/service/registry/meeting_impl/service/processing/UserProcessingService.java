package com.andersen.banking.service.registry.meeting_impl.service.processing;

import com.andersen.banking.service.registry.meeting_api.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<UserDto> findAllUsersDto(Pageable pageable);


}
