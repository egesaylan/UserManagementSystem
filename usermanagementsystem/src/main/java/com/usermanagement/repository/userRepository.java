package com.usermanagement.repository;

import com.usermanagement.model.user;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<user, Integer> {
    public Long countById(Integer id);
}
