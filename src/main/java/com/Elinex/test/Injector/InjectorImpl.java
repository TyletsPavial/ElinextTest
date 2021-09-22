package com.Elinex.test.Injector;
import com.Elinex.test.Enums.Scope;
import com.Elinex.test.Provider.ProviderImpl;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InjectorImpl implements Injector{
    volatile private static ConcurrentMap<String, ProviderImpl> cont = new ConcurrentHashMap<>();
    @Override
    public <I> void bind(Class<I> intf, Class<? extends I> impl) throws Exception {
        this.bind(intf, impl, Scope.PROTOTYPE);
    }
    @Override
    public <I> void bindSingleton(Class<I> intf, Class<? extends I> impl) throws Exception {
        this.bind(intf, impl, Scope.SINGLETONE);
    }
    private <I> void bind(Class<I> intf, Class<? extends I> impl, Scope scope) throws Exception {
        if (cont.get(intf.getName()) == null){
            cont.put(intf.getName(),new ProviderImpl(impl, scope));
        }
        else{
          throw new Exception("Class has already been bound");
        } ;
    }



    public <T> ProviderImpl<T> getProvider(Class<T> cls) {
        if (cls.isInterface() && cont.get(cls.getName()) != null){
             return cont.get(cls.getName());
        }
        Class[] clss = cls.getInterfaces();
        for (Class aCls : clss){
            if  (cont.get(aCls.getName()) != null) {
                return cont.get(aCls.getName());
            }
        }
        return null;
    }
}
