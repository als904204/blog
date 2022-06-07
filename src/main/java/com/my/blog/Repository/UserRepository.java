package com.my.blog.Repository;

import com.my.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //@Query(value = "SELECT * FROM user  WHERE username =?1")
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
    User login(String username, String password);
}
