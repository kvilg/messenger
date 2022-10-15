package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Long> {

    public User findByLogin(String login);

    public User getByLogin(String login);

    public List<User> getByName(String login);

}
