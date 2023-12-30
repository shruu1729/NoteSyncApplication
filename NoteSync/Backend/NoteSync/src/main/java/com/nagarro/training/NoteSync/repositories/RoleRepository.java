package com.nagarro.training.NoteSync.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.NoteSync.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}