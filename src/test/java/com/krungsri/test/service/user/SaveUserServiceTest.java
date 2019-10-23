package com.krungsri.test.service.user;

import com.krungsri.test.entity.User;
import com.krungsri.test.model.user.request.UserRequest;
import com.krungsri.test.repository.UserRepository;
import com.krungsri.test.util.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveUserServiceTest {
    
    @InjectMocks
    private SaveUserService saveService;

    @Mock
    private ValidateUserService validator;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserRepository repository;

    private UserRequest user = new UserRequest();
    
    private User savedUser = new User();
    
    @Before
    public void setUp() {
    }

    @Test
    public void whenSaveUser_thenSuccess() throws Exception {
        when(mapper.from(user)).thenReturn(savedUser);
        when(repository.save(any())).thenReturn(savedUser);
        assertThat(saveService.save(user)).isEqualTo(savedUser);
        verify(mapper, times(1)).from(any());
        verify(validator, times(1)).validate(any());
    }
}