package com.example.demo.service;

import com.example.demo.entity.Note;

import java.util.List;

public interface NoteService {
    public List<Note> getNotes();
    public Note postNote(Note note);
    public Note getNoteById(Long id);
    public Note putNoteById(Long id,Note updateNote);
    public void deleteNoteById(Long id);
}
