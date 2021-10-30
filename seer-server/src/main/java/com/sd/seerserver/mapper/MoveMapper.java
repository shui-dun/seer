package com.sd.seerserver.mapper;

import com.sd.seerserver.entity.Move;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MoveMapper {

    @Select("select * from move where pet_id=#{petId}")
    List<Move> findByPetId(int petId);
}
