package com.Elinex.test.Provider;

import java.lang.reflect.InvocationTargetException;

public interface Provider<T>{
    public T getInstance() throws Exception;
}
