package com.sd.seerserver.mapper;

import com.sd.seerserver.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.Set;

@Mapper
public interface CommentMapper {
    @Delete("delete from comment where user=#{name}")
    void deleteByUsername(String name);

    @Select("select * from comment")
    Set<Comment> listAll();

    @Delete("delete from comment where id=#{id}")
    int deleteById(long id);

    @Select("select * from comment where user=#{name} and id >= #{minId} order by id desc limit #{limit}")
    Set<Comment> listMine(String name, int limit, int minId);

    @Select("select * from comment where pet=#{petId} and id >= #{minId} order by id desc limit #{limit}")
    Set<Comment> listByPet(int petId, int limit, int minId);

    @Insert("insert into comment(user, pet, time, content) values (#{name}, #{petId}, #{timestamp}, #{text})")
    void addMine(String name, int petId, String text, Timestamp timestamp);

    @Select("select * from comment where id=#{id}")
    Comment findById(long id);
}
