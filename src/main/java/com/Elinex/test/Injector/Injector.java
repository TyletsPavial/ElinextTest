package com.Elinex.test.Injector;

import com.Elinex.test.Provider.Provider;
import com.Elinex.test.Provider.ProviderImpl;

public interface Injector {
    public <I> void bind(Class<I> intf, Class<? extends I> impl) throws Exception;
    public <T> ProviderImpl<T> getProvider(Class<T> cls);
    public <I> void bindSingleton(Class<I> intf, Class<? extends I> impl) throws Exception;
}
