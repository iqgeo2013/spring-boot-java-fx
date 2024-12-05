module com.example.notepad {
    requires spring.context;
    requires javafx.fxml;
    requires spring.boot;
    requires javafx.controls;
    requires spring.boot.autoconfigure;
    requires jdk.jfr;
    requires org.slf4j;
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires jakarta.annotation;
    requires spring.core;
    requires spring.beans;
    requires java.desktop;

    opens com.example.notepad to javafx.fxml, spring.core, spring.beans;
    opens com.example.notepad.controller to javafx.fxml, spring.core;
    opens com.example.notepad.service to spring.core;
    opens com.example.notepad.model to spring.core, spring.beans;

    exports com.example.notepad;
    exports com.example.notepad.controller;
    exports com.example.notepad.service;
}