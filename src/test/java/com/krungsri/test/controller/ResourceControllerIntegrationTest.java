package com.krungsri.test.controller;

import com.google.gson.Gson;
import com.krungsri.test.Application;
import com.krungsri.test.entity.User;
import com.krungsri.test.exception.UserNotFoundException;
import com.krungsri.test.model.user.request.UserRequest;
import com.krungsri.test.service.user.GetUserService;
import com.krungsri.test.service.user.SaveUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.security.InvalidParameterException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class ResourceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @InjectMocks
    private ResourceController resourceController;
    @Mock
    private GetUserService getService;
    @Mock
    private SaveUserService saveUserService;
    
    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(this.resourceController).build();
    }

    @Test
    public void whenGetUser_thenSuccess() throws Exception {
        when(getService.get(any())).thenReturn(new User());
        mockMvc.perform(get("/krungsri/api/v1/user/1")).andExpect(status().isOk());
        verify(getService, times(1)).get(anyLong());
    }

    @Test
    public void whenCreateUser_thenSuccess() throws Exception { 
        Gson gson = new Gson();
        when(saveUserService.save(any())).thenReturn(new User());
        mockMvc.perform(post("/krungsri/api/v1/user/create")
                .content(gson.toJson(new UserRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        verify(saveUserService, times(1)).save(any());
    }

    @Test
    public void whenCreateUser_thenThrowInvalidParameterException() throws Exception {
        Gson gson = new Gson();
        when(saveUserService.save(any())).thenThrow(InvalidParameterException.class);
        mockMvc.perform(post("/krungsri/api/v1/user/create")
                .content(gson.toJson(new UserRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetUser_thenThrowUserNotFoundException() throws Exception {
        when(getService.get(any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/krungsri/api/v1/user/0")).andExpect(status().isNotFound());
    }
}