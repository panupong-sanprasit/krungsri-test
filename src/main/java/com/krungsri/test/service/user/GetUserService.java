package com.krungsri.test.service.user;

import com.krungsri.test.common.abs.GetService;
import com.krungsri.test.entity.User;
import com.krungsri.test.exception.UserNotFoundException;
import com.krungsri.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService implements GetService<Long, User> {

    
    private UserRepository userRepository;

    @Autowired
    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Long query) {
        Optional<User> user = userRepository.findById(query);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("User not found");
    }    

}
