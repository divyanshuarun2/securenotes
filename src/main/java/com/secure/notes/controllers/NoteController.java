package com.secure.notes.controllers;

import com.secure.notes.models.Note;
import com.secure.notes.services.NoteService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
// @AutheticationPrincipal annotation is coming from spring securty, it is used to inject currently authenticated user into the controller
        String username= userDetails.getUsername(); // extracting username first
        System.out.println("user details: "+username);
    return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getUserNote(@AuthenticationPrincipal UserDetails userDetails){
        String username= userDetails.getUsername();
        System.out.println("user details: "+username);
        return noteService.getNotesForUser(username);
    }
    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable Long noteId,
                           @AuthenticationPrincipal UserDetails userDetails,
                           @RequestBody String content) {
        String username= userDetails.getUsername();
        System.out.println("user details: "+username);
        return noteService.updateNoteForUSer(noteId, username, content);
    }
    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId
    , @AuthenticationPrincipal UserDetails userDetails) {
        String username= userDetails.getUsername();
        System.out.println("user details: "+username);
        noteService.deleteNoteForUSer(noteId, username);
        System.out.println("Note deleted: "+noteId); 
    }
}
