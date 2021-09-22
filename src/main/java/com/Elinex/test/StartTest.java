package com.Elinex.test;

import com.Elinex.test.Injector.Injector;
import com.Elinex.test.Injector.InjectorImpl;
import com.Elinex.test.Provider.Provider;
import com.Elinex.test.TestClasses.SecondForTest;
import com.Elinex.test.TestClasses.SecondTestImpl;
import com.Elinex.test.TestClasses.forTest;
import com.Elinex.test.TestClasses.forTestImpl;

public class StartTest {

    public static void main(String[] args) throws Exception {
        Injector inj = new InjectorImpl();
        try {
            inj.bind(forTest.class, forTestImpl.class);
            inj.bindSingleton(SecondForTest.class, SecondTestImpl.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Provider<forTest> test = inj.getProvider(forTest.class);
        inj.getProvider(SecondForTest.class).getInstance().print();
        test.getInstance().print();
    }
}
