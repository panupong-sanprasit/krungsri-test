package com.krungsri.test.util.constant;

import com.krungsri.test.exception.InvalidUserParamException;

public enum MemberType {
    PLATINUM("platinum", 50000, null),
    GOLD("gold", 30000, 50000),
    SILVER("silver",15000,30000);

    private String type;
    private Integer min;
    private Integer max;

    MemberType(String type, Integer min, Integer max) {
        this.type = type;
        this.min = min;
        this.max = max;
    }

    public static String getMemberType(Integer salary){
        MemberType result = null;
        for (MemberType memberType : MemberType.values()) {
            if (memberType.min == null && memberType.max > salary){
                result = memberType;
                continue;
            }
            if (memberType.max == null && memberType.min < salary){
                result = memberType;
                continue;
            }
            if ((memberType.max != null &&  memberType.min != null) && memberType.min < salary && memberType.max > salary){
                result = memberType;
            }
        }
        if (result != null){
            return result.type;
        } else {
            throw new InvalidUserParamException("Invalid Salary");
        }
    }

    public String getType() {
        return type;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}
