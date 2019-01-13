package com.project.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<LibraryUser,Integer> {

    LibraryUser getByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByLoginAndPassword(String login,String password);

}
