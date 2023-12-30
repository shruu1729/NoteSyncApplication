package com.nagarro.training.NoteSync.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.NoteSync.models.Note;
import com.nagarro.training.NoteSync.repositories.NoteRepository;

import org.springframework.scheduling.annotation.Scheduled;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NoteServiceImpl {

	@Autowired
	private NoteRepository noteRepository;

	// create a Note
	public Note createNote(Note note) {
		Note createdNote = noteRepository.save(note);
		if (createdNote != null)
			return createdNote;
		else
			return null;
	}
	
	//show a note by id
	public Note getNotebyID(String currentUsername, Long id) {
		Optional<Note> optionalNote = noteRepository.findById(id);
		if (optionalNote.isPresent() && optionalNote.get().getAuthor().getUserName().equals(currentUsername))
		{
			Note existingNote = optionalNote.get();
			return existingNote;
			}
		else
			return null;
	}

	// Show the 10 Most recently modified Notes by the User
	public List<Note> getRecentModifiedNotes(String username) {
		// Retrieve the 10 most recently modified notes of the current user
		return noteRepository.findTop10ByAuthorUserNameOrderByModifiedDateDescModifiedTimeDesc(username);
	}

	// Edit an existing Note
	public Note editNote(Long id, Note updatedNote, String currentUserName) {
		Optional<Note> optionalNote = noteRepository.findById(id);
		// Check if the user is editing their own Note and not some other user's
		if (optionalNote.isPresent() && optionalNote.get().getAuthor().getUserName().equals(currentUserName)) {
			Note existingNote = optionalNote.get();

			// Update fields from the updatedNote
			existingNote.setTitle(updatedNote.getTitle());
			existingNote.setContent(updatedNote.getContent());
			existingNote.setImportant(updatedNote.getImportant());

			// Update modified date and time
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			Date currentDate = new Date();
			existingNote.setModifiedDate(dateFormat.format(currentDate));
			existingNote.setModifiedTime(timeFormat.format(currentDate));

			return noteRepository.save(existingNote);
		}
		return null;
	}

	// Service method to delete a note by its ID
	public boolean deleteNoteById(Long noteId, String currentUserName) {
		Optional<Note> optionalNote = noteRepository.findById(noteId);
		// Check if the user is trying to delete their own Note
		if (optionalNote.isPresent() && optionalNote.get().getAuthor().getUserName().equals(currentUserName)) {
			noteRepository.deleteById(noteId);
			return true;
		} else
			return false;
	}

	// Method which allows the system to delete all the old Notes of each user
	@Scheduled(fixedRate = 3600000) // Run every 60 minutes (in milliseconds) 3 min =180000 ms,5 min = 300000 
	@Transactional
	public void deleteOldNotes() {
		// Logging the time of starting of deletion
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formattedDate = sdf.format(new Date());
		//System.out.println("Hourly Deletion Started at " + formattedDate);

		// Iterate over each user and keep only the 10 most recent notes
		List<String> allUsernames = noteRepository.findAllUsernames();
		for (String username : allUsernames) {
			List<Long> noteIdsToKeep = noteRepository.findRecentModifiedNoteIds(username);

			// Keep only the first 10 elements of noteIdsToKeep
			if (noteIdsToKeep.size() > 10) {
				noteIdsToKeep = noteIdsToKeep.subList(0, 10);
			}

			noteRepository.deleteOldNotes(username, noteIdsToKeep);
		}
	}



}
