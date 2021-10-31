package com.sd.seerserver.service;

import com.sd.seerserver.entity.Comment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface CommentService {
    Set<Comment> listAll();

    int deleteById(long id);

    List<Comment> listMine(String name, int limit, int offset);

    List<Comment> listByPet(int petId, int limit, int offset);

    void addMine(String name, int petId, String text, Timestamp timestamp);

    int deleteMineById(String name, long id);
}
