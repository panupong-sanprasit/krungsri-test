package com.krungsri.test.service.user;

import com.krungsri.test.entity.User;
import com.krungsri.test.exception.UserNotFoundException;
import com.krungsri.test.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUserServiceTest {
    
    @Mock
    private UserRepository repository;
    
    @InjectMocks
    private GetUserService getUserService;
    
    private User user;
    
    @Before
    public void setUp() {
        user = new User();
    }

    
    @Test
    public void whetGetUser_thenSuccess() {
        when(repository.findById(any())).thenReturn(Optional.of(user));
        assertThat(getUserService.get(any())).isEqualTo(user);
        verify(repository, times(1)).findById(any());
    }
    
    @Test
    public void whetUserNotFound_thenThrowUserNotFoundException() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> { getUserService.get(any()); });
        verify(repository, times(1)).findById(any());
    }
    
}