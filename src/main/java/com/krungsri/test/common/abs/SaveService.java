package com.krungsri.test.common.abs;

public interface SaveService<ENTITY,RESULT> {
    RESULT save(ENTITY entity) throws Exception;
}
