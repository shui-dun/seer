package com.sd.seerserver.mapper;

import com.sd.seerserver.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Mapper
public interface CommentMapper {
    @Delete("delete from comment where user=#{name}")
    void deleteByUsername(String name);

    @Select("select * from comment")
    Set<Comment> listAll();

    @Delete("delete from comment where id=#{id}")
    int deleteById(long id);

    @Select("select c.*, p.name3 as pet_name from comment c join pet p on (c.pet = p.id) where user=#{name} order by id desc limit #{limit} offset #{offset}")
    List<Comment> listMine(String name, int limit, int offset);

    @Select("select * from comment where pet=#{petId} order by id desc limit #{limit} offset #{offset}")
    List<Comment> listByPet(int petId, int limit, int offset);

    @Insert("insert into comment(user, pet, time, content) values (#{name}, #{petId}, #{timestamp}, #{text})")
    void addMine(String name, int petId, String text, Timestamp timestamp);

    @Select("select * from comment where id=#{id}")
    Comment findById(long id);
}
