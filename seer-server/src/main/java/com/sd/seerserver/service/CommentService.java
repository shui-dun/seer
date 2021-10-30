package com.sd.seerserver.service;

import com.sd.seerserver.entity.Comment;

import java.sql.Timestamp;
import java.util.Set;

public interface CommentService {
    Set<Comment> listAll();

    int deleteById(long id);

    Set<Comment> listMine(String name, int limit, int minId);

    Set<Comment> listByPet(int petId, int limit, int minId);

    void addMine(String name, int petId, String text, Timestamp timestamp);

    int deleteMineById(String name, long id);
}
