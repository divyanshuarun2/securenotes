package com.secure.notes.services.impl;

import com.secure.notes.entity.Note;
import com.secure.notes.repositories.NoteRepository;
import com.secure.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
       note.setContent(content);
       note.setOwnerUsername(username);
       Note savedNote = noteRepository.save(note);
       return savedNote;

    }

    @Override
    public Note updateNoteForUSer(Long noteId, String username, String content) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                ->new RuntimeException("Note not found"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @Override
    public void deleteNoteForUSer(Long noteId, String username) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository.findByOwnerUsername(username);
        return personalNotes;
    }
}
