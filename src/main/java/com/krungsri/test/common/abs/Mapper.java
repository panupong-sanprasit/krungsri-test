package com.krungsri.test.common.abs;

public interface Mapper<FROM, TO> {
    TO from(FROM from);
}
