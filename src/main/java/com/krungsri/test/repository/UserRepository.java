package com.krungsri.test.repository;

import com.krungsri.test.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
