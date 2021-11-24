package com.example.apiserver.Repo;

import com.example.apiserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findAllByTimeAfter(Timestamp timestamp);
    List<User> findAllByOnline(Boolean online);
    List<User> findAllByTimeAfterAndOnline(Timestamp timestamp, Boolean online);

}
