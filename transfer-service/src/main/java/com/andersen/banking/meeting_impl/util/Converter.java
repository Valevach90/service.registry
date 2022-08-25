package com.andersen.banking.meeting_impl.util;

@FunctionalInterface
public interface Converter<T1, T2> {

    T1 convert(T2 o);

}
