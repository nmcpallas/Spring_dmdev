package com.cpallas.mapper;

public interface Mapper<F, T> {

    default T map(F fromObject, T toObject) {
        return toObject;
    }

    T map(F object);
}
