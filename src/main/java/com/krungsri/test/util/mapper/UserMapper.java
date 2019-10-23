package com.krungsri.test.util.mapper;

import com.krungsri.test.common.abs.Mapper;
import com.krungsri.test.entity.User;
import com.krungsri.test.model.user.request.UserRequest;
import com.krungsri.test.util.constant.MemberType;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserMapper implements Mapper<UserRequest, User> {
    
    @Override
    public User from(UserRequest user){
        
        User mappedUser = new User();
        mappedUser.setFirstName(user.getFirstName());
        mappedUser.setLastName(user.getLastName());
        mappedUser.setEmail(user.getEmail());
        mappedUser.setPhoneNumber(user.getPhoneNumber());
        mappedUser.setReferenceCode(createReferenceCode(user));
        mappedUser.setMemberType(MemberType.getMemberType(user.getSalary()));
        mappedUser.setSalary(user.getSalary());
        mappedUser.setUserName(user.getUserName());
        mappedUser.setPassword(user.getPassword());
        mappedUser.setAddress(user.getAddress());
        return mappedUser;
    }

    private String createReferenceCode(UserRequest user){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd" ) ;
        String dateCode= formatter.format(date);
        return dateCode + user.getPhoneNumber().replaceAll("^.+([0-9]{4})","$1");
    }
    
}
