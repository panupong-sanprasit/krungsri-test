package com.krungsri.test.service.user;

import com.krungsri.test.common.abs.Mapper;
import com.krungsri.test.common.abs.SaveService;
import com.krungsri.test.common.abs.ValidateService;
import com.krungsri.test.entity.User;
import com.krungsri.test.model.user.request.UserRequest;
import com.krungsri.test.repository.UserRepository;
import com.krungsri.test.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserService implements SaveService<UserRequest, User> {
    
    private ValidateService<UserRequest> validator;
    
    private UserRepository repository;

    private Mapper<UserRequest, User> mapper;

    @Autowired
    public SaveUserService(ValidateUserService validator, 
                           UserRepository repository, 
                           UserMapper mapper) {
        this.validator = validator;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(UserRequest createUserService) throws Exception {
        validator.validate(createUserService);
        return repository.save(mapper.from(createUserService));
    }
        
}
