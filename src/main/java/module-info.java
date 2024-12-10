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
    requires org.hibernate.orm.core;
    requires jakarta.validation;
    requires jakarta.servlet;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.module.paramnames;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example.notepad to javafx.fxml, spring.core, spring.beans;
    opens com.example.notepad.controller to javafx.fxml, spring.core;
    opens com.example.notepad.service to spring.core;
    opens com.example.notepad.model;
    opens com.example.notepad.config;

    exports com.example.notepad;
    exports com.example.notepad.controller;
    exports com.example.notepad.service;
    exports com.example.notepad.model;
    exports com.example.notepad.config;

}