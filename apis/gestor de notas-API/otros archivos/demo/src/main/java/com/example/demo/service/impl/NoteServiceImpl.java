package com.example.demo.service.impl;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getNotes(){
        List<Note> noteList = this.noteRepository.findAll();
        return noteList;
    };

    @Override
    public Note postNote(Note note){
        return this.noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long id){
        Optional<Note> note = this.noteRepository.findById(id);
        return note.orElse(null);
    }

    @Override
    public Note putNoteById(Long id, Note updateNote){
        try{
            Optional<Note> opNote = this.noteRepository.findById(id);
            if(opNote.isEmpty()){
                throw new RuntimeException("Note not found");
            }
            Note existingNote = opNote.get();
            existingNote.setTitle(updateNote.getTitle());
            existingNote.setContent(updateNote.getContent());

            return this.noteRepository.save(existingNote);

        }catch(Exception e){
            throw new RuntimeException("Error updating note",e);
        }

    }

    @Override
    public void deleteNoteById(Long id){
        this.noteRepository.findById(id);
        this.noteRepository.deleteById(id);
    }
}