package com.sd.seerserver.service.impl;

import com.sd.seerserver.entity.Comment;
import com.sd.seerserver.mapper.CommentMapper;
import com.sd.seerserver.service.CommentService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Set<Comment> listAll() {
        return commentMapper.listAll();
    }

    @Override
    public int deleteById(long id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public List<Comment> listMine(String name, int limit, int offset) {
        return commentMapper.listMine(name, limit, offset);
    }

    @Override
    public List<Comment> listByPet(int petId, int limit, int offset) {
        return commentMapper.listByPet(petId, limit, offset);
    }

    @Override
    public void addMine(String name, int petId, String text, Timestamp timestamp) {
        commentMapper.addMine(name, petId, text, timestamp);
    }

    @Override
    public int deleteMineById(String name, long id) {
        Comment comment = commentMapper.findById(id);
        if (comment == null) {
            return 0;
        }
        if (!comment.getUser().equals(name)) {
            throw new UnauthorizedException("不能删除不是自己发表的评论");
        }
        return commentMapper.deleteById(id);
    }
}
