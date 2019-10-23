package com.krungsri.test.util.mapper;

import com.krungsri.test.common.abs.Mapper;
import com.krungsri.test.entity.User;
import com.krungsri.test.model.user.request.UserRequest;
import com.krungsri.test.util.constant.MemberType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    
    private Mapper<UserRequest, User> mapper;
    private UserRequest from;
    private User user;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer salary;
    private String yyyyMMdd;
    private String referenceCode;

    @Before
    public void setUp() {
        mapper = new UserMapper();
        
        firstName = "Panupong";
        lastName = "Sanprasit";
        userName = "panupong";
        password = "panupong2524";
        email = "sanprasit.panupong@gmail.com";
        address ="82/84 Moo13 Bangkeaw Bangplee Samutprakarn 10540";
        salary = 150001;
        phoneNumber = "0875145175";
        
        from = new UserRequest();
        from.setAddress(address);
        from.setEmail(email);
        from.setFirstName(firstName);
        from.setLastName(lastName);
        from.setUserName(userName);
        from.setPassword(password);
        from.setPhoneNumber(phoneNumber);
        from.setSalary(salary);

        user = new User();
        user.setAddress(address);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setSalary(salary);
        user.setMemberType(MemberType.getMemberType(salary));
        prepareReferenceCode();


    }

    private void prepareReferenceCode() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        yyyyMMdd = sf.format(new Date());
        referenceCode = yyyyMMdd+phoneNumber.replaceAll("^.+([0-9]{4})", "$1");
        user.setReferenceCode(referenceCode);
    }

    @Test
    public void whenMapFromUserRequest_thenGetUser() {
        assertThat(user).isEqualToComparingFieldByField(mapper.from(from));
    }
    
    
}