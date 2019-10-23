package com.krungsri.test.service.user;

import com.krungsri.test.common.abs.ValidateService;
import com.krungsri.test.exception.InvalidUserParamException;
import com.krungsri.test.model.user.request.UserRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateUserServiceTest {
    
    
    private ValidateService<UserRequest> validateService;
    private UserRequest successUser;
    private UserRequest nullSalaryUser;
    private UserRequest salsryLess15000User;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    
    @Before
    public void setUp() {
        validateService = new ValidateUserService() ;

        firstName = "Panupong";
        lastName = "Sanprasit";
        userName = "panupong";
        password = "panupong2524";
        email = "sanprasit.panupong@gmail.com";
        address ="82/84 Moo13 Bangkeaw Bangplee Samutprakarn 10540";
        phoneNumber = "0875145175";

        successUser = createUserRequest();
        successUser.setSalary(30000);
        nullSalaryUser = createUserRequest();
        salsryLess15000User = createUserRequest();
        salsryLess15000User.setSalary(4000);
        
    }

    private UserRequest createUserRequest(){
        UserRequest from = new UserRequest();
        from.setAddress(address);
        from.setEmail(email);
        from.setFirstName(firstName);
        from.setLastName(lastName);
        from.setUserName(userName);
        from.setPassword(password);
        from.setPhoneNumber(phoneNumber);
        return from;
    }
    
    @Test
    public void whenValidateUserRequest_thenSuccess() throws Exception {
        validateService.validate(successUser);
    }

    @Test
    public void whenValidateUserRequestWithNullSalary_thenThrowInvalidUserParamException() {
        assertThrows(InvalidUserParamException.class, () -> { validateService.validate(nullSalaryUser); });
    }

    @Test
    public void whenValidateUserRequestWithSalaryLessThen15000_thenThrowInvalidUserParamException() {
        assertThrows(InvalidUserParamException.class, () -> { validateService.validate(salsryLess15000User); });
    }
    
}