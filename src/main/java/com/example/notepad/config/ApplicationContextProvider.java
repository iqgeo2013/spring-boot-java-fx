package com.example.notepad.config;

import com.example.notepad.NotepadSpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContextProvider {

    private static ConfigurableApplicationContext applicationContext;

    private ApplicationContextProvider() {
        // Private constructor to prevent instantiation
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            synchronized (ApplicationContextProvider.class) {
                if (applicationContext == null) {
                    applicationContext = new SpringApplicationBuilder(NotepadSpringBootApplication.class).run();
                }
            }
        }
        return applicationContext;
    }
}

