package com.nagarro.training.NoteSync.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.NoteSync.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
}