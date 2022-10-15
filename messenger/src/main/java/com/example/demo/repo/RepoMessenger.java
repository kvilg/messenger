package com.example.demo.repo;


import com.example.demo.model.Messenger;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoMessenger extends JpaRepository<Messenger,Long> {



}
