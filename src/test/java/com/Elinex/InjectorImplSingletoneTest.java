package com.Elinex;

import com.Elinex.test.Injector.Injector;
import com.Elinex.test.Injector.InjectorImpl;
import com.Elinex.test.Provider.Provider;
import com.Elinex.test.TestClasses.SecondForTest;
import com.Elinex.test.TestClasses.SecondTestImpl;
import com.Elinex.test.TestClasses.forTest;
import com.Elinex.test.TestClasses.forTestImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

public class InjectorImplSingletoneTest {
    @BeforeClass
    public static void before() throws Exception {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bindSingleton(forTest.class, forTestImpl.class); //добавляем в инжектор реализацию интерфейса
        injector.bindSingleton(SecondForTest.class, SecondTestImpl.class);
    }
    @Test
    public void testExistingBinding() throws Exception {
        Injector injector = new InjectorImpl();
        Provider<forTestImpl> forTestProvider = injector.getProvider(forTestImpl.class);
        assertNotNull(forTestProvider);
        assertNotNull(forTestProvider.getInstance());
        assertSame(forTestImpl.class, forTestProvider.getInstance().getClass());

    }

    @Test
    public void testExistingSingletonBinding() throws Exception {
        Injector injector = new InjectorImpl();
        Provider<forTestImpl> forTestProvider = injector.getProvider(forTestImpl.class);
        Provider<forTestImpl> forTestProviderTwo = injector.getProvider(forTestImpl.class);
        assertNotNull(forTestProvider);
        assertNotNull(forTestProvider.getInstance());
        assertTrue(forTestProviderTwo.getInstance() == forTestProvider.getInstance());

    }


}
