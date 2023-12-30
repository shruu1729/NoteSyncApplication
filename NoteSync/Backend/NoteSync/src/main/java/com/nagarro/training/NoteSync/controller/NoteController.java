package com.nagarro.training.NoteSync.controller;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nagarro.training.NoteSync.models.Note;
import com.nagarro.training.NoteSync.models.User;
import com.nagarro.training.NoteSync.services.impl.NoteServiceImpl;


@RestController
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteServiceImpl noteServiceImpl;

	// Add a Note
	@PostMapping
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<Note> createNote(@RequestBody Note note) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		note.setAuthor(new User(currentUsername));
		Note createdNote = noteServiceImpl.createNote(note);
		if (createdNote != null)
			return ResponseEntity.ok(createdNote);
		else
			return ResponseEntity.notFound().build();
	}

	// Show 10 most recent Notes to the user
	@GetMapping
	@PreAuthorize("hasRole('User')")
	public List<Note> getRecentNotes() {
		// Get the current user's username from the authentication context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		// Retrieve the 10 most recently modified notes of the current user
		List<Note> recentNotes = noteServiceImpl.getRecentModifiedNotes(currentUsername);
		return recentNotes;
	}

	// Show A Particular Note to the user
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('User')")
	public Note viewNotes(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		return noteServiceImpl.getNotebyID(currentUsername, id);
	}

	// Edit an exiting Note
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note updatedNote) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		Note editedNote = noteServiceImpl.editNote(id, updatedNote, currentUsername);
		if (editedNote != null) {
			return ResponseEntity.ok(editedNote);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Deleting an existing Note
	@DeleteMapping("/{noteId}")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<Boolean> deleteNoteById(@PathVariable Long noteId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		if (noteServiceImpl.deleteNoteById(noteId, currentUsername))
			return ResponseEntity.ok(true);
		else
			return ResponseEntity.notFound().build();

	}

}