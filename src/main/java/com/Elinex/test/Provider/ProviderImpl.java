package com.Elinex.test.Provider;

import com.Elinex.test.Annotations.Reject;
import com.Elinex.test.Enums.Scope;
import com.Elinex.test.Exceptions.BindingNotFoundException;
import com.Elinex.test.Exceptions.ConstructorNotFoundException;
import com.Elinex.test.Exceptions.InvalidScopeException;
import com.Elinex.test.Exceptions.TooManyConstructorsException;
import com.Elinex.test.Injector.Injector;
import com.Elinex.test.Injector.InjectorImpl;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProviderImpl<T extends Object> implements Provider{
    private Class<T> instClass;
    final private Scope scope;
    private T instance;

    public ProviderImpl(Class<T> instClass, Scope scope){
        this.instClass = instClass;
        this.scope = scope;
    }

    synchronized public T getInstance() throws Exception {
        if (this.scope.equals(Scope.PROTOTYPE)){
            return buildInstance();
        }
        if (this.scope.equals(Scope.SINGLETONE)){
            if (instance != null){
                return instance;
            }
            instance = buildInstance();
            return instance;
        }
        throw new InvalidScopeException();
    }

    private T buildInstance() throws Exception {
        Constructor constr;
        Set paramObj;

        List<Constructor> constructors = Arrays.stream(instClass.getConstructors()).
                filter(x -> Arrays.stream(x.getDeclaredAnnotations()).
                        filter(a -> a.annotationType().equals(Reject.class)).count() > 0 ).
                collect(Collectors.toList());
        if (constructors.size() > 1){
            throw new TooManyConstructorsException();
        }
        if (constructors.size() == 0){
            constr = instClass.getDeclaredConstructor();
            if (constr == null) throw new ConstructorNotFoundException();
            return instClass.getDeclaredConstructor().newInstance();
        }
        constr = constructors.get(0);
        paramObj = Arrays.stream(constr.getParameterTypes()).map(x -> {
            Injector inj = new InjectorImpl();
            try {
                return inj.getProvider(x).getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toSet());
        if (paramObj.contains(null)) throw new BindingNotFoundException();
        return (T) constr.newInstance(paramObj.toArray());
    }
}
