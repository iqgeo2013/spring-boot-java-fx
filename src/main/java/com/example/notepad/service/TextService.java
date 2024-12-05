package com.example.notepad.service;

import org.springframework.stereotype.Service;

@Service
public class TextService {
    public String toUpperCase(String text) {
        return text.toUpperCase();
    }
}
