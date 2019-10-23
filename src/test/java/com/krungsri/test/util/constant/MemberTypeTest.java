package com.krungsri.test.util.constant;

import com.krungsri.test.exception.InvalidUserParamException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class MemberTypeTest {
    
    private final Integer salaryPlatinum = new Integer(50001);
    private final Integer salaryGold = new Integer(30001);
    private final Integer salarySilver = new Integer(15001);
    private final Integer invalidSalary = new Integer(14999);

    @Test
    public void whenGetMemberType_thenGetPlatinumType() {
        assertThat(MemberType.getMemberType(salaryPlatinum)).isEqualTo(MemberType.PLATINUM.getType());
    }

    @Test
    public void whenGetMemberType_thenGetGoldType() {
        assertThat(MemberType.getMemberType(salaryGold)).isEqualTo(MemberType.GOLD.getType());
    }

    @Test
    public void whenGetMemberType_thenGetSilverType() {
        assertThat(MemberType.getMemberType(salarySilver)).isEqualTo(MemberType.SILVER.getType());
    }
    
    @Test
    public void whenExceptionThrown_thenThrowInvalidUserParamException() {
        assertThrows(InvalidUserParamException.class, () -> { MemberType.getMemberType(invalidSalary); });
    }
}