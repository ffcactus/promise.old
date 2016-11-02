package com.promise.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application
{
    private static final String[] configFiles = {
            "services.xml"
    };
    private static ApplicationContext context;

    public static void main(String[] argc)
    {
        context = new ClassPathXmlApplicationContext(configFiles);
        final PetStore petStore = context.getBean("petStore", PetStore.class);
        System.out.println(petStore.getName());
    }
}
