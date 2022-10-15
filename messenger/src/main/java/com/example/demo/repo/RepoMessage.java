package com.example.demo.repo;

import com.example.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoMessage extends JpaRepository<Message,Long> {


}
