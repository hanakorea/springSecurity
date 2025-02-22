package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{
}
