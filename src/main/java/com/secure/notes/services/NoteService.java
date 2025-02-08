package com.secure.notes.services;

import com.secure.notes.entity.Note;

import java.util.List;

public interface NoteService {
    Note createNoteForUser(String username, String content);
    Note updateNoteForUSer(Long noteId, String username, String content);
    void deleteNoteForUSer(Long noteId, String username);
    List<Note> getNotesForUser(String username);
}
