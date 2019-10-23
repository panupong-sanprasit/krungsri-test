package com.krungsri.test.common.abs;

import com.krungsri.test.exception.UserNotFoundException;

public interface GetService<ID, RESULT> {
    RESULT get(ID id) throws UserNotFoundException; 
}
