package com.my.blog.Repository;

import com.my.blog.Entity.Board;
import com.my.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
