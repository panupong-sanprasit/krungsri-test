package com.krungsri.test.common.abs;

import java.util.List;

public interface QueryService<QUERY, RESULT> {
    List<RESULT> query(QUERY query); 
}
