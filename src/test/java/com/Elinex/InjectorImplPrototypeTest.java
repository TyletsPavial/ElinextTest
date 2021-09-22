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

public class InjectorImplPrototypeTest {

    @BeforeClass
    public static void before() throws Exception {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        injector.bind(forTest.class, forTestImpl.class); //добавляем в инжектор реализацию интерфейса
        injector.bind(SecondForTest.class, SecondTestImpl.class);
    }

    @Test
    public void testExistingPrototypeBinding() throws Exception {
        Injector injector = new InjectorImpl(); //создаем имплементацию инжектора
        Provider<forTestImpl> forTestProvider = injector.getProvider(forTestImpl.class); //получаем инстанс класса из инжектора
        Provider<forTestImpl> forTestProviderTwo = injector.getProvider(forTestImpl.class);
        assertNotNull(forTestProvider);
        assertNotNull(forTestProvider.getInstance());
        assertFalse(forTestProviderTwo.getInstance() == forTestProvider.getInstance());

    }
}
