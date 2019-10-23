package com.krungsri.test.controller;

import com.krungsri.test.common.abs.GetService;
import com.krungsri.test.common.abs.SaveService;
import com.krungsri.test.entity.User;
import com.krungsri.test.exception.UserNotFoundException;
import com.krungsri.test.model.user.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/krungsri/api/v1/user")
public class ResourceController {
    
    @Autowired
    private GetService<Long, User> getService;
    
    @Autowired
    private SaveService<UserRequest, User> saveService ;
    
    @RequestMapping(value ="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('STANDARD_USER')")
    public ResponseEntity createUser(@RequestBody UserRequest user) throws Exception {
        
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(saveService.save(user));
        } catch (InvalidParameterException ipe){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ipe.getMessage());
        }
    }
    
    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('STANDARD_USER')")
    public ResponseEntity getUser(@PathVariable("id") Long id) throws Exception{
        try{
            return ResponseEntity.ok(getService.get(id));
        } catch (UserNotFoundException uex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uex.getMessage());
        }
    }
    
}
