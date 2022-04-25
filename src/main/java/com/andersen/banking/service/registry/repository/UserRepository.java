package com.andersen.banking.service.registry.repository;

import com.andersen.banking.service.registry.domain.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
