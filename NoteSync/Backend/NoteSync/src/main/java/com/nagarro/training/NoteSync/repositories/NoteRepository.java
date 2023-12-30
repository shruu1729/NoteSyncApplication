package com.nagarro.training.NoteSync.repositories;

import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.nagarro.training.NoteSync.models.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	// show 10 recently modified notes
	List<Note> findTop10ByAuthorUserNameOrderByModifiedDateDescModifiedTimeDesc(String username);

	// show all notes of user
	List<Note> findByAuthor_UserName(String username);

	// show all usernames
	@Query("SELECT DISTINCT n.author.userName FROM Note n")
	List<String> findAllUsernames();

	// find IDs of the 10 most recently modified notes
	@Query("SELECT n.id FROM Note n WHERE n.author.userName = :username ORDER BY n.modifiedDate DESC, n.modifiedTime DESC")
	List<Long> findRecentModifiedNoteIds(@Param("username") String username);

	// delete the notes whose IDs are not given
	@Modifying
	@Query("DELETE FROM Note n WHERE n.author.userName = :username AND n.id NOT IN :noteIds")
	void deleteOldNotes(@Param("username") String username, @Param("noteIds") List<Long> noteIds);

}
