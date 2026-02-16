package com.example.demo.controller;

import com.example.demo.entity.Note;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.NoteService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteServiceImpl) {
        this.noteService = noteServiceImpl;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = this.noteService.getNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping(
            value = "/crear",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Note> postNote(@Valid @RequestBody Note note){
        return new ResponseEntity<>(this.noteService.postNote(note), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return new ResponseEntity<>(this.noteService.getNoteById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Note> putNoteById(@PathVariable Long id,@RequestBody @Valid Note updateNote){
        return ResponseEntity.ok(
                this.noteService.putNoteById(id, updateNote)
        );
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id){
        this.noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
