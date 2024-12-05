package com.example.notepad.service;

import com.example.notepad.model.Note;
import com.example.notepad.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setTitle("Note " + i);
            note.setContent("Content " + i);
            note.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            save(note);
        }
    }
}
