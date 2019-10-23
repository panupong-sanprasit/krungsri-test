package com.krungsri.test.service.user;

import com.krungsri.test.common.abs.ValidateService;
import com.krungsri.test.exception.InvalidUserParamException;
import com.krungsri.test.model.user.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserService implements ValidateService<UserRequest> {

    @Override
    public void validate(UserRequest userRequest) {
        validateSalary(userRequest);
        validateTelephoneNumber(userRequest.getPhoneNumber());
    }

    private void validateSalary(UserRequest userRequest) {
        if (userRequest.getSalary() == null){
            throw new InvalidUserParamException("Invalid Salary");
        } else {
            if (userRequest.getSalary() < 15000){
                throw new InvalidUserParamException("Invalid Salary");
            }
        }
    }

    private void validateTelephoneNumber(String telephoneNumber){
        if (!telephoneNumber.matches("^[+]?([- ]?[0-9])+([0-9]{4})$")){
            throw new InvalidUserParamException("Invalid Telephone Number");
        }
    }
    
}
